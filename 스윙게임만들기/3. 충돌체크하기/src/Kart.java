import java.awt.*;
import javax.swing.*;
import java.util.*;

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
        // 기존 오류 수정: Y축 하단 경계선 체크이므로 MAX_Y를 사용해야 정상 작동합니다.
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

    // 다른 캐릭터와 충돌했는지 확인하는 메소드
    public void checkCrash(int ex, int ey) {
        if ((x <= ex) && (ex <= (x + 20))) {
            if((y <= ey) && ( ey <= (y + imgHeight))) {
                status = true;
            }
        }       
        else if ((x <= ex + imgWidth) && (ex + imgWidth <= (x + imgWidth))) {
            if((y <= ey) && ( ey <= (y + imgHeight))) {
                status = true;
            }
        }
        else if ((x <= ex) && (ex <= (x + imgWidth))) {
            if((y <= ey + imgHeight) && ( ey + imgHeight <= (y + imgHeight))) {
                status = true;
            }
        }       
        else if ((x <= ex + imgWidth) && (ex + imgWidth <= (x + imgWidth))) {
            if((y <= ey + imgHeight) && ( ey + imgHeight <= (y + imgHeight))) {
                status = true;
            }
        }   
        
        /*
		int cx = x + 10;
		int cy = y + 13;
		int zx = zombie.x+10;
		int zy = zombie.y+10;
		
		double a = Math.pow(cx-zx, 2.0)+Math.pow(cy-zy, 2.0);
		double distance = Math.sqrt(a);
		
		if(distance<(10+10)) return true;
		else return false;
		*/
    }
    
    // 화면에 그리기
    public void paint(Graphics g) {
        // 캐릭터의 상태(dir)에 따라 다른 이미지를 그려줌
        if(dir == 0)
            g.drawImage(img[0], x, y, null);
        else if(dir == 1)
            g.drawImage(img[1], x, y, null);
        else if(dir == 2)
            g.drawImage(img[2], x, y, null);
        else if(dir == 3)
            g.drawImage(img[3], x, y, null);    
        
        // 내 상태가 충돌일 경우에는 충돌 메시지 출력
        if(status == true) {
            g.drawString("꽝!!! 부딪쳤습니다.", 50, 100);
        }
    }
}