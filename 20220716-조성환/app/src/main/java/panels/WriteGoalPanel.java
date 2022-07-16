package panels;

import javax.swing.*;

public class WriteGoalPanel extends JPanel {
  public WriteGoalPanel() {
    JLabel label = new JLabel("안녕하십니까");
    this.add(label);
    this.setLayout(null);

    JTextField titleField = new JTextField(20);
    titleField.setBounds(50, 10, 600, 30);
    this.add(titleField);

    JTextArea writeContent = new JTextArea();
    writeContent.setBounds(50, 50, 600, 550);
    writeContent.setLineWrap(true);
    this.add(writeContent);

    JButton registerButton = new JButton("등록");
    registerButton.addActionListener(event -> {
      this.setVisible(false);
    });
    registerButton.setBounds(550, 605,100,50);
    this.add(registerButton);
  }
}
