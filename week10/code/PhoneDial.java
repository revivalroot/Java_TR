package w10;

import javax.swing.*;
import java.awt.*;

public class PhoneDial extends JFrame {

    public PhoneDial() {
        setTitle("임태후의 전화");
        setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 전체 레이아웃
        setLayout(new BorderLayout(0, 20));

        // 위쪽 입력창
        JTextField textField = new JTextField();
        add(textField, BorderLayout.NORTH);

        // 가운데 숫자 버튼 패널
        JPanel numberPanel = new JPanel();
        numberPanel.setLayout(new GridLayout(4, 3));

        String[] buttons = {
                "1", "2", "3",
                "4", "5", "6",
                "7", "8", "9",
                "*", "0", "#"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            numberPanel.add(btn);
        }

        add(numberPanel, BorderLayout.CENTER);

        // 아래 메뉴 패널
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        bottomPanel.add(new JLabel("키패드"));
        bottomPanel.add(new JLabel("최근기록"));
        bottomPanel.add(new JLabel("연락처"));

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new PhoneDial();
    }
}
