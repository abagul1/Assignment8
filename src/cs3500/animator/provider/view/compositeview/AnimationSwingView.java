package cs3500.animator.provider.view.compositeview;

import javax.swing.JFrame;
import javax.swing.Timer;

import cs3500.animator.provider.view.AnimationController;
import cs3500.animator.provider.view.ReadOnlyAnimationModel;

/**
 * An implementation of the AnimationView that renders the Animation in an Swing format.
 */
public class AnimationSwingView extends JFrame implements AnimationView {

  private int tick = 0;
  private AnimationPanel panel;

  /**
   * A constructor for the Swing view of the Animation.
   *
   * @param windowTitle the title for the window
   * @param m           the read-only model to be viewed
   * @param speed       the speed of the Animation
   */
  public AnimationSwingView(String windowTitle, ReadOnlyAnimationModel m, int speed) {
    super(windowTitle);

    setSize(m.getBackground().getDimension());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    panel = new AnimationPanel(m, tick);
    this.add(panel);
    int timePerTick = 1000 / speed;
    Timer timer = new Timer(timePerTick, e -> refresh());
    timer.start();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void refresh() {
    tick++;
    panel.setTick(tick);
    this.repaint();
  }

  @Override
  public Appendable render() {
    //Does not apply to the swing/visual view, only the textual and SVG
    throw new UnsupportedOperationException("Not supported by Swing view");
  }

  @Override
  public Timer getTimer() {
    throw new UnsupportedOperationException("Not supported by Swing view");
  }

  @Override
  public void setIsLoop(boolean isLoop) {
    throw new UnsupportedOperationException("Not supported by Swing view");
  }

  @Override
  public boolean getIsLoop() {
    throw new UnsupportedOperationException("Not supported by Swing view");
  }

  @Override
  public int getTick() {
    throw new UnsupportedOperationException("Not supported by Swing view");
  }

  @Override
  public void reset() {
    throw new UnsupportedOperationException("Not supported by Swing view");
  }

  @Override
  public ReadOnlyAnimationModel getModel() {
    throw new UnsupportedOperationException("Not supported by Swing view");
  }

  @Override
  public void setModel(ReadOnlyAnimationModel model) {
    throw new UnsupportedOperationException("Not supported by Swing view");
  }

  @Override
  public void setTimer(Timer timer) {
    throw new UnsupportedOperationException("Not supported by Swing view");
  }

  @Override
  public void doAction(AnimationController controller) {
    throw new UnsupportedOperationException("Not supported by Swing view");
  }
}
