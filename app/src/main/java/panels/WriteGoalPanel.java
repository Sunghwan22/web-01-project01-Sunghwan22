package panels;

import Frame.CancelFrame;
import models.Post;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    userNameField = new JTextField(10);
    userNameField.setBounds(180, 12, 180, 35);
    userNameField.setText("닉네임을 입력 해주세요");
    userNameField.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent event) {
        userNameField.setText("");
      }
    });
    this.add(userNameField);
  }

  private void initPassWordField() {
    passwordField = new JPasswordField(20);
    passwordField.setBounds(370 , 12, 180, 35);
    passwordField.setText("비밀번호");
    passwordField.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent event) {
        passwordField.setText("");
      }
    });
    this.add(passwordField);
  }

  private void initTitleField() {
    titleField = new JTextField(20);
    titleField.setBounds(50, 55, 500, 35);
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
      String title = titleField.getText();
      String content = writeContent.getText();

      char[] inputPassword = passwordField.getPassword();
      String nickName = userNameField.getText();
      String passWord = String.valueOf(inputPassword);

      passwords.add(passWord);

      post = new Post(title, content, Post.PROGRESS, nickName);
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
      CancelFrame cancelFrame = new CancelFrame(this,mainPanel,menuPanel);
      cancelFrame.setVisible(true);
    });
    this.add(button);
    button.setBounds(440,605,100,50);
  }

  public void showMainPanel(JPanel contentPanel, JPanel menuPanel) {
    this.setVisible(false);
    menuPanel.setVisible(true);
    mainPanel.removeAll();
    mainPanel.add(contentPanel);
    mainPanel.setVisible(false);
    mainPanel.setVisible(true);
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
    button.setBounds(30,5,100,50);
    return button;
  }
}
