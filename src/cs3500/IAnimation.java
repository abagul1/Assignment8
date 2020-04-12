package cs3500;

import cs3500.motions.Motion;

/**
 * Interface for an Animation model within an M-V-C framework.
 * An animation consists of a collection of visual elements, where at least one element has its
 * properties translated over the running time of the animation. If no elements have their
 * properties translated over the running time of the animation, then the animation may be reduced
 * to a single frame. Alternatively, an animation may be viewed as a list of temporally-linked
 * frames. Each tick,the model calculates and redraws the necessary incremental translation for each
 * element. In this manner, elements can appear to instantly shift if the number of ticks specified
 * for the translation is zero, or they will gradually change over the specified number of ticks.
 * The model may be asked to return a frame given a number of ticks, and the model will perform
 * incremental translations of elements up to that point.
 */
public interface IAnimation extends ReadOnlyAnimation {

  /**
   * Converts a motion operation into individual motions per tick,
   * allows the animation to increment the position, dimensions, and color in the motion as a factor
   * of the number of ticks the motion lasts for. Also converts the motion into
   * a verbose text output.
   * @param name id of element
   * @param t1 starting tick
   * @param x1 starting x position
   * @param y1 starting y position
   * @param w1 starting width
   * @param h1 starting height
   * @param r1 starting red value
   * @param g1 starting green value
   * @param b1 starting blue value
   * @param t2 ending tick
   * @param x2 ending x position
   * @param y2 ending y position
   * @param w2 ending width
   * @param h2 ending height
   * @param r2 ending red value
   * @param g2 ending green value
   * @param b2 ending blue value
   */
  void motion(String name,
              int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1,
              int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2);

  /**
   * Inserts an element into the animation at a certain tick.
   * @param name element id
   * @param type type of element
   */
  void insertElement(String name, String type);

  void deleteElement(String name);

  /**
   * Executes the animations by running all the operations at the given ticks.
   */
  void executeOperations();

  /**
   * Executes the animation up till a certain tick.
   * @param tick tick at which to stop executing
   */
  void executeOperationsUntil(int tick);

  /**
   * Resets the animation.
   */
  void resetAnimation();

  /**
   * Insert a key frame for a shape.
   * @param name shape
   * @param tick tick of keyframe
   * @param m keyframe object
   */
  void insertKeyFrame(String name, int tick, Motion m);

  /**
   * Delete a key frame.
   * @param name of shape
   * @param tick tick of keyframe
   */
  void deleteKeyFrame(String name, int tick);

  /**
   * Edit a key frame.
   * @param name of shape
   * @param tick of keyframe
   * @param m keyframe object
   */
  void editKeyFrame(String name, int tick, Motion m);

  /**
   * Checks if the animation is complete.
   * @param tick current tick of animation
   * @return true or false
   */
  boolean isDone(int tick);

}
