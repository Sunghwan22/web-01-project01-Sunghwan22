package panels;

import models.Comment;

import javax.swing.*;
import java.util.List;

public class CommentContentPanel extends JPanel {
  private List<Comment> comments;
  private Comment comment;

  public CommentContentPanel(List<Comment> comments, Comment comment) {
    this.comments = comments;
    this.comment = comment;
    this.removeAll();

    initCommentPanel(comments);
  }

  private void initCommentPanel(List<Comment> comments) {
    for (Comment comment : comments) {
      if (comment.state().equals(Comment.DISPLAY)) {
        JPanel panel = new JPanel();
        panel.add(createNickNameLabel(comment));
        panel.add(createCommentArea(comment));
        panel.add(createDeleteButton(comment));

        this.add(panel);
      }
    }
  }

  private JTextArea createCommentArea(Comment comment) {
    return new JTextArea(comment.nickName());
  }

  private JLabel createNickNameLabel(Comment comment) {
    return new JLabel(comment.nickName());
  }

  private JButton createDeleteButton(Comment comment) {
    JButton deleteButton = new JButton("X");
    deleteButton.addActionListener(event -> {
      JFrame frame = new JFrame("비밀번호 확인");
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.setSize(200, 100);

      JPanel panel = new JPanel();
      frame.add(panel);

      JPasswordField passwordField = new JPasswordField();
      String password = String.valueOf(passwordField.getPassword());
      panel.add(passwordField);

      frame.setVisible(true);
      JButton button1 = new JButton("확인");
      button1.addActionListener(event1 -> {
        if (password.equals(comment.password())) {
          comment.delete();
          frame.setVisible(false);
        }
      });
      panel.add(button1);

    });
    return deleteButton;
  }
}
