import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    private ImageIcon[] imgIcon = new ImageIcon[4];
    private Image img[] = new Image[4];
    private Thread th;
    private Kart kart1;
    private Kart kart2;

    public GamePanel() {
        // 패널의 실제 컨텐츠 영역 크기를 200x200으로 설정
        setPreferredSize(new Dimension(200, 200));
        setBackground(Color.WHITE); // 기본 배경색을 흰색으로 지정

        // 이미지 로드
        imgIcon[0] = new ImageIcon("./images/char_up.gif");
        img[0] = imgIcon[0].getImage();
        imgIcon[1] = new ImageIcon("./images/char_down.gif");
        img[1] = imgIcon[1].getImage();
        imgIcon[2] = new ImageIcon("./images/char_left.gif");
        img[2] = imgIcon[2].getImage();
        imgIcon[3] = new ImageIcon("./images/char_right.gif");
        img[3] = imgIcon[3].getImage();

        // 처음 좌표 초기화
        kart1 = new Kart(100, 100);
        kart2 = new Kart(150, 150);

        // 게임 루프 스레드 시작
        th = new Thread(this);
        th.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        // 부모의 paintComponent를 호출하여 흰색 배경을 자동으로 새로 칠해줍니다.
        super.paintComponent(g); 
        
        // 카트 이미지 및 메시지 그리기
        if (kart1 != null) kart1.paint(g);
        if (kart2 != null) kart2.paint(g);
    }

    @Override
    public void run() {
        while (true) {
            // 0.2초간 슬립
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                break;
            }

            // 카트 움직이기
            kart1.randomMove();
            kart2.randomMove();

            // 충돌 체크
            kart1.checkCrash(kart2.x, kart2.y);
            kart2.checkCrash(kart1.x, kart1.y);

            // 화면 갱신 요청 (paintComponent 호출)
            repaint();

            // 충돌 상태 확인 후 충돌 시 반복문(게임) 빠져나감
            if (kart1.status || kart2.status) {
                break;
            }
        }
    }
}