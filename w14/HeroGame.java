package w14;
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
	
	class GamePannel extends JPanel implements Runnable, KeyListener {		
		
		GraphicZombie zombie1 = new GraphicZombie(150, 170);
		GraphicZombie zombie2 = new GraphicZombie(300, 170);
		GraphicHero hero = new GraphicHero(0, 170);
		
		int x=0, y=170; // 주인공 좌표
		int dir=0; // 방향 0:right, 1:left
		boolean gameOver;
		
		public GamePannel() {
			hero.dir = 1;
			
			
			this.addKeyListener(this);
			
			new Thread(this).start();
		}
		
		@Override
		public void run() {
			while(true) {
				// 주인공 자동 움직임
				gameOver = hero.heroMove();
				if(gameOver == true) {
					repaint();
					break;
				}
				
				zombie1.randomMove();
				zombie2.randomMove();
				
				repaint();
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.orange);
			g.fillRect(0, 200, getWidth(), getHeight());
			if(gameOver == true) {
				g.drawString("목적지에 도달했습니다. 게임종료", 200, 150); // x,y 위치에 문구 띄우기
			}
			hero.paint(g);
			
			zombie1.paint(g);
			zombie2.paint(g);
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
				hero.moveRight();
				dir = 1;
			}
			else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
				hero.moveLeft();
				dir = 2;
			}
			else if(e.getKeyCode()==KeyEvent.VK_UP) {
				hero.jump = true;
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