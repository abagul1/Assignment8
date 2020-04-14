package cs3500.animator.view.provider;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;


/**
 * An interface representing a read-only version of the AnimationModel.
 */
public interface ReadOnlyAnimationModel {

  /**
   * Returns the state of the entire Animation in String.
   *
   * @return state of Animation, with each AnimationShape's description of their actions, size,
   *          color, and the ticks of movement. If animation does not have any shape,
   *          returns empty string, if shape doesn't have movement, return only shape type
   */
  String getAnimation();

  /**
   * Returns the state of a specific shape in the Animation at a specific time.
   *
   * @param time the time of the shape that is wanted
   * @param name the name of the shape that is being looked for
   * @return the shape at a given time
   * @throws IllegalArgumentException if the time is negative or out of range for the Animation of
   *                                  shape, and if the given name for the shape doesn't exist in
   *                                  the list
   */
  AnimationShape getShape(int time, String name);

  /**
   * Returns the list of shape at specific time in the animation.
   *
   * @param time the time
   * @return the list of shape at specific time, if the shape does not exist at the given time,
   *          return empty list
   * @throws IllegalArgumentException if the time is less than one, throw error
   */
  List<AnimationShape> getShape(int time);

  /**
   * Gets a copy of the Animation background.
   *
   * @return the AnimationBackground
   */
  AnimationBackground getBackground();

  /**
   * Gets a copy of the map of existing shapes in the Animation.
   *
   * @return the existing shape map
   */
  LinkedHashMap<String, String> getExistingShape();

  /**
   * Gets a copy of the map of the timeline in the Animation.
   *
   * @return the timeline of the Animation
   */
  HashMap<String, TreeMap<Integer, AnimationShape>> getTimeline();
}
