//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class calculating extends JFrame implements ActionListener {
//    // ... (기존 멤버 변수, 생성자 설정 유지)
//
//    public calculating() {
//        // ... (setTitle, setDefaultCloseOperation 유지)
//
//        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
//
//        add(textField);
//
//        JPanel middlePanel = new JPanel();
//        middlePanel.setPreferredSize(new Dimension(0, 50));
//        middlePanel.setBackground(Color.YELLOW);
//        add(middlePanel);
//
//        JPanel buttonContainer = new JPanel(); // buttonPanel을 감싸는 새로운 패널
//        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.Y_AXIS));
//        buttonContainer.add(buttonPanel);  // buttonPanel을 새로운 패널에 추가
//        add(buttonContainer); // 새로운 패널을 프레임에 추가
//
//        // ... (pack(), setLocationRelativeTo(), setVisible() 유지)
//    }
//
//    // ... (나머지 메서드 유지 - actionPerformed, calculate, adjustFontSize)
//}