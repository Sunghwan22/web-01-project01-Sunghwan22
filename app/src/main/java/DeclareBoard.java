import models.Comment;
import models.Post;
import models.RegistraionNumber;
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
  }

  private void run() throws FileNotFoundException {
    createFrame();

    initMenuPanel();

    initmainPanel();

    //initContentPanel();

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
    mainPanel = new JPanel();
    menuPanel.add(createWriteButton());
    menuPanel.add(createSearchButton());
    frame.add(menuPanel, BorderLayout.PAGE_START);
    frame.add(mainPanel);
  }

  private JButton createSearchButton() {
    JButton button = new JButton("게시글 검색");
    button.addActionListener(event -> {
      menuPanel.setVisible(false);
      mainPanel.setVisible(false);
      SearchPanel searchPanel = new SearchPanel(mainPanel,menuPanel,
          post,posts,comment,comments);
      searchPanel.setBackground(Color.green);
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

  // 그런데 지금 글을 쓰면서 생각을 해보니까

  private void initmainPanel() {
    mainPanel.setLayout(new GridLayout(0, 1));
    mainPanel.removeAll();

    PostsPanel postsPanel = new PostsPanel(posts,post,comment
        ,comments,mainPanel);
    //지금 콘텐트 패널에 comments가 없어서 그런거 같은데
    mainPanel.add(postsPanel);
//    for (Post post : posts) {
//      if (!post.state().equals("DELETION")) {
//        JLabel titleLabel = new JLabel(post.title() + "\t" + post.nickName());
//        titleLabel.addMouseListener(new MouseAdapter() {
//          public void mouseClicked(MouseEvent event) {
//            DetailPageFrame detailPageFrame = new DetailPageFrame(post, posts,
//                mainPanel, comments, comment);
//            detailPageFrame.setVisible(true);
//          }   //content패널을 하면 될거 같음
//        });
//        mainPanel.add(titleLabel);
//      }
//    }
//    frame.add(mainPanel);
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
  }
}