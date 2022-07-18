package panels;

import Frame.DetailPageFrame;
import models.Post;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ContentPanel extends JPanel {
  private List<Post> posts;
  private Post post;
  private JPanel mainPanel;
  private String nickName;
  private List<String> passwords;

  public ContentPanel(List<Post> posts, Post post, JPanel mainPanel) {
    this.posts = posts;
    this.post = post;
    this.mainPanel = mainPanel;
    showContentPanel();
  }

  public void showContentPanel() {
    this.removeAll();

    for (Post post : posts) {
      if (!post.state().equals("DELETION")) {
        JLabel titleLabel = new JLabel(post.title() + "\t" + "\t" +
            "\t" + post.nickName());
        titleLabel.addMouseListener(new MouseAdapter() {
          public void mouseClicked(MouseEvent event) {
            DetailPageFrame detailPageFrame = new DetailPageFrame(post, posts,
                mainPanel, passwords);
            detailPageFrame.setVisible(true);
          }
        });
        this.add(titleLabel);
      }
    }
  }
}
