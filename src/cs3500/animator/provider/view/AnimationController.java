package cs3500.animator.provider.view;


/**
 * An interface representing a controller whose purpose is to mediate the interactions between the
 * AnimationView and the AnimationModel.
 */
public interface AnimationController {

  /**
   * Deletes a shape from the Animation.
   *
   * @param name the name of the shape
   */
  void deleteShape(String name);

  /**
   * Deletes a specific keyframe for a given shape from the animation.
   *
   * @param name the name of the shape
   * @param time the time of the keyframe
   */
  void deleteFrame(String name, int time);

  /**
   * Adds a shape into the Animation, so that it exists but doesn't yet have properties.
   *
   * @param name the name of the shape
   * @param type the type of the shape
   */
  void addShape(String name, String type);

  /**
   * Adds a keyframe into the Animation for a shape that exists, does not include keyframes that are
   * in-between existing keyframes.
   *
   * @param name   the name of the shape
   * @param time   the time of the keyframe
   * @param pos    the wanted position of the shape
   * @param color  the wanted color of the shape
   * @param height the wanted height of the shape
   * @param width  the wanted width of the shape
   */
  void addFrame(String name, int time, Posn2D pos, ShapeColor color, int height, int width);

  /**
   * Modifies a shape's keyframe which are already existing or is in-between existing keyframes.
   *
   * @param name   the name of the shape
   * @param time   the time of the keyframe
   * @param pos    the wanted position of the shape
   * @param color  the wanted color of the shape
   * @param height the wanted height of the shape
   * @param width  the wanted width of the shape
   */
  void modifyFrame(String name, int time, Posn2D pos, ShapeColor color, int height, int width);

  /**
   * Play the animation.
   */
  void playAnimation();

  /**
   * Speeds up the animation.
   */
  void faster();

  /**
   * Slows down the animation.
   */
  void slower();

  /**
   * Rewinds the animation to the beginning.
   */
  void restart();

  /**
   * Play the animation on repeat.
   */
  void loop();

  /**
   * Pause the animation at a specific tick.
   */
  void pause();

  /**
   * Resume the animation at the current tick.
   */
  void resume();

  /**
   * Save the animation for txt or svg format.
   *
   * @param fileName the file name that user want to save the file
   * @throws java.io.IOException      the file write is failed to write the file
   * @throws IllegalArgumentException if fileName is null or empty string
   */
  void save(String fileName);

  /**
   * Load the file for playing the animation.
   *
   * @param fileName the file name that user want to load and play
   * @throws java.io.FileNotFoundException if the file does not exist
   * @throws IllegalArgumentException      if fileName is null or empty string
   */
  void load(String fileName);
}