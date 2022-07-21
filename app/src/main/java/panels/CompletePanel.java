package panels;

import models.Post;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CompletePanel extends JPanel {
  private Post post;
  private List<Post> posts;


  public CompletePanel(Post post, List<Post> posts) {
    this.post = post;
    this.posts = posts;

    this.setBackground(Color.green);
    this.setLayout(new GridLayout(0, 1));

    this.removeAll();

    showCompletePost(posts);
  }

  private void showCompletePost(List<Post> posts) {
    for (Post post : posts) {
      if (post.state().equals(Post.COMPLETE)) {
        JPanel panel = new JPanel();
        panel.add(createTitleLabel(post));
        panel.add(createNickNameLabel(post));
        //panel.add(createDeleteButton(post));

        this.add(panel);
      }
    }
  }

  private JLabel createTitleLabel(Post post) {
    JLabel titleLabel = new JLabel(post.title());

    titleLabel.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {

      }
    });

    return titleLabel;
  }


  private JLabel createNickNameLabel(Post post) {
    return new JLabel(post.nickName());
  }
}