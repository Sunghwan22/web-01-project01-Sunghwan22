package panels;

import Frame.DetailPageFrame;
import models.Comment;
import models.Post;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PostsPanel extends JPanel {
  private List<Post> posts;
  private Post post;
  private JPanel mainPanel;
  private List<Comment> comments;
  private Comment comment;

  public PostsPanel(List<Post> posts, Post post, Comment comment,
                    List<Comment> comments, JPanel mainPanel) {
    this.posts = posts;
    this.post = post;
    this.comment = comment;
    this.comments = comments;
    this.mainPanel = mainPanel;
    this.setLayout(new GridLayout(0,1));
    showContentPanel();
  }

  public void showContentPanel() {
    this.removeAll();

    for (Post post : posts) {
      if (!post.state().equals("DELETION")) {
        JPanel containerPanel = new JPanel();
        this.add(containerPanel);

        JLabel titleLabel = new JLabel("작성자: " + post.nickName() + post.title());

        titleLabel.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent event) {
            DetailPageFrame detailPageFrame = new DetailPageFrame(post, posts,
                mainPanel, comments,comment);
            detailPageFrame.setVisible(true);
          }
        });
        containerPanel.add(titleLabel);
        containerPanel.setBackground(Color.blue);
      }
    }
  }
}