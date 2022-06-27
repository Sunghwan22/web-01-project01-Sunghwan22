import utils.TasksPanel;

import javax.swing.*;
import java.awt.*;

public class PostBoard {
  private JFrame frame;
  private JTextField textField;
  private JPanel contentPanel;

  public static void main(String[] args) {
    PostBoard application = new PostBoard();
    application.run();
  }

  private void run() {
    frame = new JFrame("게시판");
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setSize(1000, 900);

    mainPage();
    initContentPanel();


    frame.setVisible(true);
  }

  private void mainPage() {

    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout());
    frame.add(panel, BorderLayout.PAGE_START);

    textField = new JTextField(10);

    panel.add(textField);
    panel.add(createTask());
    frame.add(panel);


  }

  private JButton createTask() {
    JButton button = new JButton("create");
    button.addActionListener(event -> {
      TasksPanel tasksPanel = new TasksPanel();
      contentPanel.add(tasksPanel);
      frame.setVisible(true);
    });

    return button;
  }

  private void initContentPanel() {
    contentPanel = new JPanel();
    frame.add(contentPanel);
  }
}
