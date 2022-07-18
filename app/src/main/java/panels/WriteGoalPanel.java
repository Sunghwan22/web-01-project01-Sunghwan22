package panels;

import models.Post;

import javax.swing.*;
import java.util.List;

public class WriteGoalPanel extends JPanel {
  private Post post;
  private String content;
  private String title;
  private JPanel subPanel;


  public WriteGoalPanel(JPanel menuPanel, List<Post> posts, JPanel subPanel) {
    this.subPanel = subPanel;
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

      ContentPanel contentPanel = new ContentPanel(posts, post, subPanel);

      showMainPanel(contentPanel, menuPanel);
    });
    registerButton.setBounds(550, 605, 100, 50);
    this.add(registerButton);
  }
  //자신이 쓴 글을 검색할 수 있으면 좋겠는데
  private void showMainPanel(JPanel contentPanel, JPanel menuPanel) {
    this.setVisible(false);
    menuPanel.setVisible(true);
    subPanel.removeAll();
    subPanel.add(contentPanel);
    subPanel.setVisible(false);
    subPanel.setVisible(true);
  }
}