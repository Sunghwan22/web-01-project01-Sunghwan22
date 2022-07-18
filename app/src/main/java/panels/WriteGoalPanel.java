package panels;

import models.Post;
import models.UserInforMation;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class WriteGoalPanel extends JPanel {
  private JTextArea writeContent;
  private JTextField titleField;
  private Post post;
  private JPanel mainPanel;
  private List<UserInforMation> userInformations;
  private JTextField userNameField;
  private JPasswordField passwordField;
  private UserInforMation userInforMation;

  public WriteGoalPanel(JPanel menuPanel, List<Post> posts, JPanel mainPanel,
                        List<UserInforMation> userInformations) {
    this.mainPanel = mainPanel;
    this.userInformations = userInformations;
    this.setLayout(null);

    initUserNameField();
    initPassWordField();

    initTitleField();

    initWriteContentArea();

    initRegisterButton(menuPanel, posts, mainPanel);
  }

  private void initUserNameField() {
    userNameField = new JTextField(10);
    userNameField.setBounds(50, 12, 200, 35);
    userNameField.setText("닉네임을 입력 해주세요");
    userNameField.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent event) {
        userNameField.setText("");
      }
    });
    this.add(userNameField);
  }

  private void initPassWordField() {
    passwordField = new JPasswordField(10);
    passwordField.setBounds(260, 12, 200, 35);
    passwordField.setText("비밀번호를 입력해 주세요");
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

      userInforMation = new UserInforMation(nickName, passWord);
      userInformations.add(userInforMation);

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
