package panels;

import models.Comment;

import javax.swing.*;
import java.awt.*;

public class WriteCommentPanel extends JPanel {
  private JTextArea commentArea;
  private JPasswordField passwordField;
  private JTextField nickNameField;

  public WriteCommentPanel(JPanel showCommentPanel) {
    this.setLayout(null);

    JLabel label = new JLabel("닉네임: ");
    this.add(label);
    label.setBounds(100,20,100,100);
    this.setBackground(Color.cyan);

    nickNameField = new JTextField(10);
    this.add(nickNameField);
    nickNameField.setBounds(140,55,180,35);

    JLabel label1 = new JLabel("비밀번호: ");
    this.add(label1);
    label1.setBounds(350,20,100,100);

    passwordField = new JPasswordField();
    this.add(passwordField);
    passwordField.setBounds(400,55,180,35);

    commentArea = new JTextArea();
    this.add(commentArea);
    commentArea.setBounds(100,100,480,200);

    JButton button = new JButton("댓글등록");
    button.addActionListener(event -> {
      char[] inputPassword = passwordField.getPassword();
      String password = String.valueOf(inputPassword);
      String nickName = nickNameField.getText();
      String content =  commentArea.getText();

      Comment comment = new Comment(nickName,password,content);

    });
    this.add(button);
    button.setBounds(480,315,100,50);
  }
}
