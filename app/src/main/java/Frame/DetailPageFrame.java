package Frame;

import models.Post;
import panels.ContentPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class DetailPageFrame extends JFrame {

  private JTextField titleField;
  private JTextArea contentArea;
  private JPanel panel;

  private Post post;
  private List<Post> posts;
  private JPanel mainPanel;
  private JPasswordField passwordField;

  public DetailPageFrame(Post post, List<Post> posts, JPanel subPanel, List<String> passwords) {
    this.post = post;
    this.posts = posts;
    this.mainPanel = subPanel;

    createDetailFrame();

    createPanel();

    initPasswordField();

    initTitleField(post);

    initContentArea(post);

    initDeleteButton(post, posts, subPanel);

    initmodifyButton();

    initCompleteModifyButton(post, posts, subPanel);
  }

  private void initPasswordField() {
    passwordField = new JPasswordField(20);
    passwordField.setBounds(50 , 12,200, 35);
    passwordField.setText("비밀번호");
    passwordField.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent event) {
        passwordField.setText("");
      }
    });
    panel.add(passwordField);
  }

  private void initTitleField(Post post) {
    titleField = new JTextField(20);
    titleField.setBounds(50, 55, 500, 35);
    titleField.setText(post.title());
    titleField.setEditable(false);
    panel.add(titleField);
  }

  private void initContentArea(Post post) {
    contentArea = new JTextArea();
    contentArea.setBounds(50, 100, 600, 500);
    contentArea.setText(post.content());
    contentArea.setEditable(false);
    contentArea.setLineWrap(true);
    panel.add(contentArea);
  }

  private void createPanel() {
    panel = new JPanel();
    panel.setLayout(null);
    this.add(panel);
  }

  private void createDetailFrame() {
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.setSize(700, 700);
  }

  private void initDeleteButton(Post post, List<Post> posts, JPanel mainPanel) {
    JButton deleteButton = new JButton("삭제하기");
    deleteButton.addActionListener(event -> {
      post.delete();

      ContentPanel contentPanel = new ContentPanel(posts, post, mainPanel);

      showMainPanel(contentPanel);
      this.setVisible(false);
    });
    panel.add(deleteButton);
    deleteButton.setBounds(550, 605, 100, 50);
  }

  private void initmodifyButton() {
    JButton modifyButton = new JButton("수정하기");
    modifyButton.addActionListener(event -> {
      titleField.setEditable(true);
      contentArea.setEditable(true);
    });
    panel.add(modifyButton);
    modifyButton.setBounds(50, 605, 100, 50);
  }

  private void initCompleteModifyButton(Post post, List<Post> posts, JPanel mainPanel) {
    JButton modifyCompleteButton = new JButton("수정완료");
    modifyCompleteButton.addActionListener(event -> {
      post.modifyTitle(titleField.getText());
      post.modifyContent(contentArea.getText());

      ContentPanel contentPanel = new ContentPanel(posts, post, mainPanel);
      showMainPanel(contentPanel);
      this.setVisible(false);
    });
    panel.add(modifyCompleteButton);
    modifyCompleteButton.setBounds(155, 605, 100, 50);
  }

  private void showMainPanel(JPanel contentPanel) {
    mainPanel.removeAll();
    mainPanel.add(contentPanel);
    mainPanel.setVisible(false);
    mainPanel.setVisible(true);
  }
}
