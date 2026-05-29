package w13;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Kart {
	
	final int MOVE_STEP = 10;		// ĳ���Ͱ� �����̴� ����
	final int MAX_X = 200;				// �� ũ�� x(�ȼ�)
	final int MAX_Y = 200;				// �� ũ�� y(�ȼ�)
	int dir;
	
	int x;										// ĳ���� x ��ǥ��(�ȼ�)
	int y;										// ĳ���� y ��ǥ��(�ȼ�)	
	
	Image img[];								// ĳ���� �̹����� ��� ���� �迭
	int imgWidth = 20;						// ĳ���� �̹��� ũ��
	int imgHeight = 20;					// ĳ���� �̹��� ũ��
	boolean status = false;			// �浹�� �ƴ��� �˱� ���� ���� ����
	
	Random r = new Random();
	
	public Kart(int x, int y, Image img[]) {
		this.x = x;
		this.y = y;
		this.img = img;
	}
	
	// ��
	public void moveUp() {
		y = y - MOVE_STEP;
		if(y < 0) y = 0; 
	}
	
	// ��
	public void moveDown() {
		y = y + MOVE_STEP;
		if(y > MAX_Y - imgHeight) y = MAX_X - imgHeight;
	}
	
	// ��
	public void moveLeft() {
		x = x - MOVE_STEP;
		if(x < 0) x = 0;
	}
	
	// �� 
	public void moveRight() {
		x = x + MOVE_STEP;
		if(x > MAX_X - imgWidth) x = MAX_X - imgWidth;
	}	
	
	// �ڵ����� �����̱� ���� �޼ҵ�
	public void randomMove() {
			
			dir = r.nextInt(4);		
			
			if(dir==0) moveDown();
			else if(dir==1) moveUp();
			else if(dir==2) moveLeft();
			else if(dir==3) moveRight();
	}

	// ȭ�鿡 �׸���
	public void paint(Graphics g) {
		// ĳ������ ���¿� ���� �ٸ� �̹����� �׷���
		if(dir == 0)
			g.drawImage(img[0], x, y, null);
		else if(dir == 1)
			g.drawImage(img[1], x, y, null);
		else if(dir == 2)
			g.drawImage(img[2], x, y, null);
		else if(dir == 3)
			g.drawImage(img[3], x, y, null);	
	}
}
