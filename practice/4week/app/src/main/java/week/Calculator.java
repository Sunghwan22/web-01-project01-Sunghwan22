package week;

import javax.swing.*;
import java.awt.*;
// 사용자 시나리오
// 1을 누르고 3을 누름 커렌트넘버가 13
// +를 누름 -> 어큐뮬레이터가 13 커렌트 넘버가 0이 됨 operator = "+"
// 7을 누르면 어큐뮬레이터가 13 커렌트 넘버가 7




public class Calculator {
  //상수 한번 들어간건 안 바뀜다
  private static final String[] OPERATORS = new String[]{"+" , "-", "*", "/", "="};

  // 프로그램의 핵심 상태
  private long currentNumber = 0;
  private long accumulator = 0;
  private String currentOperator = "";

  // GUI요소
  private JPanel panel;
  private JTextField textField;



  public static void main(String[] args) {
    Calculator calculator = new Calculator();
    calculator.run();
  }

  private void run() {
    JFrame frame = new JFrame("Calculator");
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setSize(400, 400);

    textField = new JTextField(10);
    textField.setEditable(false);
    textField.setHorizontalAlignment(SwingConstants.RIGHT);
    frame.add(textField, BorderLayout.PAGE_START);

     panel = new JPanel();
    panel.setLayout(new GridLayout(4, 3));
    frame.add(panel);

    // 1부터 시작해서 0으로 끝나게 만들어줘야한다..
    for (int i = 0; i < 10; i += 1) {
      int number = (i + 1) % 10;
      JButton button = new JButton(Integer.toString(number));
      button.addActionListener(event -> {
        currentNumber *= 10;
        currentNumber += number;
        updateDisplay(currentNumber);
      });
      panel.add(button);
    }
    // for each문 사용


    for (String operator : OPERATORS) {
      JButton button = new JButton(operator);
      button.addActionListener(event -> {
        switch (currentOperator) {
          case "+" -> accumulator += currentNumber;
          case "-" -> accumulator -= currentNumber;
          case "/" -> accumulator /= currentNumber;
          case "*" -> accumulator *= currentNumber;
          case "" -> accumulator = currentNumber;
        }
        currentOperator = operator;
        currentNumber = 0;
        updateDisplay(accumulator);

      });
      panel.add(button);
    }


    frame.setVisible(true);
  }
  //파라미터를 사용해서 하나의 메서드를 다르게 작동하게 함.
  public void updateDisplay(long number){
    textField.setText(Long.toString(number));
  }
}
