package panels;

import frame.PostDetailPageFrame;
import models.Comment;
import models.Post;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Comparator;
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
    this.setLayout(new GridLayout(0, 1));
    showContentPanel();
  }

  public void showContentPanel() {
    this.removeAll();

    Comparator<Post> views = new Comparator<>() {
      @Override
      public int compare(Post o1, Post o2) {
        return o2.registrationNumber() - o1.registrationNumber();
      }
    };

    Collections.sort(posts,views);

    for (Post post : posts) {
      if (!post.state().equals(Post.DELETION)) {
        PostPanel postPanel = new PostPanel(posts, post, comment, comments,
            mainPanel);
        this.add(postPanel);
      }
    }
  }
}
