import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calculatingTest extends JFrame implements ActionListener {
    private JTextField textField;
    private JButton[] numberButtons;
    private JButton[] operatorButtons;
    private JButton equalsButton;
    private JButton clearButton;

    private double num1, num2, result;
    private String operator;

    public calculatingTest() {
        setTitle("자바 계산기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 배경색 및 전경색 설정
        Color backgroundColor = Color.decode("#ababab");
        Color foregroundColor = Color.WHITE;
        Color borderColor = Color.decode("#ff6600");

        try {
            UIManager.put("Button.border", new LineBorder(borderColor));
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(this);

        textField = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (isFocusOwner()) {
                    g.setColor(borderColor);
                    g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
                }
            }
        };
        Border lineBorder = BorderFactory.createLineBorder(borderColor,2);
        textField.setBorder(BorderFactory.createCompoundBorder(textField.getBorder(),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        textField.setBorder(lineBorder);
        textField.setFont(new Font("Arial", Font.PLAIN, 36));
        textField.setPreferredSize(new Dimension(300, 80));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setBackground(backgroundColor);
        textField.setForeground(foregroundColor);
        textField.setCaretColor(foregroundColor);
        add(textField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));
        buttonPanel.setBackground(backgroundColor);

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setBackground(backgroundColor);
            numberButtons[i].setForeground(foregroundColor);
            buttonPanel.add(numberButtons[i]);
        }

        operatorButtons = new JButton[4];
        operatorButtons[0] = new JButton("+");
        operatorButtons[1] = new JButton("-");
        operatorButtons[2] = new JButton("*");
        operatorButtons[3] = new JButton("/");
        for (int i = 0; i < 4; i++) {
            operatorButtons[i].addActionListener(this);
            operatorButtons[i].setBackground(backgroundColor);
            operatorButtons[i].setForeground(foregroundColor);
            buttonPanel.add(operatorButtons[i]);
        }

        equalsButton = new JButton("=");
        equalsButton.addActionListener(this);
        equalsButton.setBackground(backgroundColor);
        equalsButton.setForeground(foregroundColor);
        buttonPanel.add(equalsButton);

        clearButton = new JButton("C");
        clearButton.addActionListener(this);
        clearButton.setBackground(backgroundColor);
        clearButton.setForeground(foregroundColor);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9]")) {
            textField.setText(textField.getText() + command);
        } else if (command.matches("[+\\-*/]")) {
            num1 = Double.parseDouble(textField.getText());
            operator = command;
            textField.setText("");
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(textField.getText());
            calculate();
            textField.setText(String.valueOf(result));
        } else if (command.equals("C")) {
            textField.setText("");
            num1 = 0;
            num2 = 0;
            result = 0;
            operator = "";
        }
    }

    private void calculate() {
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2; // 곱셈
                break;
            case "/":
                if (num2 != 0) { // 0으로 나누는지 검사
                    result = num1 / num2; // 나눗셈
                } else {
                    textField.setText("Error"); // 0으로 나누면 에러 메시지 표시
                    return;
                }
                break;
        }
    }

    public static void main(String[] args) { // 메인 메소드
        new calculatingTest(); // 계산기 객체 생성 및 실행
    }
}