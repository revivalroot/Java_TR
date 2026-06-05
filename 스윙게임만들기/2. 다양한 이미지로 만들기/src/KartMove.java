import javax.swing.JFrame;

public class KartMove extends JFrame {
    
    // 실제 게임이 돌아갈 패널 추가
    GamePanel gamePanel = new GamePanel();
	
    public KartMove() {
        setTitle("좀비 게임"); // 창 제목 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setSize(200, 250);
		setVisible(true);
		
		this.setContentPane(gamePanel);
		
        gamePanel.setFocusable(true);
		gamePanel.requestFocus();
        
        setLocationRelativeTo(null); // 화면 중앙에 창 띄우기
        setVisible(true);
    }
    
    public static void main(String[] args) {
        
        new KartMove();      
    }
}