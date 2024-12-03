import javax.swing.*;
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
        setSize(300, 400); // 전체 창 크기 유지
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 36)); // 폰트 크기 확대
        textField.setPreferredSize(new Dimension(300, 80)); // 텍스트 필드 크기 조정
        textField.setHorizontalAlignment(JTextField.RIGHT);
        add(textField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5)); // 행 개수 줄여서 빈 공간 제거

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            buttonPanel.add(numberButtons[i]);
        }

        operatorButtons = new JButton[4];
        operatorButtons[0] = new JButton("+");
        operatorButtons[1] = new JButton("-");
        operatorButtons[2] = new JButton("*");
        operatorButtons[3] = new JButton("/");
        for (int i = 0; i < 4; i++) {
            operatorButtons[i].addActionListener(this);
            buttonPanel.add(operatorButtons[i]);
        }

        equalsButton = new JButton("=");
        equalsButton.addActionListener(this);
        buttonPanel.add(equalsButton);

        clearButton = new JButton("C");
        clearButton.addActionListener(this);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.CENTER);

        pack(); // 컨텐츠 크기에 맞게 창 크기 자동 조절
        setLocationRelativeTo(null); // 화면 중앙에 창 표시
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
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    textField.setText("Error");
                    return;
                }
                break;
        }
    }

    public static void main(String[] args) {
        new calculatingTest();
    }
}