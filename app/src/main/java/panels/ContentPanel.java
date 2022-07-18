package panels;

import models.Post;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ContentPanel extends JPanel {
  private List<Post> posts;
  private Post post;
  private JPanel subPanel;

  public ContentPanel(List<Post> posts, Post post, JPanel subPanel) {
    this.posts = posts;
    this.post = post;
    this.subPanel = subPanel;
    showContentPanel();
  }

  public void showContentPanel() {
    this.removeAll();

    for (Post post : posts) {
      if (!post.state().equals("DELETION")) {
        JLabel titleLabel = new JLabel(post.title());
        titleLabel.addMouseListener(new MouseAdapter() {
          public void mouseClicked(MouseEvent event) {
            DetailPageFrame detailPageFrame = new DetailPageFrame(post, posts,subPanel);
            detailPageFrame.setVisible(true);
          }
        });
        this.add(titleLabel);
      }
    }
  }
}
