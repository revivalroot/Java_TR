package pj_3w;
import java.util.Scanner;
import java.util.Random;

public class ex_1 {
	public static void main(String args[]) {
		
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		
		int zoms[] = {7, 13, 17, 21, 25};
		int player = 1;
		
		while(true) {
			System.out.println("주인공이 움직일 방법을 고르세요(1(왼쪽), 2(오른쪽), 3(점프)): ");
			int p_move = scanner.nextInt();
			
			if(p_move == 1) {
				player--;
				System.out.println("왼쪽으로 움직였습니다. 주인공의 현재 위치는 "+player + "입니다.");
			}
			else if(p_move == 2) {
				player++;
				System.out.println("오른쪽으로 움직였습니다. 주인공의 현재 위치는 "+player + "입니다.");
			}
			else if(p_move == 3) {
				int jump = random.nextInt(3) + 1;
				player += jump;
				System.out.println("점프했습니다. 주인공의 현재 위치는 "+player + "입니다.");
			}
			
			for(int i=0; i<zoms.length; i++) {
				int m_jombie = random.nextInt(2)*2 - 1;
				zoms[i] += m_jombie;
				System.out.println("좀비"+ (i+1) +"의 현재 위치는 "+zoms[i] + "입니다.");
			}
			
			for(int i=0; i<5; i++) {
				if(player == zoms[i]) {
					System.out.println("좀비에게 잡혔습니다. 처음 위치에서 다시 시작합니다.");
					player = 1;	
					break;
				}
			}
			
			// 종료시점
			if(player >= 30) {
				System.out.println("미션 클리어!!! 목적지에 도착하였습니다.");
				break;
			}	
		}
	}
}
