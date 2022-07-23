package panels;

import models.Comment;
import models.Post;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DetailCommentPanel extends JPanel {
  private List<Comment> comments;
  private Comment comment;
  private JPanel showCommentPanel;
  private Post post;
  public DetailCommentPanel(List<Comment> comments, Comment comment,
                            JPanel showCommentPanel, Post post) {
    this.comments = comments;
    this.comment = comment;
    this.showCommentPanel = showCommentPanel;
    this.post = post;

    this.setLayout(new GridLayout(0, 1));
    initCommentPanel(comments);
  }

  private void initCommentPanel(List<Comment> comments) {
    this.removeAll();

    for (Comment comment : comments) {
      if (comment.state().equals(Comment.DISPLAY) &&
          comment.registraionNumber() == post.registrationNumber()) {
        JPanel panel = new JPanel();
        panel.add(createNickNameLabel(comment));
        panel.add(createCommentArea(comment));
        panel.add(createDeleteButton(comment));

        this.add(panel);
      }
    }
  }

  private JLabel createNickNameLabel(Comment comment) {
    return new JLabel("글쓴이   " + comment.nickName() + "         ");
  }

  private JTextArea createCommentArea(Comment comment) {
    JTextArea textArea = new JTextArea(comment.content());
    textArea.setEditable(false);
    return textArea;
  }

  private JButton createDeleteButton(Comment comment) {
    JButton deleteButton = new JButton("X");
    deleteButton.addActionListener(event -> {
      JFrame frame = new JFrame("비밀번호 확인");
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.setSize(250, 150);

      JPanel panel = new JPanel();
      panel.setLayout(null);
      frame.add(panel);

      JPasswordField passwordField = new JPasswordField();
      passwordField.setBounds(30,20,200,35);
      panel.add(passwordField);

      frame.setVisible(true);
      JButton button1 = new JButton("확인");
      button1.addActionListener(event1 -> {
        String password = String.valueOf(passwordField.getPassword());

        if (password.equals(comment.password())) {
          comment.delete();

          initCommentPanel(comments);
          System.out.println(password);
          refreshCommentPanel(frame);
        }
        showWarningFrame(comment, password);
      });
      button1.setBounds(75,60,100,35);
      panel.add(button1);

    });
    return deleteButton;
  }

  private void refreshCommentPanel(JFrame frame) {
    showCommentPanel.setVisible(false);
    showCommentPanel.setVisible(true);
    frame.setVisible(false);
  }

  private void showWarningFrame(Comment comment, String password) {
    if (!password.equals(comment.password())) {
      JFrame warningFrame = new JFrame("Warning");
      warningFrame.setLayout(new GridLayout(2, 1));
      warningFrame.setSize(200, 100);
      warningFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      warningFrame.setVisible(true);

      JLabel messageLabel = new JLabel("비밀번호를 확인하세요");
      warningFrame.add(messageLabel);

      JButton button = new JButton("확인");
      button.addActionListener(event2 -> {
        warningFrame.setVisible(false);
      });
      warningFrame.add(button);
    }
  }
}
