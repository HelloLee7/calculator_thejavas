import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calculating extends JFrame implements ActionListener {
    private JTextField textField;
    private JButton[] numberButtons;
    private JButton[] operatorButtons;
    private JButton equalsButton;
    private JButton clearButton;

    private double num1, num2, result;
    private String operator;

    public calculating() {
        setTitle("자바 계산기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 배경색 및 전경색 설정
        Color backgroundColor = Color.BLACK;
        Color foregroundColor = Color.WHITE;

        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 36));
        textField.setPreferredSize(new Dimension(300, 80));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setBackground(backgroundColor); // 배경색 설정
        textField.setForeground(foregroundColor); // 전경색 (글자색) 설정
        textField.setCaretColor(foregroundColor); // 커서 색 설정
        add(textField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));
        buttonPanel.setBackground(backgroundColor); // 배경색 설정

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setBackground(backgroundColor); // 배경색 설정
            numberButtons[i].setForeground(foregroundColor); // 전경색 설정
            buttonPanel.add(numberButtons[i]);
        }

        operatorButtons = new JButton[4];
        operatorButtons[0] = new JButton("+");
        operatorButtons[1] = new JButton("-");
        operatorButtons[2] = new JButton("*");
        operatorButtons[3] = new JButton("/");
        for (int i = 0; i < 4; i++) {
            operatorButtons[i].addActionListener(this);
            operatorButtons[i].setBackground(backgroundColor); // 배경색 설정
            operatorButtons[i].setForeground(foregroundColor); // 전경색 설정
            buttonPanel.add(operatorButtons[i]);
        }

        equalsButton = new JButton("=");
        equalsButton.addActionListener(this);
        equalsButton.setBackground(backgroundColor); // 배경색 설정
        equalsButton.setForeground(foregroundColor); // 전경색 설정
        buttonPanel.add(equalsButton);

        clearButton = new JButton("C");
        clearButton.addActionListener(this);
        clearButton.setBackground(backgroundColor); // 배경색 설정
        clearButton.setForeground(foregroundColor); // 전경색 설정
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
        new calculating();
    }
}