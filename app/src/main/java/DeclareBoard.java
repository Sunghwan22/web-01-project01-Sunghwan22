import models.Post;
import models.UserInforMation;
import panels.ContentPanel;
import panels.DetailPageFrame;
import panels.WriteGoalPanel;

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
  private List<UserInforMation> userInformations;
  private UserInformationLoader userInformationLoader;
  private List<Post> posts;
  private PostLoader postLoader;
  private Post post;

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

    userInformationLoader = new UserInformationLoader();
    userInformations = userInformationLoader.loadInformation();
  }

  private void run() throws FileNotFoundException {
    createFrame();

    initMenuPanel();

    initmainPanel();

    initContentPanel();

    savePosts();
    saveUserInformation();

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
    JButton button = new JButton("글 작성하기");
    button.addActionListener(event -> {
      writeGoalPanel = new WriteGoalPanel(menuPanel, posts, mainPanel,userInformations);
      showWritePanel(writeGoalPanel);
    });
    return button;
  }

  private void initmainPanel() {
    mainPanel = new JPanel();
    mainPanel.removeAll();
    for (Post post : posts) {
      if (!post.state().equals("DELETION")) {
        JLabel titleLabel = new JLabel(post.title());
        titleLabel.addMouseListener(new MouseAdapter() {
          public void mouseClicked(MouseEvent event) {
            DetailPageFrame detailPageFrame = new DetailPageFrame(post, posts, mainPanel);
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
        PostLoader postLoader = new PostLoader();
        try {
          postLoader.postWriter(posts);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    });
  }

  public void saveUserInformation() {
    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent event) {
        UserInformationLoader userInformationLoader = new UserInformationLoader();
        try {
          userInformationLoader.userInformationWriter(userInformations);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    });
  }
}
