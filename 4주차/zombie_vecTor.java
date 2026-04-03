package	w5_pj;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

abstract class Unit{
	String name;
	int pos;
	
	public Unit(String name, int pos) {
		this.name = name;
		this.pos = pos;
	}
	
	public void left() {
		this.pos--;
		if(this.pos <1) this.pos = 1;
	}
	
	public void right() {
		this.pos++;
		if(this.pos>50) this.pos = 50;
	}
	
	public abstract void move();
}

class Zombie extends Unit{
	Random random;
	
	public Zombie(String name, int pos, Random random) {
		super(name, pos);
		this.random = random;
	}
	
	public void move() {
		int dir = random.nextInt(2);
		if(dir == 0) {
			left();
			System.out.println(this.name+"이(가) 왼쪽으로 움직여 현재위치는 "+ this.pos+"입니다.");
		}
		else {
			right();
			System.out.println(this.name+"이(가) 오른쪽으로 움직여 현재위치는 "+ this.pos+"입니다.");
		}
	}
}

class Zombieking extends Unit{
	Random random;
	
	public Zombieking(String name, int pos, Random random) {
		super(name, pos);
		this.random = random;
	}
	
	public void move() {
		this.pos = random.nextInt(20)+1;
		System.out.println(this.name+"이(가) 순간이동하여 현재위치는 "+ this.pos+"입니다.");
	}
}

class Hero extends Unit{

	Random random;
	Scanner scanner;
	int life_cnt;
	
	public Hero(String name, int pos, int life_cnt, Scanner scanner, Random random) {
		super(name, pos);
		this.life_cnt = life_cnt;
		this.scanner = scanner;
		this.random = random;
	}
	
	public void move() {
		System.out.println(this.name+"이(가) 움직일 동작을 입력하세요(1:왼쪽, 2:오른쪽, 3:점프): ");
		int num = scanner.nextInt();
		if(num == 1) {
			left();
			System.out.println(this.name+"이(가) 왼쪽으로 움직여 현재위치는 "+ this.pos+"입니다.");
		}
		else if(num == 2) {
			right();
			System.out.println(this.name+"이(가) 오른쪽으로 움직여 현재위치는 "+ this.pos+"입니다.");
		}
		else if(num == 3) {
			int jump = random.nextInt(3)+1;
			this.pos += jump;
			if(this.pos > 50) this.pos = 50;
			System.out.println(this.name+"이(가) 점프하여 현재위치는 "+ this.pos+"입니다.");
		}
	}
}

public class zombie_vecTor {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();	
		
		Vector<Zombie> zombies = new Vector<Zombie>();
		
		zombies.add(new Zombie("좀비 1", 10, random));
		zombies.add(new Zombie("좀비 2", 20, random));
		zombies.add(new Zombie("좀비 3", 30, random));
		zombies.add(new Zombie("좀비 4", 40, random));

		Hero hero = new Hero("주인공", 1, 3, scanner, random);
		Zombieking zomking = new Zombieking("좀비킹", 18, random);
		
		while(true) {
			
			hero.move();
			for(int i=0; i<zombies.size(); i++) {
				zombies.get(i).move();
			}
			zomking.move();
			
			System.out.println("주인공 위치: " + hero.pos + " / 생명: " + hero.life_cnt);
			for(int i=0; i<zombies.size(); i++) {
				System.out.println(zombies.get(i).name + "위치: " + zombies.get(i).pos);
			}
			System.out.println("좀비킹 위치: " + zomking.pos);
			
			if(hero.pos >= 50) {	
				System.out.println("미션 클리어!! 목적지에 도착하였습니다.");
				break;
			}
			
			// zombie한테 잡힘
			boolean caught = false;
            for (int i = 0; i < zombies.size(); i++) {
                if (hero.pos == zombies.get(i).pos) {
                    caught = true;
                    break;
                }
            }
			
			if(caught) {
				hero.life_cnt--;
				System.out.println("좀비에게 잡혔습니다. 현재 나의 생명은"+ hero.life_cnt+ "개 남았습니다.");
				hero.pos=1;
			}
			
			if(hero.life_cnt == 0) {
				System.out.println("생명이 0입니다. 게임을 종료합니다.");
				break;
			}
		}
		
		scanner.close();
	}
}
