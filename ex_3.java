package pj_3w;
import java.util.Random;

class Character{
	String name;
	int hp;
	
	public Character(String name, int hp) {
		this.name = name;
		this.hp = hp;	
	}
	
	public void attack(Character enemy, int damage) {
		enemy.hp -= damage;
		if(enemy.hp < 0) enemy.hp = 0;
	}
	
}

public class ex_3 {
	public static void main(String[] args) {
		
		Random random = new Random();
		Character ryu = new Character("류", 100);
		Character ken= new Character("켄", 200);
		
		int turn=1;
		while(true) {
			
			System.out.println(turn+"번째 턴");
			System.out.println("류의 체력 " + ryu.hp);
			System.out.println("켄의 체력 " + ken.hp);
			System.out.println();
			
			int ryu_dm = random.nextInt(20)+1;
			ryu.attack(ken, ryu_dm);
			if(ken.hp<=0) break;
			
			int ken_dm = random.nextInt(10)+1;
			ken.attack(ryu, ken_dm);
			if(ryu.hp<=0) break;
		
			turn++;
		}
		
		if(ryu.hp>0) {
			System.out.println("최종 결과 류의 승리!");
		}
		else {
			System.out.println("최종 결과 켄의 승리!");
		}
		
	}
}
