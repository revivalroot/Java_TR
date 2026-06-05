import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    private ImageIcon imgIcon = new ImageIcon();
    
    private Image wood_img; 
    private Thread th;
    private Kart kart1;
    private Kart kart2;
    
    // 10 * 10 크기의 맵 데이터
    private int map[][] = { 
        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
        { 1, 0, 0, 0, 0, 1, 0, 0, 1, 1 },
        { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
        { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
        { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
        { 1, 1, 0, 0, 0, 0, 0, 0, 1, 1 },
        { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
        { 1, 1, 0, 0, 0, 0, 0, 0, 0, 1 },
        { 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 },
        { 1, 1, 1, 1, 1, 1, 1, 0, 1, 1 } 
    };

    public GamePanel() {

        imgIcon = new ImageIcon("./images/map_wood.png");
        wood_img = imgIcon.getImage();

        // 카트 객체 초기화 (동일한 맵 배열 공유)
        kart1 = new Kart(100, 100, map);
        kart2 = new Kart(150, 150, map);

        // 독립 스레드 가동
        th = new Thread(this);
        th.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        // 기존의 fillRect를 대체하여 설정된 배경색(WHITE)으로 깔끔하게 지워줍니다.
        super.paintComponent(g); 
        
        // 1. 배경인 맵 먼저 렌더링
        drawMap(g);

        // 2. 그 위에 카트 얹어서 렌더링
        if (kart1 != null) kart1.paint(g);
        if (kart2 != null) kart2.paint(g);
    }

    // 맵 배열을 순회하며 나무 그리기
    public void drawMap(Graphics g) {
        int xp = 0;   // x 좌표 초기화
        int yp = 0;   // y 좌표 초기화
        
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 1) {
                    g.drawImage(wood_img, xp, yp, this);
                }
                xp += 20; // 가로 타일 크기만큼 이동
            }
            xp = 0;       // 행이 바뀌면 가로 시작점을 0으로 복귀
            yp += 20;     // 세로 타일 크기만큼 아래로 이동
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(200); // 0.2초 대기                 
            } catch (Exception e) {
                break;
            }
            
            // 카트의 자율 랜덤 이동
            if (kart1 != null) kart1.randomMove();
            if (kart2 != null) kart2.randomMove();
    
            // 카트 간 상호 충돌 검사
            if (kart1 != null && kart2 != null) {
                boolean cra = kart1.checkCrash(kart2.x, kart2.y);
                if (cra) {
                	//스레드 종료
					System.out.println("충돌 발생!");
					break; // 충돌 시 게임 종료
				}
                kart2.checkCrashEnermy(kart1.x, kart1.y);
            }
            
            // 화면 재도화 요청 (스윙 내부 메커니즘에 의해 paintComponent 자동 수행)
            repaint();
        }
    }
}