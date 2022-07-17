
import models.Post;
import panels.DetailPagePanel;
import panels.WriteGoalPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeclareBoard {
  private JFrame frame;
  private JPanel mainPanel;
  private JPanel contentPanel;
  private List<Post> posts;
  private PostLoader postLoader;
  private DetailPagePanel detailPagePanel;


  public static void main(String[] args) throws FileNotFoundException {
    DeclareBoard declareBoard = new DeclareBoard();
    declareBoard.run();
  }

  public DeclareBoard() throws FileNotFoundException {
    posts = new ArrayList<>();

    postLoader = new PostLoader();
    posts = postLoader.loadpost();
  }

  private void run() throws FileNotFoundException {
    createFrame();

    initMenuPanel();

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
    mainPanel = new JPanel();
    mainPanel.add(createWriteButton());
    //mainPanel.add(create)
    frame.add(mainPanel, BorderLayout.PAGE_START);
  }

  private JButton createWriteButton() {
    JButton button = new JButton("글 작성하기");
    button.addActionListener(event -> {
      WriteGoalPanel writeGoalPanel = new WriteGoalPanel(contentPanel, mainPanel,
          posts, frame);
      showWritePanel(writeGoalPanel);
    });
    return button;
  }

  private void initContentPanel() {
    contentPanel = new JPanel();
    showContentPanel();
    frame.add(contentPanel);
    contentPanel.setBackground(Color.green);
  }

  private void showWritePanel(WriteGoalPanel writeGoalPanel) {
    frame.add(writeGoalPanel);
    mainPanel.setVisible(false);
    contentPanel.setVisible(false);
    writeGoalPanel.setVisible(true);
  }

  public void showContentPanel() {
    for (Post post : posts) {
      if (!post.state().equals(Post.DELETION)) {

        JLabel titleLabel = new JLabel(post.title());
        titleLabel.addMouseListener(new MouseAdapter() {
          public void mouseClicked(MouseEvent event) {
            detailPagePanel = new DetailPagePanel(posts, post, contentPanel, mainPanel);
            frame.add(detailPagePanel);
            contentPanel.setVisible(false);
            mainPanel.setVisible(false);
            detailPagePanel.setVisible(true);
          }
        });
        contentPanel.add(titleLabel);
      }
    }
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
