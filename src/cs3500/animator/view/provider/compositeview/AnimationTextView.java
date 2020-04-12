package cs3500.animator.view.provider.compositeview;

import java.io.IOException;

import javax.swing.Timer;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.view.provider.ReadOnlyAnimationModel;

/**
 * An implementation of the AnimationView that renders the Animation in an text format.
 */
public class AnimationTextView implements AnimationView {

  private final ReadOnlyAnimationModel model;
  private final Appendable ap;

  /**
   * A constructor for the text view of the Animation.
   *
   * @param model the read-only model that will be viewed
   * @param ap    the appendable used to build up the view of the Animation
   */
  public AnimationTextView(ReadOnlyAnimationModel model, Appendable ap) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }
    if (ap == null) {
      throw new IllegalArgumentException("Appendable cannot be null.");
    }
    this.model = model;
    this.ap = ap;
  }

  @Override
  public void makeVisible() {
    throw new UnsupportedOperationException("Not supported by text view");
  }

  @Override
  public void refresh() {
    // Text view does not need this method. Therefore, we have two options
    // first, do nothing. Second throw error. We decide to do nothing in this method
    throw new UnsupportedOperationException("Not supported by text view");
  }

  @Override
  public Appendable render() {
    try {
      ap.append(model.getAnimation());
      return ap;
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed.", ioe);
    }
  }

  @Override
  public Timer getTimer() {
    throw new UnsupportedOperationException("Not supported by text view");
  }

  @Override
  public void setIsLoop(boolean isLoop) {
    throw new UnsupportedOperationException("Not supported by text view");
  }

  @Override
  public boolean getIsLoop() {
    throw new UnsupportedOperationException("Not supported by text view");
  }

  @Override
  public int getTick() {
    throw new UnsupportedOperationException("Not supported by text view");
  }

  @Override
  public void reset() {
    throw new UnsupportedOperationException("Not supported by text view");
  }

  @Override
  public ReadOnlyAnimationModel getModel() {
    throw new UnsupportedOperationException("Not supported by text view");
  }

  @Override
  public void setModel(ReadOnlyAnimationModel model) {
    throw new UnsupportedOperationException("Not supported by text view");
  }

  @Override
  public void setTimer(Timer timer) {
    throw new UnsupportedOperationException("Not supported by text view");
  }

  @Override
  public void doAction(AnimationController controller) {
    throw new UnsupportedOperationException("Not supported by text view");
  }
}
