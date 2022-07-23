package application;

import models.Comment;
import models.Post;
import utils.RegistraionNumber;
import panels.CompletePostPanel;
import panels.PostLikesPanel;
import panels.PostViewsPanel;
import panels.PostsPanel;
import panels.SearchPanel;
import panels.WriteGoalPanel;
import utils.CommentLoader;
import utils.PostLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class DeclareBoard {
  private List<Comment> comments;
  private List<Post> posts;

  private CommentLoader commentLoader;
  private PostLoader postLoader;

  private Post post;
  private Comment comment;

  private JFrame frame;
  private JPanel menuPanel;
  private WriteGoalPanel writeGoalPanel;
  private PostsPanel postsPanel;
  private JPanel mainPanel;

  public static void main(String[] args) throws FileNotFoundException {
    DeclareBoard declareBoard = new DeclareBoard();
    declareBoard.run();
  }

  public DeclareBoard() throws FileNotFoundException {
    postLoader = new PostLoader();
    posts = postLoader.loadPost();
    RegistraionNumber.setRegistraionNumber(postLoader.loadRegistraionNumber());

    commentLoader = new CommentLoader(post);
    comments = commentLoader.loadComment();

    post = new Post();
  }

  private void run() throws FileNotFoundException {
    createFrame();

    initMenuPanel();

    initmainPanel();

    savePosts();

    frame.setVisible(true);
  }

  private void createFrame() {
    frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(700, 700);
  }

  private void initMenuPanel() {
    menuPanel = new JPanel();
    menuPanel.setLayout(new GridLayout(2,1));

    mainPanel = new JPanel();
    mainPanel.setLayout(new GridLayout(0, 1));

    JPanel buttonPanel = new JPanel();
    menuPanel.add(buttonPanel);
    buttonPanel.add(createByLikeButton());
    buttonPanel.add(createByViewsButton());
    buttonPanel.add(createAllPostButton());
    buttonPanel.add(createSearchButton());
    buttonPanel.add(createWriteButton());
    buttonPanel.add(createCompleteGoalButton());

    initguidePanel();

    frame.add(menuPanel, BorderLayout.PAGE_START);
    frame.add(mainPanel);
  }

  private JButton createByLikeButton() {
    JButton likeButton = new JButton("개념");
    likeButton.addActionListener(event -> {
      mainPanel.removeAll();
      PostLikesPanel postLikesPanel = new PostLikesPanel(posts,post,comment,
          comments,mainPanel);
      showMainPanel(postLikesPanel);
    });
    return likeButton;
  }

  private JButton createByViewsButton() {
    JButton viewsButton = new JButton("조회수");
    viewsButton.addActionListener(event -> {
      mainPanel.removeAll();
      PostViewsPanel postViewsPanel = new PostViewsPanel(posts,post,comment
          ,comments,mainPanel);
      showMainPanel(postViewsPanel);
    });
    return viewsButton;
  }

  private JButton createAllPostButton() {
    JButton allPostButton = new JButton("전체 글");
    allPostButton.addActionListener(event -> {
      mainPanel.removeAll();
      PostsPanel postsPanel = new PostsPanel(posts,post,comment
          ,comments,mainPanel);
      showMainPanel(postsPanel);
    });
    return allPostButton;
  }

  private JButton createCompleteGoalButton() {
    JButton button = new JButton("완료한 목표");
    button.addActionListener(event -> {
      mainPanel.removeAll();
      CompletePostPanel completePostPanel = new CompletePostPanel(posts,post,comment
          ,comments,mainPanel);
      showMainPanel(completePostPanel);
    });
    return button;
  }

  private JButton createSearchButton() {
    JButton button = new JButton("게시글 검색");
    button.addActionListener(event -> {
      menuPanel.setVisible(false);
      mainPanel.setVisible(false);
      SearchPanel searchPanel = new SearchPanel(mainPanel,menuPanel,
          post,posts,comment,comments);
      frame.add(searchPanel);
    });
    return button;
  }

  private JButton createWriteButton() {
    JButton button = new JButton("목표 작성하기");
    button.addActionListener(event -> {
      writeGoalPanel = new WriteGoalPanel(menuPanel, posts, mainPanel,comments,comment);
      showWritePanel(writeGoalPanel);
    });
    return button;
  }

  private void initmainPanel() {
    mainPanel.removeAll();
    PostsPanel postsPanel = new PostsPanel(posts,post,comment
        ,comments,mainPanel);
    mainPanel.add(postsPanel);
  }

  private void showWritePanel(WriteGoalPanel writeGoalPanel) {
    frame.add(writeGoalPanel);
    menuPanel.setVisible(false);
    mainPanel.setVisible(false);
    writeGoalPanel.setVisible(true);
  }

  private void showMainPanel(JPanel panel) {
    mainPanel.add(panel);
    mainPanel.setVisible(false);
    mainPanel.setVisible(true);
  }

  private void initguidePanel() {
    JPanel guidePanel = new JPanel();
    JLabel guideLabel = new JLabel("   번호         " + "                " +
        "         " + "                    제목                       " +
        "                            글쓴이         " +
        "          추천   " +      "       조회수");
    guideLabel.setFont(new Font("serif", Font.BOLD, 13));
    guidePanel.add(guideLabel);
    menuPanel.add(guidePanel);
  }

  public void savePosts() {
    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent event) {
        try {
          postLoader.postWriter(posts);
          postLoader.registraionNumberWriter();
          commentLoader.commentWriter(comments);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    });
  }
}
