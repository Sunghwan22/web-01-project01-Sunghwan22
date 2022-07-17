package panels;

import models.Post;

import javax.swing.*;

public class DetailPagePanel extends JPanel {
  public DetailPagePanel(Post post, JPanel contentPanel, JPanel mainPanel) {
    this.setLayout(null);

    JTextField titleField = new JTextField(20);
    titleField.setBounds(50, 10, 600, 30);
    titleField.setText(post.title());
    titleField.setEditable(false);
    this.add(titleField);

    JTextArea contentArea = new JTextArea();
    contentArea.setBounds(50, 50, 600, 550);
    contentArea.setText(post.content());
    contentArea.setEditable(false);
    contentArea.setLineWrap(true);
    this.add(contentArea);

    JButton deleteButton = new JButton("삭제하기");
    deleteButton.addActionListener(event -> {
      post.delete();
      this.setVisible(false);

      contentPanel.setVisible(false);
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
    this.add(modifyButton);
    modifyButton.setBounds(50, 605, 100, 50);

    JButton modifyCompleteButton = new JButton("수정완료");
    modifyCompleteButton.addActionListener(event -> {
      post.modifyTitle(titleField.getText());
      post.modifyContent(contentArea.getText());

      this.setVisible(false);
      mainPanel.setVisible(true);
      contentPanel.setVisible(true);
    });
    this.add(modifyCompleteButton);
    modifyCompleteButton.setBounds(155, 605, 100, 50);
  }
}
