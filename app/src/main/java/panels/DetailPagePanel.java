package panels;

import models.Post;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class DetailPagePanel extends JPanel {
  private final JTextField titleField;
  private final JTextArea contentArea;

  public DetailPagePanel(List<Post> posts, Post post,
                         JPanel contentPanel, JPanel mainPanel, JFrame frame) {
    this.setLayout(null);

    titleField = new JTextField(20);
    titleField.setBounds(50, 10, 600, 30);
    titleField.setText(post.title());
    titleField.setEditable(false);
    this.add(titleField);

    contentArea = new JTextArea();
    contentArea.setBounds(50, 50, 600, 550);
    contentArea.setText(post.content());
    contentArea.setEditable(false);
    contentArea.setLineWrap(true);
    this.add(contentArea);

    JButton deleteButton = new JButton("삭제하기");
    deleteButton.addActionListener(event -> {
      post.delete();

      contentPanel.removeAll();

      for (Post title : posts) {
        if (!(title.state().equals(Post.DELETION))) {
          JLabel titleLabel = new JLabel(title.title());
          contentPanel.add(titleLabel);

          titleLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
              DetailPagePanel detailPagePanel = new DetailPagePanel(posts, post,
                  contentPanel, mainPanel, frame);

              frame.add(detailPagePanel);
              contentPanel.setVisible(false);
              mainPanel.setVisible(false);
              detailPagePanel.setVisible(false);
              detailPagePanel.setVisible(true);
            }
          });
        }
      }
      this.setVisible(false);
      contentPanel.setVisible(true);
      mainPanel.setVisible(true);
    });
    this.add(deleteButton);
    deleteButton.setBounds(550, 605, 100, 50);

    JButton modifyButton = new JButton("수정하기");
    modifyButton.addActionListener(event -> {
      titleField.setEditable(true);
      contentArea.setEditable(true);
    });


    JButton modifyCompleteButton = new JButton("수정완료");
    modifyCompleteButton.addActionListener(event -> {
      post.modifyTitle(titleField.getText());
      post.modifyContent(contentArea.getText());


      this.setVisible(false);
      contentPanel.setVisible(false);
      contentPanel.setVisible(true);

    });
    this.add(modifyButton);
    modifyButton.setBounds(50, 605, 100, 50);
    this.add(modifyCompleteButton);
    modifyCompleteButton.setBounds(155, 605, 100, 50);
  }
}
