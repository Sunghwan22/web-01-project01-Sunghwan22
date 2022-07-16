package week;

import jdk.incubator.vector.VectorOperators;

import javax.swing.*;
import java.awt.*;

public class HelloWorld {
  private String name = "World";

  public static void main(String[] args){
    HelloWorld helloWorld = new HelloWorld();
    helloWorld.run();
  }

  private void run() {
    JFrame frame = new JFrame(greetingMessage());
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setLayout(new GridLayout(3 , 1));
    frame.setSize(400 , 300);

    JLabel label = new JLabel(greetingMessage());
    frame.add(label);

    JTextField textField = new JTextField(10);
    frame.add(textField);

    JButton button = new JButton("확인");
    button.addActionListener(event -> {
      name = textField.getText();
      label.setText(greetingMessage());
    });
    frame.add(button);

    frame.setVisible(true);
  }

  private String greetingMessage() {
    return "Hello" + name + "!";

  }
}
