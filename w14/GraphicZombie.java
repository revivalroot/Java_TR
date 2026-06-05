package w14;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GraphicZombie {
	final int MOVE_STEP = 5;
	final int MAX_X = 500, MAX_Y = 300;
	
	int x, y;
	int imgWidth = 20, imgHeight = 20;
	int dir;
	Random r = new Random();
	boolean toggle;	
	
	ImageIcon batImgIcon[] = new ImageIcon[2];
	Image img[] = new Image[2];
	
	public GraphicZombie(int x, int y) {
		this.x = x;
		this.y = y;
		
		for(int i=0; i<batImgIcon.length; i++) {
			batImgIcon[i] = new ImageIcon("images/enemy" + (i+1) + ".png");
			img[i] = batImgIcon[i].getImage();
		}
	}
	
	public void moveLeft() {
		x = x - MOVE_STEP;
		if(x < 0) x = 0;
	}
	
	public void moveRight() {
		x = x + MOVE_STEP;
		if(x > MAX_X - imgWidth) x = MAX_X - imgWidth;	
	}
	
	public void randomMove() {
		dir = r.nextInt(3);
		if(dir == 0);
		else if(dir == 1) moveLeft();
		else if(dir == 2) moveRight();
		toggle = !toggle;
	}
	
	public void paint(Graphics g) {
		if(toggle) g.drawImage(img[0], x, y, null);
		else g.drawImage(img[1], x, y, null);
	}
	
	/*
	 * public boolean crush() {
	 * 
	 * }
	 */
	
}
