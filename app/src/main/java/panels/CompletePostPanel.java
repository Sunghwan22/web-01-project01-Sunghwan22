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
        PostPanel postPanel = new PostPanel(posts, post, comment,
            comments, mainPanel);
        this.add(postPanel);
        this.setVisible(true);
      }
    }
  }
}

