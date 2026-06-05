
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

public class Kart {
    
    final int MOVE_STEP = 10;       // 캐릭터가 움직이는 간격
    final int MAX_X = 200;          // 맵 크기 x(픽셀)
    final int MAX_Y = 200;          // 맵 크기 y(픽셀)
    
    int x;                          // 캐릭터 x 좌표값(픽셀)
    int y;                          // 캐릭터 y 좌표값(픽셀)    
    
    Image img;                      // 캐릭터 이미지
    int imgWidth = 20;              // 캐릭터 이미지 크기
    int imgHeight = 20;             // 캐릭터 이미지 크기
    boolean status = false;         // 충돌 여부 변수
    
    Random r = new Random();
    
    public Kart(int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }
    
    // 상
    public void moveUp() {
        y = y - MOVE_STEP;
        if(y < 0) y = 0; 
    }
    
    // 하
    public void moveDown() {
        y = y + MOVE_STEP;
        // 버그 수정: MAX_X -> MAX_Y로 변경
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
        int a = r.nextInt(4) + 1;
        
        if(a == 1) moveLeft();
        else if(a == 2) moveRight();
        else if(a == 3) moveUp();
        else if(a == 4) moveDown();
    }

    public void paint(Graphics g) {
        g.drawImage(img, x, y, null);       
    }
}