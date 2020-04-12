package cs3500.animator.view;

import cs3500.animator.controller.AnimationController;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * A ButtonPanel class which represents the buttons that a user can click to manipulate the
 * Animation, mostly to do with the Timer and speed.
 */
public class ButtonPanel extends JPanel {

  private JButton start;
  private JButton pause;
  private JButton restart;
  private JButton speedUp;
  private JButton speedDown;
  private JButton loop;

  /**
   * A constructor for ButtonPanel, doesn't take in anything, sets the buttons.
   */
  public ButtonPanel() {
    start = new JButton("\u25B6");
    pause = new JButton("Ⅱ");
    restart = new JButton("⟳");
    speedUp = new JButton(">>");
    speedDown = new JButton("<<");
    loop = new JButton("∞");

    JFrame frame = new JFrame();
    Box box = Box.createVerticalBox();
    box.add(start);
    box.add(pause);
    box.add(restart);
    box.add(speedUp);
    box.add(speedDown);
    box.add(loop);
    frame.add(box, BorderLayout.CENTER);
    frame.setSize(80, 175);
    frame.setUndecorated(true);
    frame.setLocation(70, 225);
    frame.setVisible(true);
  }

  /**
   * Takes in the controller to do the corresponding act according to the button pressed by the
   * user.
   *
   * @param listener The controller that will handle the actions
   */
  public void addClickListener(AnimationController listener) {
    start.addActionListener(e -> listener.resume());
    pause.addActionListener(e -> listener.pause());
    restart.addActionListener(e -> listener.restart());
    speedUp.addActionListener(e -> listener.faster());
    speedDown.addActionListener(e -> {
      try {
        listener.slower();
      } catch (IllegalArgumentException ex) {
        JFrame error = new JFrame();
        JOptionPane.showMessageDialog(error,
            ex.getMessage(),
            "Illegal Argument",
            JOptionPane.ERROR_MESSAGE);
      }
    });
    loop.addActionListener(e -> listener.loop());
  }
}

