package panels;

import models.Comment;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WriteCommentPanel extends JPanel {

  private Comment comment;
  private List<Comment> comments;
  private String content;
  private String nickName;
  private String password;
  private JTextArea commentArea;
  private JPasswordField passwordField;
  private JTextField nickNameField;

  public WriteCommentPanel(JPanel showCommentPanel, List<Comment> comments) {
    this.comments = comments;
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
       password = String.valueOf(inputPassword);
       nickName = nickNameField.getText();
       content =  commentArea.getText();

      JPanel commentPanel = new JPanel();
      commentPanel.add(createNickNameLabel());
      commentPanel.add(createCommentArea());
      commentPanel.add(createDeleteButton());

      comment = new Comment(nickName,password,content,Comment.DISPLAY);
      comments.add(comment);
      //post.getIdentifier());
      // 버튼을 클릭할떄 이 포스트에 대한 아이덴파이어가 코멘트의 아이덴티파이어로 지정되어야 함
      // 출력 하는 클래스에 아이덴티 파이어랑 게시글의 아이덴티 파이어랑 같으면 출력 해줘라
      //  todo 쇼 컨텐트 패널에서 파일에서 받아온 친구들을 출력 해줘야 함
      showCommentPanel.add(commentPanel);
      this.setVisible(false);
      showCommentPanel.setVisible(false);
      showCommentPanel.setVisible(true);
    });
    this.add(button);
    button.setBounds(480,315,100,50);
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
}
