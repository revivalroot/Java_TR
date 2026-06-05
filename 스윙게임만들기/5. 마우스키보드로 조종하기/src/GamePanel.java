import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener {
    private ImageIcon imgIcon = new ImageIcon();    
    private Image wood_img; 
    
    private Thread th;
    private Kart kart1;	//주인공
    private Kart kart2;
    
	int map[][] = {	{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },					// 10 * 10 크기의 맵 
			{ 1, 0, 0, 0, 0, 1, 0, 0, 1, 1},
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{ 1, 1, 0, 0, 0, 0, 0, 0, 1, 1},
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{ 1, 1, 0, 0, 0, 0, 0, 0, 0, 1},
			{ 1, 1, 1, 0, 1, 1, 0, 1, 0, 1},
			{ 1, 1, 1, 1, 1, 1, 1, 0, 1, 1} };

	
    public GamePanel() {

        imgIcon = new ImageIcon("./images/map_wood.png");
        wood_img = imgIcon.getImage();
        
        // 캐릭터 좌표 초기화 (이미지 배열 전달)
        kart1 = new Kart(20, 20, map);
        kart2 = new Kart(100, 100, map); // 필요 시 주석 해제

        addKeyListener(this);
		addMouseListener(this);

		
        // 게임 스레드 시작
        th = new Thread(this);
        th.start();
    }
    
	  // 맵 배열을 이용하여 맵 그리기
	  public void drawMap(Graphics g) {
		  int xp = 0;	// 맵을 그리기 위한 x 좌표
		  int yp = 0;	// 맵을 그리기 위한 y 좌표
		  
		  for(int i = 0; i < map.length; i++) {
			  for(int j = 0; j < map.length; j++) {
				  // 맵 배열의 값이 1이면 나무를 그림
				  if(map[i][j] == 1) g.drawImage(wood_img, xp, yp, this);
				  // 하나 그리고 다음 좌표로 이동하기 위해 xp 값 증가
				  xp += 20;
			  }
			  // 한줄 다 그리고 나서 한 줄 내려와서 다음 줄 그리기
			  xp = 0;
			  yp += 20;
		  }
	  }


    @Override
    protected void paintComponent(Graphics g) {
        // 부모의 paintComponent를 호출하여 배경색(WHITE)으로 화면을 깨끗하게 지웁니다.
        super.paintComponent(g); 
        
        drawMap(g);

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
            //if (kart1 != null) {
            //    kart1.randomMove();
            //}
            if (kart2 != null) {
                kart2.randomMove();
            }
            
            kart1.checkCrashEnermy(kart2.x, kart2.y);
            kart2.checkCrashEnermy(kart1.x, kart1.y);
    
            // 화면 갱신 요청 -> paintComponent()가 자동으로 호출됨
            repaint();
            
            // 충돌 상태 확인 후 충돌 시 반복문(게임) 빠져나감
			if((kart1.status == true)||(kart2.status == true)) break;			 

        }
    }
    
	  public void keyPressed(KeyEvent e) {
		  if(kart1.status == true) return; // 충돌 상태에서는 키 입력 무시
		  if(e.getKeyCode() == KeyEvent.VK_DOWN) kart1.moveDown();
		  else if(e.getKeyCode() == KeyEvent.VK_UP) kart1.moveUp();
		  else if(e.getKeyCode() == KeyEvent.VK_LEFT) kart1.moveLeft();
		  else if(e.getKeyCode() == KeyEvent.VK_RIGHT) kart1.moveRight();
		  
		  repaint();
	  }
	  
	  public void keyReleased(KeyEvent e) {
		  
	  }
	  public void keyTyped(KeyEvent e) {
		  
	  }
	  public void mousePressed(MouseEvent e) {
		  kart1.x = e.getX();
		  kart1.y = e.getY();
		  
		  repaint();
	  }
	  
	  public void mouseReleased(MouseEvent e) {
		  
	  }
	  
	  public void mouseClicked(MouseEvent e) {
		  
	  }
	  
	  public void mouseEntered(MouseEvent e) {
		  
	  }
	  
	  public void mouseExited(MouseEvent e) {
		  
	  }

}