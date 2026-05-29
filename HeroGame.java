package subject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class HeroGame extends JFrame {
	
	GamePannel my = new GamePannel();
	public HeroGame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(my);
		this.setSize(500, 300);
		this.setVisible(true);
		my.setFocusable(true);	
		my.requestFocus();
	}
	
	class GamePannel extends JPanel implements KeyListener {		
		
		
		ImageIcon icon1 = new ImageIcon("images/hero01.png");	// 오른쪽
		ImageIcon icon2 = new ImageIcon("images/hero04.png");	// 왼쪽
		
		Image heroRight = icon1.getImage();
		Image heroLeft = icon2.getImage();
		
		int x=0, y=170; // 주인공 좌표
		int dir=0; // 방향 0:right, 1:left
		
		public GamePannel() {
			this.addKeyListener(this);
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.orange);
			g.fillRect(0, 200, getWidth(), getHeight());
			
			if(dir==0) g.drawImage(heroRight, x, y, this);
			else if(dir==1) g.drawImage(heroLeft, x, y, this);
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
				x += 10;
				dir = 0;
			}
			else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
				x -= 10;
				dir = 1;
			}
			repaint();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

	}
	
	public static void main(String[] args) {
		new HeroGame();
	}	
}
