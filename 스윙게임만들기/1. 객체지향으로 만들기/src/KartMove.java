
import javax.swing.JFrame;

public class KartMove extends JFrame {
    
    // 실제 게임이 돌아갈 패널 추가
    GamePanel gamePanel = new GamePanel();
	
    public KartMove() {
        setTitle("좀비 게임");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); // 맵 크기가 고정이므로 창 크기 조절 막기

        setSize(200, 250);
		setVisible(true);
		this.setContentPane(gamePanel);
		
        //add(gamePanel);
        gamePanel.setFocusable(true);
		gamePanel.requestFocus();

        // 패널 크기에 맞게 프레임 크기 자동 조절
        pack(); 
        
        setLocationRelativeTo(null); // 화면 중앙에 배치
        setVisible(true);
    }
    
    public static void main(String[] args) {
    	
    	new KartMove();
    }
}