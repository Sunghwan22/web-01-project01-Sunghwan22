package panels;

import models.Post;

import javax.swing.*;
import java.util.List;

public class DetailPagePanel extends JPanel {
  private final JTextField titleField;
  private final JTextArea contentArea;
  private Post post;
  private List<Post> posts;

//  public DetailPagePanel(Post post, JPanel contentPanel,
//                         JPanel mainPanel, List<Post> posts) {
//    this.post = post;
//    this.posts = posts;
//    this.setLayout(null);
//
//    JTextField titleField = new JTextField(20);
//    titleField.setBounds(50, 10, 600, 30);
//    titleField.setText(post.title());
//    titleField.setEditable(false);
//    this.add(titleField);
//
//    JTextArea contentArea = new JTextArea();
//    contentArea.setBounds(50, 50, 600, 550);
//    contentArea.setText(post.content());
//    contentArea.setEditable(false);
//    contentArea.setLineWrap(true);
//    this.add(contentArea);
//
//    JButton deleteButton = new JButton("삭제하기");
//    deleteButton.addActionListener(event -> {
//      post.delete();
//      this.setVisible(false);
//
//      contentPanel.removeAll();
//
//      //todo for문이 적용이 안됨 삭제하기 버튼 미구현
//      for(Post title : posts){
//        if(!title.state().equals(Post.DELETION)){
//          JLabel label = new JLabel(post.title());
//          contentPanel.add(label);
//        }
//      }
//      contentPanel.setVisible(false);
//      contentPanel.setVisible(true);
//      mainPanel.setVisible(true);
//    });
//    this.add(deleteButton);
//    deleteButton.setBounds(550, 605, 100, 50);
//
//    JButton modifyButton = new JButton("수정하기");
//    modifyButton.addActionListener(event -> {
//      titleField.setEditable(true);
//      contentArea.setEditable(true);
//    });
//    this.add(modifyButton);
//    modifyButton.setBounds(50, 605, 100, 50);
//
//    JButton modifyCompleteButton = new JButton("수정완료");
//    modifyCompleteButton.addActionListener(event -> {
//      post.modifyTitle(titleField.getText());
//      post.modifyContent(contentArea.getText());
//      //todo 글 내용은 수정이 되지만 content패널에서 제목은 바뀌지 않음
//      this.setVisible(false);
//      mainPanel.setVisible(true);
//      contentPanel.setVisible(true);
//    });
//    this.add(modifyCompleteButton);
//    modifyCompleteButton.setBounds(155, 605, 100, 50);
//  }

  public DetailPagePanel(List<Post> posts, Post post, JPanel contentPanel, JPanel mainPanel) {
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
      // 아 근데 이건 제목만 나오잖아 그러면 post가 만들어질때 인덱스 번호도 같이 생성이 되면 remove에 해당번호에 해당하는 녀석이 삭제된다.

//      for (Post title : posts)
//        if (!title.state().equals(Post.DELETION)) {
//          JLabel titleLabel = new JLabel(title.title());
//          contentPanel.add(titleLabel);
//
//        }
//      }

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

      for (Post title : posts) {
        if (!title.state().equals(Post.DELETION)) {
          JLabel titleLabel = new JLabel(title.title());
          contentPanel.add(titleLabel);
        }
      }

      this.setVisible(false);
      contentPanel.setVisible(false);
      contentPanel.setVisible(true);
      mainPanel.setVisible(true);
    });
    this.add(modifyCompleteButton);
    modifyCompleteButton.setBounds(155, 605, 100, 50);
  }
}
