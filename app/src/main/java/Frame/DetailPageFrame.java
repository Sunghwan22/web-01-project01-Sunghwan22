package Frame;

import models.Post;
import panels.ContentPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DetailPageFrame extends JFrame {

  private JTextField titleField;
  private JTextArea contentArea;
  private JPanel panel;

  private Post post;
  private List<Post> posts;
  private JPanel mainPanel;
  private JPasswordField passwordField;
  private String inputPassword;

  public DetailPageFrame(Post post, List<Post> posts, JPanel mainPanel) {
    this.post = post;
    this.posts = posts;
    this.mainPanel = mainPanel;

    createDetailFrame();

    createPanel();

    initPasswordField();

    initTitleField(post);

    initContentArea(post);

    initDeleteButton(post, posts, mainPanel);

    initmodifyButton();

    initCompleteModifyButton(post, posts, mainPanel);
  }

  private void initPasswordField() {
    JLabel label = new JLabel("비밀번호");
    passwordField = new JPasswordField(20);
    passwordField.setBounds(120, 12, 200, 35);
    panel.add(passwordField);
    panel.add(label);
    label.setBounds(60, 12, 150, 30);
    char[] password = passwordField.getPassword();
    inputPassword = String.valueOf(password);
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

  public void initDeleteButton(Post post, List<Post> posts, JPanel mainPanel) {
    JButton deleteButton = new JButton("삭제하기");
    deleteButton.addActionListener(event -> {
      if (inputPassword.equals(post.passWord())) {
        post.delete();

        ContentPanel contentPanel = new ContentPanel(posts, post, mainPanel);

        showMainPanel(contentPanel);
        this.setVisible(false);
      }

      checkPassword();
    });
    panel.add(deleteButton);
    deleteButton.setBounds(550, 605, 100, 50);
  }

  private void initmodifyButton() {
    JButton modifyButton = new JButton("수정하기");
    modifyButton.addActionListener(event -> {
      if (inputPassword.equals(post.passWord())) {

        titleField.setEditable(true);
        contentArea.setEditable(true);
      }
      checkPassword();
    });
    panel.add(modifyButton);
    modifyButton.setBounds(50, 605, 100, 50);
  }

  private void initCompleteModifyButton(Post post, List<Post> posts, JPanel mainPanel) {
    JButton modifyCompleteButton = new JButton("수정완료");
    modifyCompleteButton.addActionListener(event -> {
      if (inputPassword.equals(post.passWord())) {
        post.modifyTitle(titleField.getText());
        post.modifyContent(contentArea.getText());

        ContentPanel contentPanel = new ContentPanel(posts, post, mainPanel);
        showMainPanel(contentPanel);
        this.setVisible(false);
      }
      checkPassword();
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

  public void checkPassword() {
    if (!inputPassword.equals(post.passWord()) || inputPassword.isBlank()) {
      JFrame warningFrame = new JFrame("Warning");
      warningFrame.setLayout(new GridLayout(2, 1));
      warningFrame.setSize(200, 100);
      warningFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      warningFrame.setVisible(true);

      JLabel messageLabel = new JLabel("비밀번호를 확인하세요");
      warningFrame.add(messageLabel);

      JButton button = new JButton("확인");
      button.addActionListener(event2 -> {
        warningFrame.setVisible(false);
      });
      warningFrame.add(button);
    }
  }
}
