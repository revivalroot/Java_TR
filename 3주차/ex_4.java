package pj_3w;
import java.util.Random;
import java.util.Scanner;

class Zombie{
	String name;
	int current;
	
	public Zombie(String name, int current) {
		this.name = name;
		this.current = current;
	}
	
	public void move(int move) {
		this.current += move;
		if(this.current < 0) this.current = 0;
	    if(this.current > 20) this.current = 20;
		System.out.println(this.name+"의 현재위치는 "+ this.current+"입니다.");
	}
}

class Hero{
	String name;
	int current, life_cnt;
	
	public Hero(String name, int current, int life_cnt) {
		this.name = name;
		this.current = current;
		this.life_cnt = life_cnt;
	}
	
	public void jump(int move) {
		
		this.current += move;
		if(this.current > 20) this.current = 20;
		System.out.println(this.name+ "이(가)"+ move+"만큼 점프하였습니다.");
	}
	
	public void leftMove() {
		this.current--;
		if(this.current < 0) this.current = 0;
		System.out.println(this.name+ "이(가) 왼쪽으로 이동하여 현재 위치는"+ this.current+"입니다.");
	}
	
	public void rightMove() {
		this.current++;
		if(this.current > 20) this.current = 20;
		System.out.println(this.name+ "이(가) 오른쪽으로 이동하여 현재 위치는"+ this.current+"입니다.");
	}
}

public class ex_4 {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		Zombie zom1 = new Zombie("좀비 1", 7);
		Zombie zom2 = new Zombie("좀비 2", 15);
		Hero hero = new Hero("주인공", 1, 3);
		
		while(true) {
			System.out.println(hero.name+ "이(가) 움직일 동작을 입력하세요(1:왼쪽, 2:오른쪽, 3:점프):");
			int move = scanner.nextInt();
			
			if(move == 1) {
				hero.leftMove();
			}
			else if(move == 2) {
				hero.rightMove();
			}
			else if(move == 3) {
				hero.jump(random.nextInt(3)+1);
			}
			
			zom1.move(random.nextInt(3)-1);
			zom2.move(random.nextInt(3)-1);
			
			if(hero.current == zom1.current || hero.current == zom2.current) {
				hero.life_cnt--;
				System.out.println("좀비에게 잡혔습니다. 현재 나의 생명은"+ hero.life_cnt+ "개 남았습니다.");
				hero.current=1;
			}
			
			if(hero.life_cnt == 0) {
				System.out.println("생명이 0입니다. 게임을 종료합니다.");
				break;
			}
			
			if(hero.current >= 20) {	
				System.out.println("미션 클리어!! 목적지에 도착하였습니다.");
				break;
			}
		}
		
	}
}
