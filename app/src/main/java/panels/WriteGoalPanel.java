package panels;

import Frame.CancelFrame;
import models.Post;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WriteGoalPanel extends JPanel {
  private JPanel menuPanel;
  private JTextArea writeContent;
  private JTextField titleField;
  private Post post;
  private JPanel mainPanel;
  private List<String> passwords;

  private JTextField userNameField;
  private JPasswordField passwordField;
  private ContentPanel contentPanel;
  private String inputPassword;


  // todo 취소하기 뒤로가기 버튼 추가
  public WriteGoalPanel(JPanel menuPanel, List<Post> posts, JPanel mainPanel,
                        List<String> passwords) {
    this.menuPanel = menuPanel;
    this.mainPanel = mainPanel;
    this.passwords = passwords;

    this.setLayout(null);

    initUserNameField();
    initPassWordField();

    initBackButton();

    initTitleField();

    initWriteContentArea();

    initCancelButton();

    initRegisterButton(menuPanel, posts, mainPanel);
  }

  private void initUserNameField() {
    JLabel label = new JLabel("닉네임");
    userNameField = new JTextField(10);
    userNameField.setBounds(240, 12, 150, 35);
    label.setBounds(180, 12, 100, 35);
    this.add(userNameField);
    this.add(label);
  }

  private void initPassWordField() {
    JLabel label = new JLabel("비밀번호");
    passwordField = new JPasswordField();
    passwordField.setBounds(500, 12, 150, 35);
    label.setBounds(440, 12, 150, 35);
    this.add(passwordField);
    this.add(label);
    char[] password = passwordField.getPassword();
    inputPassword = String.valueOf(password);
  }

  public JButton initBackButton() {
    JButton button = new JButton("뒤로가기");
    button.addActionListener(event -> {
      this.setVisible(false);
      menuPanel.setVisible(true);
      mainPanel.setVisible(false);
      mainPanel.setVisible(true);
    });
    this.add(button);
    button.setBounds(30, 5, 100, 50);
    return button;
  }

  private void initTitleField() {
    titleField = new JTextField(20);
    titleField.setBounds(50, 55, 600, 35);
    this.add(titleField);
  }

  private void initWriteContentArea() {
    writeContent = new JTextArea();
    writeContent.setBounds(50, 100, 600, 500);
    writeContent.setLineWrap(true);
    this.add(writeContent);
  }

  private void initRegisterButton(JPanel menuPanel, List<Post> posts, JPanel mainPanel) {
    JButton registerButton = new JButton("등록");
    registerButton.addActionListener(event -> {
      checknickName();
      checkpassWord();
      String title = titleField.getText();
      String content = writeContent.getText();

      char[] inputPassword = passwordField.getPassword();
      String nickName = userNameField.getText();
      String passWord = String.valueOf(inputPassword);

      passwords.add(passWord);

      post = new Post(title, content, Post.PROGRESS, nickName, passWord);
      posts.add(post);

      contentPanel = new ContentPanel(posts, post, mainPanel);

      showMainPanel(contentPanel, menuPanel);
    });
    registerButton.setBounds(550, 605, 100, 50);
    this.add(registerButton);
  }

  private void initCancelButton() {
    JButton button = new JButton("취소");
    button.addActionListener(event -> {
      CancelFrame cancelFrame = new CancelFrame(this, mainPanel, menuPanel);
      cancelFrame.setVisible(true);
    });
    this.add(button);
    button.setBounds(440, 605, 100, 50);
  }

  private void checkpassWord() {
    if (inputPassword.isBlank()) {
      JFrame warningFrame = new JFrame("Warning");
      warningFrame.setLocation(350,350);
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

  private void checknickName() {
    if (userNameField.getText().isBlank()) {
      JFrame warningFrame = new JFrame("Warning");
      warningFrame.setLocation(350,350);
      warningFrame.setLayout(new GridLayout(2, 1));
      warningFrame.setSize(200, 100);
      warningFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      warningFrame.setVisible(true);

      JLabel messageLabel = new JLabel("닉네임을 확인하세요");
      warningFrame.add(messageLabel);

      JButton button = new JButton("확인");
      button.addActionListener(event2 -> {
        warningFrame.setVisible(false);
      });
      warningFrame.add(button);
    }
  }

  public void showMainPanel(JPanel contentPanel, JPanel menuPanel) {
    this.setVisible(false);
    menuPanel.setVisible(true);
    mainPanel.removeAll();
    mainPanel.add(contentPanel);
    mainPanel.setVisible(false);
    mainPanel.setVisible(true);
  }
}
