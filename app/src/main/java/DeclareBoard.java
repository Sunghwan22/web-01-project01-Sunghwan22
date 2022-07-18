import models.Post;
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
  private JFrame frame;
  private JPanel menuPanel;
  private List<Post> posts;
  private PostLoader postLoader;
  private WriteGoalPanel writeGoalPanel;
  private ContentPanel contentPanel;
  private Post post;
  private JPanel subPanel;


  public static void main(String[] args) throws FileNotFoundException {
    DeclareBoard declareBoard = new DeclareBoard();
    declareBoard.run();
  }

  public DeclareBoard() throws FileNotFoundException {
    postLoader = new PostLoader();
    posts = postLoader.loadPost();
  }

  private void run() throws FileNotFoundException {
    createFrame();

    initMenuPanel();

    initBackGroundPanel();

    initContentPanel();

    postWriter();

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
    menuPanel.add(createMenuButton());
    frame.add(menuPanel, BorderLayout.PAGE_START);
    menuPanel.setBackground(Color.green);
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
      writeGoalPanel = new WriteGoalPanel(menuPanel, posts,subPanel);
      showWritePanel(writeGoalPanel);
    });
    return button;
  }

  private void initBackGroundPanel() {
    subPanel = new JPanel();
    subPanel.removeAll();
    for (Post post : posts) {
      if (!post.state().equals("DELETION")) {
        JLabel titleLabel = new JLabel(post.title());
        titleLabel.addMouseListener(new MouseAdapter() {
          public void mouseClicked(MouseEvent event) {
            DetailPageFrame detailPageFrame = new DetailPageFrame(post, posts,subPanel);
            detailPageFrame.setVisible(true);
          }
        });
        subPanel.add(titleLabel);
      }
    }
    frame.add(subPanel);
  }

  private void initContentPanel() {
    contentPanel = new ContentPanel(posts,post,subPanel);
  }

  private void showWritePanel(WriteGoalPanel writeGoalPanel) {
    frame.add(writeGoalPanel);
    menuPanel.setVisible(false);
    subPanel.setVisible(false);
    writeGoalPanel.setVisible(true);
  }

  public void postWriter() {
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
}
