package panels;

import Frame.DetailPageFrame;
import models.Comment;
import models.Post;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ContentPanel extends JPanel {
  private List<Post> posts;
  private Post post;
  private JPanel mainPanel;
  private List<Comment> comments;
  private Comment comment;

  public ContentPanel(List<Post> posts, Post post, JPanel mainPanel) {
    this.posts = posts;
    this.post = post;
    this.mainPanel = mainPanel;
    showContentPanel();
  }

  public void showContentPanel() {
    this.removeAll();
    //이 친구가 포스트의 아이덴티파이어랑 코멘트가 가지고 있는 아이덴티파이어랑 같으면 출력해줘라
    for (Post post : posts) {
      if (!post.state().equals("DELETION")) {
        JLabel titleLabel = new JLabel(post.title() + "\t" + "\t" +
            "\t" + post.nickName());
        titleLabel.addMouseListener(new MouseAdapter() {
          public void mouseClicked(MouseEvent event) {
            DetailPageFrame detailPageFrame = new DetailPageFrame(post, posts,
                mainPanel, comments, comment);
            detailPageFrame.setVisible(true);
          }
        });
        this.add(titleLabel);
      }
    }
  }
}
