import javax.swing.*; // Swing GUI 라이브러리 import
import java.awt.*; // AWT 그래픽 라이브러리 import
import java.awt.event.ActionEvent; // 액션 이벤트 import
import java.awt.event.ActionListener; // 액션 리스너 import

public class calculating extends JFrame implements ActionListener { // JFrame 상속 및 ActionListener 구현
    private JTextField textField; // 계산 결과를 보여주는 텍스트 필드
    private JButton[] numberButtons; // 숫자 버튼 배열
    private JButton[] operatorButtons; // 연산자 버튼 배열
    private JButton equalsButton; // 등호 버튼
    private JButton clearButton; // 초기화 버튼

    private double num1, num2, result; // 계산에 사용될 숫자 변수와 결과 변수
    private String operator; // 연산자 저장 변수

    public calculating() { // 생성자
        setTitle("자바 계산기"); // 윈도우 제목 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 윈도우 닫을 때 프로그램 종료
        setLayout(new BorderLayout()); // 레이아웃 설정 (BorderLayout)

        // 배경색 및 전경색 설정
        Color backgroundColor = Color.decode("#e38c00"); // 배경색 설정 (회색)
        Color foregroundColor = Color.BLACK; // 전경색 (글자색) 설정 (흰색)

        textField = new JTextField(); // 텍스트 필드 생성
        textField.setFont(new Font("Arial", Font.PLAIN, 36)); // 폰트 설정 (Arial, 36pt)
        textField.setPreferredSize(new Dimension(150, 180)); // 텍스트 필드 크기 설정 (300x80)
        textField.setHorizontalAlignment(JTextField.RIGHT); // 텍스트 필드 오른쪽 정렬
        textField.setBackground(backgroundColor); // 배경색 설정
        textField.setForeground(foregroundColor); // 전경색 설정
        textField.setCaretColor(foregroundColor); // 커서 색 설정
        add(textField, BorderLayout.NORTH); // 텍스트 필드를 상단에 추가

        JPanel buttonPanel = new JPanel(); // 버튼을 담을 패널 생성
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5)); // 패널 레이아웃 설정 (GridLayout, 4x4, 간격 5)
        buttonPanel.setBackground(backgroundColor); // 배경색 설정

        numberButtons = new JButton[10]; // 숫자 버튼 배열 초기화 (0~9)
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i)); // 숫자 버튼 생성
            numberButtons[i].addActionListener(this); // 액션 리스너 등록
            numberButtons[i].setBackground(backgroundColor); // 배경색 설정
            numberButtons[i].setForeground(foregroundColor); // 전경색 설정
            buttonPanel.add(numberButtons[i]); // 버튼 패널에 추가
        }

        operatorButtons = new JButton[4]; // 연산자 버튼 배열 초기화 (+, -, *, /)
        operatorButtons[0] = new JButton("+"); // 덧셈 버튼 생성
        operatorButtons[1] = new JButton("-"); // 뺄셈 버튼 생성
        operatorButtons[2] = new JButton("*"); // 곱셈 버튼 생성
        operatorButtons[3] = new JButton("/"); // 나눗셈 버튼 생성
        for (int i = 0; i < 4; i++) {
            operatorButtons[i].addActionListener(this); // 액션 리스너 등록
            operatorButtons[i].setBackground(backgroundColor); // 배경색 설정
            operatorButtons[i].setForeground(foregroundColor); // 전경색 설정
            buttonPanel.add(operatorButtons[i]); // 버튼 패널에 추가
        }

        equalsButton = new JButton("="); // 등호 버튼 생성
        equalsButton.addActionListener(this); // 액션 리스너 등록
        equalsButton.setBackground(backgroundColor); // 배경색 설정
        equalsButton.setForeground(foregroundColor); // 전경색 설정
        buttonPanel.add(equalsButton); // 버튼 패널에 추가

        clearButton = new JButton("C"); // 초기화 버튼 생성
        clearButton.addActionListener(this); // 액션 리스너 등록
        clearButton.setBackground(backgroundColor); // 배경색 설정
        clearButton.setForeground(foregroundColor); // 전경색 설정
        buttonPanel.add(clearButton); // 버튼 패널에 추가

        add(buttonPanel, BorderLayout.CENTER); // 버튼 패널을 중앙에 추가

        pack(); // 컴포넌트 크기에 맞게 윈도우 크기 조정
        setLocationRelativeTo(null); // 윈도우를 화면 중앙에 위치
        setVisible(true); // 윈도우를 보이게 설정
    }

    @Override
    public void actionPerformed(ActionEvent e) { // 액션 이벤트 처리 메소드
        String command = e.getActionCommand(); // 눌린 버튼의 텍스트 가져오기

        if (command.matches("[0-9]")) { // 숫자 버튼이 눌렸을 경우
            textField.setText(textField.getText() + command); // 텍스트 필드에 숫자 추가
        } else if (command.matches("[+\\-*/]")) { // 연산자 버튼이 눌렸을 경우
            num1 = Double.parseDouble(textField.getText()); // 텍스트 필드의 숫자를 num1에 저장
            operator = command; // 연산자 저장
            textField.setText(""); // 텍스트 필드 초기화
        } else if (command.equals("=")) { // 등호 버튼이 눌렸을 경우
            num2 = Double.parseDouble(textField.getText()); // 텍스트 필드의 숫자를 num2에 저장
            calculate(); // 계산 수행
            textField.setText(String.valueOf(result)); // 결과 텍스트 필드에 표시
        } else if (command.equals("C")) { // 초기화 버튼이 눌렸을 경우
            textField.setText(""); // 텍스트 필드 초기화
            num1 = 0; // num1 초기화
            num2 = 0; // num2 초기화
            result = 0; // 결과 초기화
            operator = ""; // 연산자 초기화
        }
    }

    private void calculate() { // 계산 메소드
        switch (operator) { // 연산자에 따라 계산 수행
            case "+":
                result = num1 + num2; // 덧셈
                break;
            case "-":
                result = num1 - num2; // 뺄셈
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
        new calculating(); // 계산기 객체 생성 및 실행
    }
}