package cs3500.animator.view.visualview;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import cs3500.IController;
import cs3500.IView;
import cs3500.ReadOnlyAnimation;


/**
 * Parent class for visual views and their respective decorator classes.
 */
public class VisualView extends JFrame implements IView {
  private ReadOnlyAnimation m;

  /**
   * Constructor for a visual view.
   * @param m read only animation
   */
  public VisualView(ReadOnlyAnimation m) {
    super();

    if (m == null) {
      JOptionPane.showMessageDialog(null, "Model cannot be null",
              "Error", JOptionPane.ERROR_MESSAGE);
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.m = m;

    AnimationPanel animationPanel = new AnimationPanel(m);
    this.setTitle("Animation Station");
    this.setSize(m.getWidth(), m.getHeight());
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JScrollPane scrollPane = new JScrollPane(animationPanel,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setPreferredSize(new Dimension(600, 600));
    this.add(scrollPane);
    this.makeVisible();
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void addClickListener(IController listener) {
    throw new UnsupportedOperationException("visual view doesn't use addClickListener");
  }

  @Override
  public void execute() {
    this.refresh();
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(m.getWidth(), m.getHeight());
  }

}
