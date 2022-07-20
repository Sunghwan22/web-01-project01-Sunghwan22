import models.Comment;
import models.Post;
import models.RegistraionNumber;
import panels.ContentPanel;
import Frame.DetailPageFrame;
import panels.WriteGoalPanel;
import utils.CommentLoader;
import utils.PostLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
  private ContentPanel contentPanel;
  private JPanel mainPanel;

  public static void main(String[] args) throws FileNotFoundException {
    DeclareBoard declareBoard = new DeclareBoard();
    declareBoard.run();
  }

  public DeclareBoard() throws FileNotFoundException {

    postLoader = new PostLoader();
    posts = postLoader.loadPost();

    RegistraionNumber.setRegistraionNumber(postLoader.loadRegistraionNumber());

    commentLoader = new CommentLoader();
    comments = commentLoader.loadComment();
  }

  private void run() throws FileNotFoundException {
    createFrame();

    initMenuPanel();

    initmainPanel();

    initContentPanel();

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
    menuPanel.add(createWriteButton());
    //menuPanel.add(createMenuButton());
    frame.add(menuPanel, BorderLayout.PAGE_START);
  }

  private JButton createMenuButton() {
    JButton button = new JButton();
    button.addActionListener(event -> {
      menuPanel.setVisible(true);
      contentPanel.setVisible(true);
    });
    return button;
  }

  private JButton createWriteButton() {
    JButton button = new JButton("목표 작성하기");
    button.addActionListener(event -> {
      writeGoalPanel = new WriteGoalPanel(menuPanel, posts, mainPanel);
      showWritePanel(writeGoalPanel);
    });
    return button;
  }

  // 그런데 지금 글을 쓰면서 생각을 해보니까

  private void initmainPanel() {
    mainPanel = new JPanel();
    mainPanel.setLayout(new GridLayout(0, 1));
    mainPanel.removeAll();
    for (Post post : posts) {
      if (!post.state().equals("DELETION")) {
        JLabel titleLabel = new JLabel(post.title() + "\t" + post.nickName());
        titleLabel.addMouseListener(new MouseAdapter() {
          public void mouseClicked(MouseEvent event) {
            DetailPageFrame detailPageFrame = new DetailPageFrame(post, posts,
                mainPanel, comments, comment);
            detailPageFrame.setVisible(true);
          }
        });
        mainPanel.add(titleLabel);
      }
    }
    frame.add(mainPanel);
  }

  private void initContentPanel() {
    contentPanel = new ContentPanel(posts, post, mainPanel);
  }

  private void showWritePanel(WriteGoalPanel writeGoalPanel) {
    frame.add(writeGoalPanel);
    menuPanel.setVisible(false);
    mainPanel.setVisible(false);
    writeGoalPanel.setVisible(true);
  }

  public void savePosts() {
    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent event) {
        try {
          postLoader.postWriter(posts);
          commentLoader.commentWriter(comments);
          postLoader.registraionNumberWriter();
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    });
    for(Comment comment1 : comments){
      System.out.println(comment1);
    }

  }
}
