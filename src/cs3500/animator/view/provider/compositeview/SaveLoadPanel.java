package cs3500.animator.view.provider.compositeview;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;


import cs3500.animator.controller.AnimationController;

/**
 * A SaveLoadPanel class which allows the user to save and load animations.
 */
public class SaveLoadPanel extends JPanel {

  private JTextField fileNameText;
  private JButton save;
  private JButton load;

  /**
   * A constructor for SaveLoadPanel, doesn't take anything in, sets the initial buttons.
   */
  public SaveLoadPanel() {
    JFrame frame = new JFrame();
    JLabel fileName = new JLabel("File Name: ");
    fileNameText = new JTextField(10);
    fileName.setLabelFor(fileNameText);
    save = new JButton("Save");
    load = new JButton("Load");

    JPanel panel = new JPanel();
    panel.add(fileName);
    panel.add(fileNameText);
    panel.add(save);
    panel.add(load);
    frame.add(panel, BorderLayout.WEST);
    frame.setLocation(0, 50);
    frame.setSize(350, 80);
    frame.setVisible(true);
  }

  /**
   * Takes in the controller to do the corresponding act according to the button pressed by the
   * user.
   *
   * @param listener The controller that will handle the actions
   */
  public void addClickListener(AnimationController listener) {
    save.addActionListener(e -> listener.save(fileNameText.getText()));
    load.addActionListener(e -> listener.load(fileNameText.getText()));
  }

}
