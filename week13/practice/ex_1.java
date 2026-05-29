package w13;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ex_1 extends JFrame {
	
	MyPannel my = new MyPannel();
	public ex_1() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(my);
		this.setSize(500, 500);
		this.setVisible(true);
	}
	
	class MyPannel extends JPanel implements MouseMotionListener {		
		
		ImageIcon icon = new ImageIcon("images/pear.jpg");
		Image img = icon.getImage();
		int x = 0;
		int y = 0;
		
		public MyPannel() {
			this.addMouseMotionListener(this);
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			// 실제 패널 그리기
			g.setClip(x, y, 100, 100);
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			x = e.getX();
			y = e.getY();
			repaint();
		}
	}
	
	public static void main(String[] args) {
		new ex_1();
	}	
}
