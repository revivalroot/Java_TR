
import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {
    private ImageIcon imgIcon;
    private Image img;
    private Thread th;
    private Kart kart1;
    // private Kart kart2; // 주석 해제하여 사용 가능

    public GamePanel() {
        // 패널의 크기를 200x200으로 설정
    	setPreferredSize(new Dimension(200, 200));
        setBackground(Color.WHITE);

        // 이미지 로드
        imgIcon = new ImageIcon("./images/kart.gif");
        img = imgIcon.getImage();

        // 처음 좌표 초기화
        kart1 = new Kart(100, 100, img);
        // kart2 = new Kart(70, 70, img);

        // 게임 루프 스레드 시작
        th = new Thread(this);
        th.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 부모 메서드를 호출하여 배경색(WHITE)을 자동으로 지움
        
        // 카트 이미지 그리기
        if (kart1 != null) {
            kart1.paint(g);
        }
        // if (kart2 != null) { kart2.paint(g); }
    }

    @Override
    public void run() {
        while (true) {
            // 0.2초간 슬립
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                break; // 스레드가 중단되면 루프 종료
            }

            // 카트 움직이기
            if (kart1 != null) {
                kart1.randomMove();
            }
            // if (kart2 != null) { kart2.randomMove(); }

            // 화면 갱신 요청 (paintComponent를 다시 호출하게 됨)
            repaint();
        }
    }
}