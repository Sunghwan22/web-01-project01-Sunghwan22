package panels;

import models.Post;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class WriteGoalPanel extends JPanel {
  private Post post;
  private String content;
  private String title;
  private JLabel titleLabel;
  private DetailPagePanel detailPagePanel;
  private List<Post> posts;

  public WriteGoalPanel(JPanel contentPanel, JPanel mainPanel,
                        List<Post> posts, JFrame frame) {
    this.setLayout(null);

    JTextField titleField = new JTextField(20);
    titleField.setBounds(50, 10, 600, 30);
    this.add(titleField);

    JTextArea writeContent = new JTextArea();
    writeContent.setBounds(50, 50, 600, 550);
    writeContent.setLineWrap(true);
    this.add(writeContent);

    JButton registerButton = new JButton("등록");
    registerButton.addActionListener(event -> {

      title = titleField.getText();
      content = writeContent.getText();

      post = new Post(title, content, Post.PROGRESS);
      posts.add(post);

      showMainPanel(contentPanel, mainPanel);

      titleLabel.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent event) {
          detailPagePanel = new DetailPagePanel(posts, post, contentPanel, mainPanel);

          frame.add(detailPagePanel);
          contentPanel.setVisible(false);
          mainPanel.setVisible(false);
          detailPagePanel.setVisible(true);
        }
      });
    });
    registerButton.setBounds(550, 605, 100, 50);
    this.add(registerButton);
  }

  private void showMainPanel(JPanel contentPanel, JPanel mainPanel) {
    this.setVisible(false);
    mainPanel.setVisible(true);
    titleLabel = new JLabel(post.title());
    contentPanel.add(titleLabel);
    contentPanel.setVisible(true);
  }
}