package panels;

import frame.PostDetailPageFrame;
import models.Comment;
import models.Post;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PostPanel extends JPanel {


  public PostPanel(List<Post> posts, Post post, Comment comment,
                   List<Comment> comments, JPanel mainPanel) {
    this.removeAll();

    this.setBorder(new LineBorder(Color.BLACK, 1, true));

    JPanel panel = new JPanel();
    this.add(panel);

    JPanel containerPanel = new JPanel();
    panel.add(containerPanel);
    containerPanel.setLayout(new FlowLayout());

    JLabel titleLabel = new JLabel(post.registrationNumber() + "                " +
        "                                   " +  post.title());
    JLabel nickNameLabel = new JLabel("                            " +
        "            " + post.nickName() + "                   " + post.like());
    JLabel likesLabel = new JLabel("          " + post.views());

    titleLabel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent event) {
        PostDetailPageFrame postDetailPageFrame = new PostDetailPageFrame(post, posts,
            mainPanel, comments, comment);
        postDetailPageFrame.setVisible(true);
        post.plusViews();
      }
    });
    containerPanel.add(titleLabel);
    containerPanel.add(nickNameLabel);
    containerPanel.add(likesLabel);
  }
}
