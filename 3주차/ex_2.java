package pj_3w;
import java.util.Scanner;

public class ex_2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int board[][] = {
				{0, 0, 1, 1, 1},
				{1, 0, 0, 1, 1},
				{1, 1, 0, 0, 1},
				{1, 1, 1, 0, 1},
				{1, 1, 1, 0, 0},
		};
		
		int x=0, y=0; // 현재 주인공 위치
		int cnt=0;
		
		while(true) {
			board[x][y] = 2;
			int nx=x;
			int ny=y;
			
			
			// 맵 보여주기
			System.out.println("-현재 맵 상태 (주인공 위치=2)");
			for(int i=0; i<board.length; i++) {
				for(int j=0; j<board[i].length; j++) {
					System.out.print(board[i][j]+ " ");
				}
				System.out.println();
			}
			
			// 도착완료
			if(x == 4 && y == 4) {
				break;
			}
			
			System.out.println("상하좌우로 움직임을 입력하세요(1:상, 2:하, 3:좌, 4:우):");
			int move = scanner.nextInt();
			
			if(move == 1) nx--;
			else if(move == 2) nx++;
			else if(move == 3) ny--;
			else if(move == 4) ny++;
			
			if(nx<0 || ny<0 || nx>5 || ny>5 ) {
				System.out.println("맵 밖으로 나갈 수 없습니다.");
			}
			else if(board[nx][ny] == 1) {
				System.out.println("벽이 있어서 이동할 수 없습니다.");
			}
			else {
				// 전에 있던 위치를 0으로 초기화
				board[x][y] = 0;
				x = nx;
				y = ny;
				// 새로 움직인 위치
				board[x][y] = 2;
				cnt++;
			}
			
		}
		System.out.println("주인공이 "+ cnt+"번 만에 도착하였습니다!!");
		
	}
}
