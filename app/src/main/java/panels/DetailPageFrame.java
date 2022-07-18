package panels;

import models.Post;

import javax.swing.*;
import java.util.List;

public class DetailPageFrame extends JFrame {

  private final JTextField titleField;
  private final JTextArea contentArea;

  private Post post;
  private List<Post> posts;
  private JPanel subPanel;

  public DetailPageFrame(Post post, List<Post> posts, JPanel subPanel) {
    this.post = post;
    this.posts = posts;
    this.subPanel = subPanel;

    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.setSize(700, 700);

    JPanel panel = new JPanel();
    panel.setLayout(null);
    this.add(panel);

    titleField = new JTextField(20);
    titleField.setBounds(50, 10, 600, 30);
    titleField.setText(post.title());
    titleField.setEditable(false);
    panel.add(titleField);

    contentArea = new JTextArea();
    contentArea.setBounds(50, 50, 600, 550);
    contentArea.setText(post.content());
    contentArea.setEditable(false);
    contentArea.setLineWrap(true);
    panel.add(contentArea);

    JButton deleteButton = new JButton("삭제하기");
    deleteButton.addActionListener(event -> {
      post.delete();

      ContentPanel contentPanel = new ContentPanel(posts, post, subPanel);

      showMainPanel(contentPanel);
      this.setVisible(false);
    });
    panel.add(deleteButton);
    deleteButton.setBounds(550, 605, 100, 50);

    JButton modifyButton = new JButton("수정하기");
    modifyButton.addActionListener(event -> {
      titleField.setEditable(true);
      contentArea.setEditable(true);
    });
    panel.add(modifyButton);
    modifyButton.setBounds(50, 605, 100, 50);

    JButton modifyCompleteButton = new JButton("수정완료");
    modifyCompleteButton.addActionListener(event -> {
      post.modifyTitle(titleField.getText());
      post.modifyContent(contentArea.getText());

      ContentPanel contentPanel = new ContentPanel(posts,post, subPanel);
      showMainPanel(contentPanel);
      this.setVisible(false);
    });
    panel.add(modifyCompleteButton);
    modifyCompleteButton.setBounds(155, 605, 100, 50);
  }

  private void showMainPanel(JPanel contentPanel) {
    subPanel.removeAll();
    subPanel.add(contentPanel);
    subPanel.setVisible(false);
    subPanel.setVisible(true);
  }
}






