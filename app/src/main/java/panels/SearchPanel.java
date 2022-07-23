package panels;

import models.Comment;
import models.Post;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SearchPanel extends JPanel {
  private JPanel searchResultPanel;
  private JPanel mainPanel;
  private JPanel menuPanel;
  private Post post;
  private List<Post> posts;
  private Comment comment;
  private List<Comment> comments;

  public SearchPanel(JPanel mainPanel, JPanel menuPanel, Post post,
                     List<Post> posts, Comment comment, List<Comment> comments) {
    this.mainPanel = mainPanel;
    this.menuPanel = menuPanel;
    this.post = post;
    this.posts = posts;
    this.comment = comment;
    this.comments = comments;

    this.setLayout(new BorderLayout());

    initGoBackButton(mainPanel, menuPanel);

    initSearchMenuPanel();

    initFormPanel();
  }

  private void initSearchMenuPanel() {
    searchResultPanel = new JPanel();
    this.add(searchResultPanel, BorderLayout.CENTER);
    searchResultPanel.setLayout(new BorderLayout());
  }

  private void initGoBackButton(JPanel mainPanel, JPanel menuPanel) {
    JPanel buttonPanel = new JPanel();
    this.add(buttonPanel, BorderLayout.PAGE_START);

    JButton button = new JButton("뒤로가기");
    button.addActionListener(event -> {
      this.setVisible(false);
      mainPanel.setVisible(true);
      menuPanel.setVisible(true);
    });
    buttonPanel.add(button);
  }

  private void initFormPanel() {
    JPanel formPanel = new JPanel();

    String[] words = {"작성자", "제목"};
    JComboBox comboBox = new JComboBox(words);
    formPanel.add(comboBox);

    JTextField searchTextField = new JTextField(15);
    formPanel.add(searchTextField);

    JButton searchButton = new JButton("확인");
    searchButton.addActionListener(event -> {
      String text = searchTextField.getText();
      if (text.isBlank()) {
        searchTextField.setText("검색어를 입력해주세요");
      }
      if (!text.isBlank()) {
        String category = String.valueOf(comboBox.getSelectedItem());

        if (category.equals("작성자")) {
          searchNickname(text);
        }
        if (category.equals("제목")) {
          searchTitle(text);
        }
      }
    });
    formPanel.add(searchButton);

    searchResultPanel.add(formPanel, BorderLayout.PAGE_START);
    searchResultPanel.setVisible(false);
    searchResultPanel.setVisible(true);
  }

  private void searchTitle(String text) {
    searchResultPanel.removeAll();

    initFormPanel();

    for (Post post : posts) {
      if (post.state().equals(Post.PROGRESS) &&
          post.title().contains(text)) {
        PostPanel postPanel = new PostPanel(posts, post, comment,
            comments, mainPanel);
        searchResultPanel.add(postPanel);
        postPanel.setVisible(true);
      }
    }
  }

  private void searchNickname(String text) {
    searchResultPanel.removeAll();

    initFormPanel();

    for (Post post : posts) {
      if (post.state().equals(Post.PROGRESS) &&
          post.nickName().contains(text)) {
        PostPanel postPanel = new PostPanel(posts, post, comment,
            comments, mainPanel);
        searchResultPanel.add(postPanel);
        postPanel.setVisible(true);
      }
    }
  }
}
