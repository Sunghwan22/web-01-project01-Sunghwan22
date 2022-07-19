package panels;

import javax.swing.*;
import java.awt.*;

public class CommentPanel extends JPanel {

  private JPanel showCommentPanel;
  private JPanel detailPagePanel;

  public CommentPanel(JPanel detailPagePanel) {
    this.detailPagePanel = detailPagePanel;

    this.setLayout(new BorderLayout());

    JPanel panel = new JPanel();
    this.add(panel, BorderLayout.PAGE_START);
    panel.setBackground(Color.blue);

    JButton goBackbutton = new JButton("뒤로가기");
    goBackbutton.addActionListener(event -> {
      this.setVisible(false);
      detailPagePanel.setVisible(true);
    });
    panel.add(goBackbutton);

    JButton writeCommentButton = new JButton("댓글쓰기");
    writeCommentButton.addActionListener(event -> {
      showCommentPanel.setVisible(false);
      JPanel writeCommentPanel = new WriteCommentPanel(showCommentPanel);
      this.add(writeCommentPanel);
    });
    panel.add(writeCommentButton);

    showCommentPanel = new JPanel();
    this.add(showCommentPanel, BorderLayout.CENTER);
    showCommentPanel.setBackground(Color.green);
  }
}
