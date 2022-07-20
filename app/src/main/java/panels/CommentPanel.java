package panels;

import models.Comment;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CommentPanel extends JPanel {
  private JPanel showCommentPanel;
  private JPanel detailPagePanel;


  //todo 댓글보기 클릭시 마다 게시물 출력하듯이 출력을 해줘야함 그러기 위해서 일단 로더를 만들자
  public CommentPanel(JPanel detailPagePanel, List<Comment> comments, Comment comment) {
    this.detailPagePanel = detailPagePanel;

    this.setLayout(new BorderLayout());

    JPanel menuPanel = new JPanel();
    this.add(menuPanel, BorderLayout.PAGE_START);
    menuPanel.setBackground(Color.blue);

    showCommentPanel = new JPanel();
    this.add(showCommentPanel, BorderLayout.CENTER);
    showCommentPanel.setBackground(Color.green);

    JButton goBackbutton = new JButton("뒤로가기");
    goBackbutton.addActionListener(event -> {
      this.setVisible(false);
      detailPagePanel.setVisible(true);
    });
    menuPanel.add(goBackbutton);

    JButton writeCommentButton = new JButton("댓글쓰기");
    writeCommentButton.addActionListener(event -> {
      showCommentPanel.setVisible(false);
      JPanel writeCommentPanel = new WriteCommentPanel(showCommentPanel, comments);
      this.add(writeCommentPanel);
    });
    menuPanel.add(writeCommentButton);

    showComment(comments, comment);
  }

  private void showComment(List<Comment> comments, Comment comment) {
    CommentContentPanel commentContentPanel = new CommentContentPanel(comments, comment);
    showCommentPanel.add(commentContentPanel);
  }
}

