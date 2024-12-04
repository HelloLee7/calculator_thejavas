// 필요한 라이브러리들을 import 합니다.
import javax.swing.*; // GUI 컴포넌트들을 제공하는 Swing 라이브러리
import java.awt.*; // AWT(Abstract Window Toolkit) 라이브러리, GUI와 그래픽 관련 클래스 제공
import java.awt.event.ActionEvent; // 액션 이벤트 처리를 위한 클래스
import java.awt.event.ActionListener; // 액션 리스너 인터페이스
import javax.swing.border.Border;



// 'calculating' 클래스를 선언합니다. JFrame을 상속받고 ActionListener 인터페이스를 구현합니다.
// JFrame은 윈도우 창을 만드는 클래스이고, ActionListener는 버튼 클릭 등의 이벤트를 처리하는 인터페이스입니다.
public class calculating extends JFrame implements ActionListener {
    // GUI 컴포넌트들을 선언합니다.
    private JTextField textField; // 계산 결과를 보여주고 입력을 받는 텍스트 필드
    private JButton[] numberButtons; // 숫자 버튼들을 저장하는 배열
    private JButton[] operatorButtons; // 연산자 버튼들을 저장하는 배열
    private JButton equalsButton; // '=' 버튼
    private JButton clearButton; // 'C' (Clear) 버튼

    // 계산에 필요한 변수들을 선언합니다.
    private double num1, num2, result; // 첫 번째 숫자, 두 번째 숫자, 계산 결과를 저장하는 변수
    private String operator; // 연산자를 저장하는 변수

    private int defaultFontSize = 36;  // 기본 폰트 크기
    private int minFontSize = 12;      // 최소 폰트 크기

    // 'calculating' 클래스의 생성자입니다.
    public calculating() {
        // 윈도우 창의 제목을 설정합니다.
        setTitle("자바 계산기");
        // 윈도우 창을 닫을 때 프로그램을 종료하도록 설정합니다.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 윈도우 창의 레이아웃을 BorderLayout으로 설정합니다.
        // BorderLayout은 컴포넌트들을 동서남북중앙에 배치하는 레이아웃 매니저입니다.
        setLayout(new BorderLayout());

        // 배경색 및 전경색 설정
        Color backgroundColor = Color.decode("#cfcfcf"); // 배경색을 주황색으로 설정합니다.
        Color foregroundColor = Color.BLACK; // 전경색(글자색)을 검정색으로 설정합니다.

        // 텍스트 필드를 생성하고 설정합니다.
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, defaultFontSize)); // 폰트 설정 (Arial, 기본 스타일, 36 크기)
        textField.setPreferredSize(new Dimension(220, 180)); // 텍스트 필드의 선호 크기를 설정합니다.
        textField.setHorizontalAlignment(JTextField.RIGHT); // 텍스트를 오른쪽 정렬합니다.
        textField.setBackground(backgroundColor); // 배경색 설정
        textField.setForeground(foregroundColor); // 전경색 설정
        textField.setCaretColor(foregroundColor); // caret(커서) 색상 설정
        add(textField, BorderLayout.NORTH); // 텍스트 필드를 윈도우 창의 북쪽에 추가합니다.

        // 버튼들을 담을 패널을 생성하고 설정합니다.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 8, 5)); // 4x4 grid 레이아웃으로 설정, 가로 세로 간격
        buttonPanel.setBackground(backgroundColor); // 배경색 설정

        // 숫자 버튼들을 생성하고 설정합니다.
        numberButtons = new JButton[10]; // 0부터 9까지 10개의 버튼 배열 생성
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i)); // 각 숫자에 해당하는 버튼 생성
            numberButtons[i].addActionListener(this); // 버튼에 액션 리스너(this, 즉 현재 클래스)를 등록합니다.
            numberButtons[i].setBackground(backgroundColor); // 배경색 설정
            numberButtons[i].setForeground(foregroundColor); // 전경색 설정
            buttonPanel.add(numberButtons[i]); // 숫자 버튼을 버튼 패널에 추가합니다.
            Border raisedBevelBorder = BorderFactory.createRaisedBevelBorder();
            numberButtons[i].setBorder(raisedBevelBorder);

            buttonPanel.add(numberButtons[i]);
        }

        // 연산자 버튼들을 생성하고 설정합니다.
        operatorButtons = new JButton[4]; // 4개의 연산자 버튼 배열 생성
        operatorButtons[0] = new JButton("+"); // 덧셈 버튼
        operatorButtons[1] = new JButton("-"); // 뺄셈 버튼
        operatorButtons[2] = new JButton("*"); // 곱셈 버튼
        operatorButtons[3] = new JButton("/"); // 나눗셈 버튼
        for (int i = 0; i < 4; i++) {
            operatorButtons[i].addActionListener(this); // 연산자 버튼에 액션 리스너 등록
            operatorButtons[i].setBackground(backgroundColor); // 배경색 설정
            operatorButtons[i].setForeground(foregroundColor); // 전경색 설정
            buttonPanel.add(operatorButtons[i]); // 연산자 버튼을 버튼 패널에 추가합니다.
            Border raisedBevelBorder = BorderFactory.createRaisedBevelBorder();
            operatorButtons[i].setBorder(raisedBevelBorder);

            buttonPanel.add(operatorButtons[i]);
        }

        // '=' 버튼을 생성하고 설정합니다.
        equalsButton = new JButton("=");
        equalsButton.addActionListener(this); // '=' 버튼에 액션 리스너 등록
        equalsButton.setBackground(backgroundColor); // 배경색 설정
        equalsButton.setForeground(foregroundColor); // 전경색 설정
        buttonPanel.add(equalsButton); // '=' 버튼을 버튼 패널에 추가합니다.
        Border raisedBevelBorder = BorderFactory.createRaisedBevelBorder();
        equalsButton.setBorder(raisedBevelBorder);

        buttonPanel.add(equalsButton);

        // 'C' 버튼을 생성하고 설정합니다.
        clearButton = new JButton("C");
        clearButton.addActionListener(this); // 'C' 버튼에 액션 리스너 등록
        clearButton.setBackground(backgroundColor); // 배경색 설정
        clearButton.setForeground(foregroundColor); // 전경색 설정
        buttonPanel.add(clearButton); // 'C' 버튼을 버튼 패널에 추가합니다.
        clearButton.setBorder(raisedBevelBorder);

        buttonPanel.add(clearButton);

        // 버튼 패널을 윈도우 창의 중앙에 추가합니다.
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(buttonPanel, BorderLayout.CENTER); // buttonPanel을 중앙에 배치
        add(centerPanel, BorderLayout.CENTER); // 새로운 패널을 BorderLayout.CENTER에 추가

        // 남쪽에 빈 공간 추가
        JPanel southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(0, 20));
        southPanel.setBackground(backgroundColor);
        add(southPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // 액션 이벤트 처리 메서드입니다. 버튼을 클릭하면 이 메서드가 호출됩니다.
    @Override
    public void actionPerformed(ActionEvent e) {
        // 발생한 이벤트의 액션 커맨드를 가져옵니다. (예: 숫자 버튼의 숫자, 연산자 기호, "=" 또는 "C")
        String command = e.getActionCommand();

        // 입력된 command가 숫자인 경우
        if (command.matches("[0-9]")) {
            textField.setText(textField.getText() + command); // 텍스트 필드에 입력된 숫자 추가
            adjustFontSize(); // 폰트 크기 조절
        } else if (command.matches("[+\\-*/]")) { // 연산자 버튼(+, -, *, /)이 눌렸을 때
            String currentText = textField.getText(); // 현재 텍스트 필드의 내용을 currentText에 저장
            if (!currentText.isEmpty() && !currentText.matches(".*[+\\-*/]$")) {
                // 텍스트 필드가 비어있지 않고, 마지막 문자가 연산자가 아닌 경우
                try {
                    num1 = Double.parseDouble(currentText); // 현재 텍스트를 숫자(double)로 변환하여 num1에 저장
                } catch (NumberFormatException ex) { // 숫자 변환에 실패하면 (예: 숫자가 아닌 문자 입력)
                    textField.setText("Error"); // 텍스트 필드에 "Error" 표시
                    return; // 메소드 종료
                }
                operator = command;  // 눌린 연산자 버튼의 값을 operator에 저장
                textField.setText(currentText + command); // 텍스트 필드에 기존 내용과 연산자를 함께 표시 (예: "123+", "45.6*")
                adjustFontSize(); // 텍스트 필드의 글자 크기 조정
            } else if (!currentText.isEmpty()){ // 텍스트 필드가 비어있지 않은데 위 조건을 만족하지 않는 경우, 즉 마지막 문자가 연산자인 경우
                operator = command; // 눌린 연산자를 operator에 저장
                textField.setText(currentText.substring(0, currentText.length()-1) + command); // 마지막 연산자를 현재 연산자로 교체
            }
        } else if (command.equals("=")) { // "=" 버튼이 눌렸을 때
            try {
                String expression = textField.getText(); // 텍스트 필드의 내용을 expression에 저장
                if (expression.isEmpty()) return; // 텍스트 필드가 비어있으면 메소드 종료
                if (!expression.matches(".*[+\\-*/].*")) return; // 연산자가 없으면 메소드 종료
                String[] parts = expression.split("[+\\-*/]"); // expression을 연산자를 기준으로 분할하여 parts 배열에 저장
                if(parts.length == 2){ // parts 배열의 길이가 2인 경우 (정상적인 수식 - 숫자, 연산자, 숫자)
                    num1 = Double.parseDouble(parts[0]); // 첫 번째 숫자를 num1에 저장
                    num2 = Double.parseDouble(parts[1]); // 두 번째 숫자를 num2에 저장
                    operator = String.valueOf(expression.charAt(parts[0].length()));// 연산자 추출
                    calculate(); // calculate() 메서드 호출하여 계산 수행
                    textField.setText(String.valueOf(result)); // 계산 결과를 텍스트 필드에 표시
                    adjustFontSize(); // 텍스트 필드의 글자 크기 조정
                }else if (parts.length<2){ // parts 배열의 길이가 2보다 작은 경우 (숫자 또는 연산자 하나만 입력된 경우)
                    textField.setText("Error"); // 텍스트 필드에 "Error" 표시
                }else{ //parts 배열의 길이가 2보다 큰 경우 (연속 계산)
                    num1 = Double.parseDouble(parts[0]);  // 첫 번째 숫자 저장
                    operator = String.valueOf(expression.charAt(parts[0].length())); // 첫 번째 연산자 저장
                    for (int i = 1; i < parts.length; i++) { // parts 배열의 두 번째 요소부터 반복
                        num2 = Double.parseDouble(parts[i]); // 현재 숫자를 num2에 저장
                        calculate(); // 계산 수행
                        num1 = result; // 계산 결과를 num1에 저장 (다음 계산을 위해)
                        if(i+1<parts.length)  operator = String.valueOf(expression.charAt(expression.indexOf(String.valueOf(num2))+String.valueOf(num2).length())); // 다음 연산자 저장
                    }
                    textField.setText(String.valueOf(result)); // 최종 계산 결과를 텍스트 필드에 표시
                    adjustFontSize(); // 텍스트 필드의 글자 크기 조정
                }

            } catch (NumberFormatException ex) { // 숫자 변환에 실패하면 (예: 숫자가 아닌 문자 입력)
                textField.setText("Error"); // 텍스트 필드에 "Error" 표시
            }
            // 입력된 command가 "C"인 경우
        }else if (command.equals("C")) {
            textField.setText(""); // 텍스트 필드 초기화
            num1 = 0; // 첫 번째 숫자 초기화
            num2 = 0; // 두 번째 숫자 초기화
            result = 0; // 결과 초기화
            operator = ""; // 연산자 초기화
            adjustFontSize(); // 폰트 크기 초기화
        }
    }

    // 계산을 수행하는 메서드입니다.
    private void calculate() {
        // 연산자에 따라 계산을 수행합니다.
        switch (operator) {
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
                if (num2 != 0) {
                    result = num1 / num2; // 나눗셈 (0으로 나누는 경우 예외 처리)
                } else {
                    textField.setText("Error"); // 0으로 나누면 "Error" 표시
                    return;
                }
                break;
        }
    }

    // 텍스트 필드의 폰트 크기를 조절하는 메서드입니다.
    private void adjustFontSize() {
        // 현재 텍스트 필드에 입력된 텍스트의 길이를 가져옵니다.
        int currentLength = textField.getText().length();
        // 기본 폰트 크기를 가져옵니다.
        int fontSize = defaultFontSize;

        // 텍스트 길이에 따라 폰트 크기를 조절합니다.
        if (currentLength > 6) {
            // 텍스트 길이가 10자를 초과하면, 폰트 크기를 줄입니다.
            // 초과하는 글자 수마다 폰트 크기를 2씩 줄입니다.
            fontSize = defaultFontSize - (currentLength - 6) * 2;
            // 폰트 크기가 최소 폰트 크기보다 작아지지 않도록 제한합니다.
            fontSize = Math.max(fontSize, minFontSize);
        } else {
            // 텍스트 길이가 6자 이하이면, 기본 폰트 크기를 사용합니다.
            fontSize = defaultFontSize;
        }

        // 텍스트 필드의 폰트를 설정합니다. (폰트 종류: Arial, 스타일: 보통, 크기: 조정된 폰트 크기)
        textField.setFont(new Font("Arial", Font.PLAIN, fontSize));
    }

    // main 메서드, 프로그램의 시작점입니다.
    public static void main(String[] args) {
        // 'calculating' 클래스의 인스턴스를 생성합니다.
        // 이렇게 하면 계산기 창이 나타나고, 사용자와 상호작용할 수 있게 됩니다.
        new calculating();
    }
}