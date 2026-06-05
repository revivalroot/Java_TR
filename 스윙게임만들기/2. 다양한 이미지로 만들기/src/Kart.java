import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Kart {
    
    final int MOVE_STEP = 10;       // 캐릭터가 움직이는 간격
    final int MAX_X = 200;          // 맵 크기 x(픽셀)
    final int MAX_Y = 200;          // 맵 크기 y(픽셀)
    int dir;
    
    int x;                          // 캐릭터 x 좌표값(픽셀)
    int y;                          // 캐릭터 y 좌표값(픽셀)    
    
    private ImageIcon[] imgIcon = new ImageIcon[4];
    private Image img[] = new Image[4];
    
    int imgWidth = 20;              // 캐릭터 이미지 크기
    int imgHeight = 20;             // 캐릭터 이미지 크기
    boolean status = false;         // 충돌 여부 상태 변수
    
    Random r = new Random();
    
    public Kart(int x, int y) {
        this.x = x;
        this.y = y;
        this.img = img;
        
        // 이미지 로드
        imgIcon[0] = new ImageIcon("./images/char_up.gif");
        img[0] = imgIcon[0].getImage();
        imgIcon[1] = new ImageIcon("./images/char_down.gif");
        img[1] = imgIcon[1].getImage();
        imgIcon[2] = new ImageIcon("./images/char_left.gif");
        img[2] = imgIcon[2].getImage();
        imgIcon[3] = new ImageIcon("./images/char_right.gif");
        img[3] = imgIcon[3].getImage();

    }
    
    // 상
    public void moveUp() {
        y = y - MOVE_STEP;
        if(y < 0) y = 0; 
    }
    
    // 하
    public void moveDown() {
        y = y + MOVE_STEP;
        // 오타 수정: 하단 경계선 체크이므로 MAX_X를 MAX_Y로 변경합니다.
        if(y > MAX_Y - imgHeight) y = MAX_Y - imgHeight;
    }
    
    // 좌
    public void moveLeft() {
        x = x - MOVE_STEP;
        if(x < 0) x = 0;
    }
    
    // 우 
    public void moveRight() {
        x = x + MOVE_STEP;
        if(x > MAX_X - imgWidth) x = MAX_X - imgWidth;
    }   
    
    // 자동으로 움직이기 위한 메소드
    public void randomMove() {
        dir = r.nextInt(4);       
        
        if(dir == 0) moveDown();
        else if(dir == 1) moveUp();
        else if(dir == 2) moveLeft();
        else if(dir == 3) moveRight();
    }

    // 화면에 그리기
    public void paint(Graphics g) {
        // 방향(dir)에 맞는 이미지가 배열에 존재할 때만 그려줍니다.
        if (img != null && dir >= 0 && dir < img.length && img[dir] != null) {
            g.drawImage(img[dir], x, y, null);
        }
    }
}