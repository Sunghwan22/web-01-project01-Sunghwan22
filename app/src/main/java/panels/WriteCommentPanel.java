package panels;

import models.Comment;
import models.Post;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WriteCommentPanel extends JPanel {

  private Comment comment;
  private JPanel showCommentPanel;
  private List<Comment> comments;
  private Post post;
  private String content;
  private String nickName;
  private String password;
  private JTextArea commentArea;
  private JPasswordField passwordField;
  private JTextField nickNameField;
  private JPanel commentPanel;

  public WriteCommentPanel(JPanel showCommentPanel, List<Comment> comments,
                           Post post) {
    this.showCommentPanel = showCommentPanel;
    this.comments = comments;
    this.post = post;
    this.setLayout(null);

    guideNickname();

    inputNickName();

    guidePassword();

    inputPassword();

    inputConttent();

    initCommentRegisterButton();
  }

  private void inputConttent() {
    commentArea = new JTextArea();
    this.add(commentArea);
    commentArea.setBounds(100,100,480,200);
  }

  private void inputPassword() {
    passwordField = new JPasswordField();
    this.add(passwordField);
    passwordField.setBounds(400,55,180,35);
  }

  private void guidePassword() {
    JLabel label1 = new JLabel("비밀번호: ");
    this.add(label1);
    label1.setBounds(350,20,100,100);
  }

  private void inputNickName() {
    nickNameField = new JTextField(10);
    this.add(nickNameField);
    nickNameField.setBounds(140,55,180,35);
  }

  private void guideNickname() {
    JLabel label = new JLabel("닉네임: ");
    this.add(label);
    label.setBounds(100,20,100,100);
    this.setBackground(Color.cyan);
  }

  private JLabel createNickNameLabel() {
    return new JLabel("닉네임: " + nickName);
  }

  private JTextArea createCommentArea() {
    JTextArea textArea = new JTextArea(content);
    textArea.setEditable(false);
    return textArea;
  }

  // 지금 구조가 어떻게 되냐

  private JButton createDeleteButton() {
    JButton button = new JButton("X");
    button.addActionListener(event -> {
      comment.delete();

    });
    return button;
  }
  private void initCommentRegisterButton() {
    JButton button = new JButton("댓글등록");
    button.addActionListener(event -> {
      DisplayCommentPanel();
    });
    this.add(button);
    button.setBounds(480,315,100,50);
  }
  private void DisplayCommentPanel() {

    nickName = nickNameField.getText();
    password = String.valueOf(passwordField.getPassword());
    content =  commentArea.getText();

    showCommentPanel.removeAll();

    comment = new Comment(nickName,password,content,Comment.DISPLAY,
        post.registrationNumber());
    comments.add(comment);

    CommentContentPanel commentContentPanel = new CommentContentPanel(comments,
        comment,showCommentPanel,post);
    showCommentPanel.add(commentContentPanel);

    this.setVisible(false);
    showCommentPanel.setVisible(true);
  }
}
