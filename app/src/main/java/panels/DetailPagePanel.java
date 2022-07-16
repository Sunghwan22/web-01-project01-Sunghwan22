package panels;

import javax.swing.*;

public class DetailPagePanel extends JPanel {
  private String title;
  private String content;

  public DetailPagePanel(String title, String content) {
    this.title = title;
    this.content = content;

    JPanel panel = new JPanel();
    this.add(panel);
    JTextField titleField = new JTextField(20);
    titleField.setText(title);
    titleField.setBounds(50, 10, 600, 30);
    this.add(titleField);

    JTextArea writeContent = new JTextArea();
    writeContent.setText(content);
    writeContent.setBounds(50, 50, 600, 550);
    writeContent.setLineWrap(true);
    this.add(writeContent);
  }
}
