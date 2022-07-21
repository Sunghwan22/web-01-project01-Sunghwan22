package panels;

import javax.swing.*;
import java.awt.*;

public class FrameBackGroundPanel extends JPanel {
  private final Image image;

  public FrameBackGroundPanel(Image image) {
    this.image = image;
    setSize(new Dimension(image.getWidth(null),image.getHeight(null)));
    setPreferredSize(new Dimension(image.getWidth(null),image.getHeight(null)));
    setLayout(null);
  }
  public void paintComponent(Graphics graphics){
    graphics.drawImage(image,0,0,null);
  }
}
