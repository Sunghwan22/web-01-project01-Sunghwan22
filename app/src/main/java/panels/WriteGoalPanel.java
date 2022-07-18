package panels;

import models.Post;

import javax.swing.*;
import java.util.List;

public class WriteGoalPanel extends JPanel {
  private JTextArea writeContent;
  private JTextField titleField;
  private Post post;
  private String content;
  private String title;
  private JPanel mainPanel;

  public WriteGoalPanel(JPanel menuPanel, List<Post> posts, JPanel mainPanel) {
    this.mainPanel = mainPanel;
    this.setLayout(null);
    // 아이디를 해야한다.
    initTitleField();

    initWriteContentArea();

    initRegisterButton(menuPanel, posts, mainPanel);
  }

  private void initWriteContentArea() {
    writeContent = new JTextArea();
    writeContent.setBounds(50, 50, 600, 550);
    writeContent.setLineWrap(true);
    this.add(writeContent);
  }

  private void initTitleField() {
    titleField = new JTextField(20);
    titleField.setBounds(50, 10, 600, 30);
    this.add(titleField);
  }

  private void initRegisterButton(JPanel menuPanel, List<Post> posts, JPanel mainPanel) {
    JButton registerButton = new JButton("등록");
    registerButton.addActionListener(event -> {
      title = titleField.getText();
      content = writeContent.getText();

      post = new Post(title, content, Post.PROGRESS);
      posts.add(post);

      ContentPanel contentPanel = new ContentPanel(posts, post, mainPanel);

      showMainPanel(contentPanel, menuPanel);
    });
    registerButton.setBounds(550, 605, 100, 50);
    this.add(registerButton);
  }

  private void showMainPanel(JPanel contentPanel, JPanel menuPanel) {
    this.setVisible(false);
    menuPanel.setVisible(true);
    mainPanel.removeAll();
    mainPanel.add(contentPanel);
    mainPanel.setVisible(false);
    mainPanel.setVisible(true);
  }
}
