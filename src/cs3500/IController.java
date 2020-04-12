package cs3500;

/**
 * Interface to represent a controller.
 */
public interface IController {

  /**
   * Creates a new view, and executes it.
   * @param a animation
   * @param viewType type of view
   * @param tempo speed if view
   */
  void playAnimation(IAnimation a, String viewType, int tempo);

  /**
   * Changes the speed of the animation.
   * @param type increment or decrement speed
   */
  void changeSpeed(String type);

  /**
   * Gets whether the view is paused or not.
   * @return true or false
   */
  boolean getPaused();

  /**
   * Set the paused state to the opposite of the current state.
   */
  void setPaused();

  /**
   * Get if the animation is looped or not.
   * @return true or false
   */
  boolean getLoop();

  /**
   * Set the loop state to the opposite of the current state.
   */
  void setLoop();

  /**
   * Get the speed of the animation.
   * @return speed
   */
  int getSpeed();

}
