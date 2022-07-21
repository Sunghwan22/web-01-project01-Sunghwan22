package panels;

import frame.CompletePostDetailPageFrame;
import frame.PostDetailPageFrame;
import models.Comment;
import models.Post;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CompletePostPanel extends JPanel{
  private List<Post> posts;
  private Post post;
  private Comment comment;
  private List<Comment> comments;
  private JPanel mainPanel;

  public CompletePostPanel(List<Post> posts, Post post, Comment comment,
                           List<Comment> comments, JPanel mainPanel) {
    this.posts = posts;
    this.post = post;
    this.comment = comment;
    this.comments = comments;
    this.mainPanel = mainPanel;

    this.setLayout(new GridLayout(0, 1));
    showContentPanel();
  }

  public void showContentPanel() {
    this.removeAll();

    for (Post post : posts) {
      if (post.state().equals("COMPLETE")) {
        JPanel containerPanel = new JPanel();
        this.add(containerPanel);

        JLabel titleLabel = new JLabel("작성자: " + post.nickName() + post.title());

        titleLabel.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent event) {
           CompletePostDetailPageFrame completePostDetailPageFrame =
               new CompletePostDetailPageFrame(post,posts,mainPanel,comments,comment);
            completePostDetailPageFrame.setVisible(true);
          }
        });
        containerPanel.add(titleLabel);
      }
    }
  }
}

