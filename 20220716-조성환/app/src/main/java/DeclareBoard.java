import panels.WriteGoalPanel;

import javax.swing.*;
import java.awt.*;

public class DeclareBoard {
  private JFrame frame;
  private JPanel mainPanel;
  private JPanel contentPanel;

  public static void main(String[] args) {
    DeclareBoard declareBoard = new DeclareBoard();
    declareBoard.run();
  }

  private void run() {
    createFrame();

    initMenuPanel();

    initContentPanel();

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
    frame.add(mainPanel, BorderLayout.PAGE_START);
  }

  private JButton createWriteButton() {
    JButton button = new JButton("글 작성하기");
    button.addActionListener(event -> {
      WriteGoalPanel writeGoalPanel = new WriteGoalPanel(contentPanel, mainPanel);
      showWritePanel(writeGoalPanel);
    });
    return button;
  }

  private void initContentPanel() {
    contentPanel = new JPanel();
    frame.add(contentPanel);
    contentPanel.setBackground(Color.blue);
  }

  private void showWritePanel(WriteGoalPanel writeGoalPanel) {
    frame.add(writeGoalPanel);
    mainPanel.setVisible(false);
    contentPanel.setVisible(false);
    writeGoalPanel.setVisible(true);
  }
}
