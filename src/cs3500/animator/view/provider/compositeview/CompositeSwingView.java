package cs3500.animator.view.provider.compositeview;

import static java.util.Collections.max;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.view.provider.ReadOnlyAnimationModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * A composite view for the Swing representation of an Animation, allows for more actions, such as
 * playing, pausing, and restarting.
 */
public class CompositeSwingView extends JFrame implements AnimationView {

  private AnimationPanel animationPanel;
  private ButtonPanel buttonPanel;
  private KeyFramePanel keyFramePanel;
  private SaveLoadPanel saveLoadPanel;
  private ReadOnlyAnimationModel model;

  private int tick = 0;
  private boolean isLoop = false;
  private Timer timer;

  /**
   * A constructor for the CompositeSwingView, which takes in a window title and a model.
   *
   * @param windowTitle the title for the window
   * @param model       the model that is to be displayed and manipulated in the Animation.
   */
  public CompositeSwingView(String windowTitle, ReadOnlyAnimationModel model) {
    super(windowTitle);
    this.model = model;

    setSize(model.getBackground().getDimension());
    setLocation(150, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    animationPanel = new AnimationPanel(model, tick);
    this.add(animationPanel);
    buttonPanel = new ButtonPanel();
    keyFramePanel = new KeyFramePanel();
    saveLoadPanel = new SaveLoadPanel();
  }

  @Override
  public void doAction(AnimationController listener) {
    buttonPanel.addClickListener(listener);
    keyFramePanel.addClickListener(listener);
    saveLoadPanel.addClickListener(listener);
  }

  @Override
  public void setTimer(Timer timer) {
    this.timer = timer;
  }

  @Override
  public Timer getTimer() {
    return this.timer;
  }

  @Override
  public void reset() {
    this.tick = 0;
  }

  @Override
  public void setIsLoop(boolean isLoop) {
    this.isLoop = isLoop;
  }

  @Override
  public boolean getIsLoop() {
    return isLoop;
  }

  @Override
  public int getTick() {
    return tick;
  }

  @Override
  public ReadOnlyAnimationModel getModel() {
    return this.model;
  }

  @Override
  public void setModel(ReadOnlyAnimationModel model) {
    this.model = model;
    this.reset();
    this.animationPanel.setModel(model);
    this.refresh();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void refresh() {
    if (isLoop) {
      tick++;
      tick = tick % maxTick();
    } else {
      tick++;
    }
    animationPanel.setTick(tick);
    this.repaint();
  }

  @Override
  public Appendable render() {
    //Does not apply to the swing/visual view, only the textual and SVG
    throw new UnsupportedOperationException("Not supported by Swing view");
  }

  /**
   * Calculates the last tick that exists in the model.
   *
   * @return the last tick
   */
  private int maxTick() {
    List<Integer> highest = new ArrayList<>();
    for (String string : model.getTimeline().keySet()) {
      int num = model.getTimeline().get(string).lastKey();
      highest.add(num);
    }
    return max(highest);
  }
}