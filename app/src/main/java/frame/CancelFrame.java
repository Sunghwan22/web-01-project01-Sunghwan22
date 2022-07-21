package frame;

import panels.WriteGoalPanel;

import javax.swing.*;

public class CancelFrame extends JFrame {
  private WriteGoalPanel writeGoalPanel;
  private JPanel mainPanel;
  private JPanel menuPanel;

  public CancelFrame(WriteGoalPanel writeGoalPanel,
                     JPanel mainPanel, JPanel menuPanel) {
    this.writeGoalPanel = writeGoalPanel;
    this.mainPanel = mainPanel;
    this.menuPanel = menuPanel;

    this.setName("글쓰기");
    this.setSize(300,200);
    this.setLocation(350,350);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    JLabel label = new JLabel("글 작성을 취소하시겠습니까?");

    JPanel panel = new JPanel();
    this.add(panel);
    panel.add(label);
    panel.setLayout(null);
    label.setBounds(80,30,200,30);

    JButton confirmButton = new JButton("확인");
    confirmButton.addActionListener(event -> {
        this.setVisible(false);
        writeGoalPanel.setVisible(false);
        mainPanel.setVisible(true);
        menuPanel.setVisible(true);

    });
    panel.add(confirmButton);
    confirmButton.setBounds(40,70,100,50);
    this.setVisible(true);

    JButton cancelButton = new JButton("취소");
    cancelButton.addActionListener(event -> {
      this.setVisible(false);
    });
    panel.add(cancelButton);
    cancelButton.setBounds(145,70,100,50);
  }
}
