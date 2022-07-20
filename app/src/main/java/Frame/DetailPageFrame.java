package Frame;

import models.Comment;
import models.Post;
import panels.CommentPanel;
import panels.ContentPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DetailPageFrame extends JFrame {

  private JTextField titleField;
  private JTextArea contentArea;
  private JPanel detailPagePanel;

  private List<Post> posts;
  private List<Comment> comments;
  private JPanel mainPanel;
  private Post post;
  private Comment comment;
  private JPasswordField passwordField;
  private String inputPassword;


  public DetailPageFrame(Post post, List<Post> posts, JPanel mainPanel,
                         List<Comment> comments, Comment comment) {
    this.post = post;
    this.posts = posts;
    this.mainPanel = mainPanel;
    this.comments = comments;
    this.comment = comment;

    createDetailFrame();

    createPanel();

    initPasswordField();

    initTitleField(post);

    initContentArea(post);

    initDeleteButton(post, posts, mainPanel);

    initmodifyButton();

    initCompleteModifyButton(post, posts, mainPanel);

    createCommentButton();

    for(Comment comment1 : comments){
      System.out.println(comment1);
    }
  }

  private void createCommentButton() {
    JButton button = new JButton("댓글보기");
    button.addActionListener(event -> {
      detailPagePanel.setVisible(false);
      CommentPanel commentPanel = new CommentPanel(detailPagePanel,comments,comment);
      this.add(commentPanel);
    });
    detailPagePanel.add(button);
    button.setBounds(550, 12, 100, 50);
  }

  private void createDetailFrame() {
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.setSize(700, 700);
  }

  private void initPasswordField() {
    JLabel label = new JLabel("비밀번호");
    passwordField = new JPasswordField(20);
    passwordField.setBounds(120, 12, 200, 35);
    passwordField.setEditable(true);
    detailPagePanel.add(passwordField);
    detailPagePanel.add(label);
    label.setBounds(60, 12, 150, 30);
  }

  private void createPanel() {
    detailPagePanel = new JPanel();
    detailPagePanel.setLayout(null);
    this.add(detailPagePanel);
  }

  private void initTitleField(Post post) {
    titleField = new JTextField(20);
    titleField.setBounds(50, 55, 450, 35);
    titleField.setText(post.title());
    titleField.setEditable(false);
    detailPagePanel.add(titleField);
  }

  private void initContentArea(Post post) {
    contentArea = new JTextArea();
    contentArea.setBounds(50, 100, 600, 500);
    contentArea.setText(post.content());
    contentArea.setEditable(false);
    contentArea.setLineWrap(true);
    detailPagePanel.add(contentArea);
  }

  public void initDeleteButton(Post post, List<Post> posts, JPanel mainPanel) {
    JButton deleteButton = new JButton("삭제하기");
    deleteButton.addActionListener(event -> {
      inputPassword();

      if (inputPassword.equals(post.passWord())) {
        post.delete();

        ContentPanel contentPanel = new ContentPanel(posts, post, mainPanel);

        showMainPanel(contentPanel);
        this.setVisible(false);
      }

      checkPassword();
    });
    detailPagePanel.add(deleteButton);
    deleteButton.setBounds(550, 605, 100, 50);
  }

  private void initmodifyButton() {
    JButton modifyButton = new JButton("수정하기");
    modifyButton.addActionListener(event -> {
      inputPassword();

      if (inputPassword.equals(post.passWord())) {

        titleField.setEditable(true);
        contentArea.setEditable(true);
      }
      checkPassword();
    });
    detailPagePanel.add(modifyButton);
    modifyButton.setBounds(50, 605, 100, 50);
  }

  private void initCompleteModifyButton(Post post, List<Post> posts, JPanel mainPanel) {
    JButton modifyCompleteButton = new JButton("수정완료");
    modifyCompleteButton.addActionListener(event -> {
      inputPassword();

      if (inputPassword.equals(post.passWord())) {
        post.modifyTitle(titleField.getText());
        post.modifyContent(contentArea.getText());

        ContentPanel contentPanel = new ContentPanel(posts, post, mainPanel);
        showMainPanel(contentPanel);
        this.setVisible(false);
      }
      checkPassword();
    });
    detailPagePanel.add(modifyCompleteButton);
    modifyCompleteButton.setBounds(155, 605, 100, 50);
  }

  private void showMainPanel(JPanel contentPanel) {
    mainPanel.removeAll();
    mainPanel.add(contentPanel);
    mainPanel.setVisible(false);
    mainPanel.setVisible(true);
  }

  public void checkPassword() {
    inputPassword();

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

  private void inputPassword() {
    char[] password = passwordField.getPassword();
    inputPassword = String.valueOf(password);
  }
}
