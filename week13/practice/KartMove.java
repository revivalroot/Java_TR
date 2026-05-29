package w13;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class KartMove extends JFrame implements Runnable {
	ImageIcon[] imgIcon = new ImageIcon[4];
	Image img[] = new Image[4];
	Thread th;
	Kart kart1;
	Kart kart2;
	
	public KartMove() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(200, 200);
		setUndecorated(true);    

		setVisible(true);
		//�̹��� �ε�
		imgIcon[0] = new ImageIcon("./images/char_up.gif");
		img[0] = imgIcon[0].getImage();
		imgIcon[1] = new ImageIcon("./images/char_down.gif");
		img[1] = imgIcon[1].getImage();
		imgIcon[2] = new ImageIcon("./images/char_left.gif");
		img[2] = imgIcon[2].getImage();
		imgIcon[3] = new ImageIcon("./images/char_right.gif");
		img[3] = imgIcon[3].getImage();


		// ó�� ��ǥ �ʱ�ȭ
		kart1 = new Kart(100, 100, img);
		//kart2 = new Kart(70, 70, img);
	
		new Thread(this).start();

	}

	public void start() {
		  if(th==null){
			  th= new Thread(this);
			  th.start();
	    }
	  }
	
	  public void paint(Graphics g) {
		  
		  //���������� �����
		  g.setColor(Color.white);
		  g.fillRect(0, 0, 200, 200);
			
		  // īƮ �̹��� �׸���
		  kart1.paint(g);
		  //kart2.paint(g);
	  }

	  public void run() {
		  
		  while(true) {
			  
			  // 0.2�ʰ� ����
			  try {
				  Thread.sleep(200);				  
			  } catch(Exception e) { }
			  
			  // īƮ  �����̱�
			  kart1.randomMove();
			  // kart2.randomMove();
	
			  // ȭ�� ����
			  repaint();
		  }
	  }
	  
	  public static void main(String[] args) {
			// TODO Auto-generated method stub
			new KartMove().setTitle("���� ����");
	  }

}
