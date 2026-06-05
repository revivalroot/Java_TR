import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
   
    private Thread th;
    private Kart kart1;
    private Kart kart2;

    public GamePanel() {

        // 캐릭터 좌표 초기화 (이미지 배열 전달)
        kart1 = new Kart(0, 0);
        // kart2 = new Kart(70, 70, img); // 필요 시 주석 해제

        // 게임 스레드 시작
        th = new Thread(this);
        th.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        // 부모의 paintComponent를 호출하여 배경색(WHITE)으로 화면을 깨끗하게 지웁니다.
        super.paintComponent(g); 
        
        // 카트 그리기
        if (kart1 != null) {
            kart1.paint(g);
        }
        if (kart2 != null) {
            kart2.paint(g);
        }
    }

    @Override
    public void run() {
        while (true) {
            // 0.2초간 대기
            try {
                Thread.sleep(200);                
            } catch (InterruptedException e) {
                break; // 스레드가 종료되면 루프 탈출
            }
            
            // 카트 랜덤 이동
            if (kart1 != null) {
                kart1.randomMove();
            }
            if (kart2 != null) {
                kart2.randomMove();
            }
    
            // 화면 갱신 요청 -> paintComponent()가 자동으로 호출됨
            repaint();
        }
    }
}