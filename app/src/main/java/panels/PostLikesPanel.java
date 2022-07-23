package panels;

import models.Comment;
import models.Post;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PostLikesPanel extends JPanel {
  private List<Post> posts;
  private Post post;
  private Comment comment;
  private List<Comment> comments;
  private JPanel mainPanel;

  public PostLikesPanel(List<Post> posts, Post post, Comment comment,
                        List<Comment> comments, JPanel mainPanel) {
    this.posts = posts;
    this.post = post;
    this.comment = comment;
    this.comments = comments;
    this.mainPanel = mainPanel;

    this.removeAll();
    this.setLayout(new GridLayout(0, 1));


    Comparator<Post> views = new Comparator<>() {
      @Override
      public int compare(Post o1, Post o2) {
        return o2.like() - o1.like();
      }
    };

    Collections.sort(posts,views);

    for (Post post1 : posts) {
      if (!post1.state().equals("DELETION")) {
        PostPanel postPanel = new PostPanel(posts, post1, comment,
            comments, mainPanel);
        this.add(postPanel);
        this.setVisible(true);
      }
    }
  }
}
