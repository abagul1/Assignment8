package cs3500.animator.view.provider.compositeview;

import javax.swing.Timer;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.view.provider.ReadOnlyAnimationModel;

/**
 * A interface for View of AnimationOperations: to display the animations and provide visual
 * interface for users.
 */
public interface AnimationView {

  /**
   * Makes the view of the animation visible to the user.
   *
   * @throws UnsupportedOperationException for SVG and text view
   */
  void makeVisible();

  /**
   * Refreshes the animation.
   *
   * @throws UnsupportedOperationException for SVG and text view
   */
  void refresh();

  /**
   * Builds up the animation view in text or svg format.
   *
   * @return Appendable that has to build up animation
   * @throws UnsupportedOperationException for Swing view
   */
  Appendable render();

  /**
   * Getter for the timer.
   *
   * @return the timer
   */
  Timer getTimer();

  /**
   * Sets the field isLoop.
   */
  void setIsLoop(boolean isLoop);

  /**
   * Gets the boolean isLoop.
   *
   * @return isLoop the boolean to get
   */
  boolean getIsLoop();

  /**
   * Gets the current tick in the view.
   *
   * @return tick which is the current tick
   */
  int getTick();

  /**
   * Resets the tick to the 0.
   */
  void reset();

  /**
   * Getter for the model.
   *
   * @return the ReadOnlyAnimationModel
   */
  ReadOnlyAnimationModel getModel();

  /**
   * Setter for the model.
   *
   * @param model the model to be set
   */
  void setModel(ReadOnlyAnimationModel model);

  /**
   * Setter for the timer.
   *
   * @param timer the wanted timer
   */
  void setTimer(Timer timer);

  /**
   * Method that takes in a controller to handle the actions that is desired.
   *
   * @param controller the controller to handle the actions
   */
  void doAction(AnimationController controller);
}

