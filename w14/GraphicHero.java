package w14;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GraphicHero {
	final int MOVE_STEP = 5;
	final int MAX_X = 500, MAX_Y = 300;
	
	int x, y;
	int imgWidth = 21, imgHeight = 26;
	int dir;	// 1: right, 2: left;
	int count, jump_count=1;
	Random r = new Random();
	boolean jump;	// 점프 상태인지 아닌지
	
	ImageIcon heroImgIcon[] = new ImageIcon[6];
	Image img[] = new Image[6];
	
	public GraphicHero(int x, int y) {
		this.x = x;
		this.y = y;
		
		for(int i=0; i<heroImgIcon.length; i++) {
			heroImgIcon[i] = new ImageIcon("images/hero0" + (i+1) + ".png");
			img[i] = heroImgIcon[i].getImage();
		}
	}
	
	public void moveLeft() {
		x = x - MOVE_STEP;
		if(x < 0) x = 0;
		dir=2;
	}
	
	public void moveRight() {
		x = x + MOVE_STEP;
		if(x > MAX_X - imgWidth) x = MAX_X - imgWidth;	
		dir=1;
	}
	
	public boolean heroMove() {	
		
		if(jump == true) {
			if(jump_count <= 5) y -= 10;
			else if(jump_count <=10) y += 10;
			if(jump_count == 10) {
				jump_count = 1;
				jump = false;
			}
			else {
				jump_count++;
			}
		}
		
		count++;
		if(x >= MAX_X-imgWidth) return true;
		else return false;
	}
	
	public void paint(Graphics g) {
		if(dir==1) {
			if(jump == true) {
				g.drawImage(img[2], x, y, null);
			}
			g.drawImage(img[count%2], x, y, null);
		}
		else if(dir==2) {
			if(jump == true) {
				g.drawImage(img[5], x, y, null);
			}
			g.drawImage(img[count%2+3], x, y, null); 
		}
	}
	
	/*
	 * public boolean crush() {
	 * 
	 * }
	 */
	
}
