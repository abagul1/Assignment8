package cs3500.animator.provider.view;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;



/**
 * The interface of the Animation model, given all the possible actions for an Animation.
 */
public interface AnimationOperations extends ReadOnlyAnimationModel {

  /**
   * Declares an Animation shape in an Animation, adds it to the existing shape list of the
   * Animation.
   *
   * @param name the name that is given to the shape
   * @param type the type of shape
   * @throws IllegalArgumentException if the name already exists in the Animation of if the name is
   *                                  invalid (null or empty string), or if the type is a null or an
   *                                  invalid shape type
   */
  void declareShape(String name, String type);

  /**
   * Adds an AnimationShape into the list of shapes.
   *
   * @param newShape the AnimationShape that is to be added
   * @param name     the name that is given to the shape being added
   * @param time     the tick that the shape is being added into the Animation
   * @throws IllegalArgumentException if the shape is invalid because it doesn't have the correct
   *                                  elements of an AnimationShape or if the time is negative or
   *                                  out of bounds for the Animation or if the name already exists
   *                                  in the Animation or if the name is invalid (null or empty
   *                                  string)
   */
  void addShape(AnimationShape newShape, String name, int time);

  /**
   * Removes the AnimationShape from the Animation entirely.
   *
   * @param name the name of the shape that is being removed
   * @throws IllegalArgumentException if the name doesn't exist in the Animation or the given name
   *                                  is invalid (null or empty string)
   */
  void removeShape(String name);

  /**
   * Makes a certain shape in the Animation move in a certain direction and speed at a certain time
   * frame.
   *
   * @param name         the name of the shape that is being called to move
   * @param startingTime the tick that the move should begin
   * @param endingTime   the tick that the move should end
   * @param newX         the x position when the shape is at the endingTime
   * @param newY         the y position when the shape is at the endingTime
   * @throws IllegalArgumentException if the name doesn't exist in the Animation, or the name is
   *                                  invalid(null or empty string), or the time is less than one,
   *                                  or starting time is greater or equal to the ending time, or
   *                                  starting time is less than the certain shape that is exist
   */
  void move(String name, int startingTime, int endingTime, int newX, int newY);

  /**
   * Changes the color of a certain shape in the Animation at a certain time.
   *
   * @param name         the name of the shape that is being changed
   * @param startingTime the tick that the shape should start to change color
   * @param color        the ShapeColor that the shape should change to
   * @param endingTime   the tick that the shape should stop to change color
   * @throws IllegalArgumentException if the name doesn't exist in the Animation, or the name is
   *                                  invalid(null or empty string), or the time is less than one,
   *                                  or starting time is greater or equal to the ending time, or
   *                                  starting time is less than the certain shape that is exist
   */
  void changeColor(String name, int startingTime, ShapeColor color, int endingTime);

  /**
   * Changes the size of a certain shape in the Animation at a certain time.
   *
   * @param name         the name of the shape that is being changed
   * @param startingTime the tick that the shape should start to change size
   * @param height       the height of the shape
   * @param width        the width of the shape
   * @param endingTime   the tick that the shape should stop to change size
   * @throws IllegalArgumentException if the name doesn't exist in the Animation, or the name is
   *                                  invalid(null or empty string), or the time is less than one,
   *                                  or starting time is greater or equal to the ending time, or
   *                                  starting time is less than the certain shape that is exist
   */
  void changeSize(String name, int startingTime, int height, int width, int endingTime);

  /**
   * Sets the background of the Animation to specific position and size.
   *
   * @param x      the x-position of the background
   * @param y      the y-position of the background
   * @param width  the width of the background
   * @param height the height of the background
   */
  void setBackground(int x, int y, int width, int height);

  /**
   * Sets the map of the existing shapes in the Animation with given map.
   *
   * @param shapes the map of existing shapes for the Animation
   */
  void setExistingShape(LinkedHashMap<String, String> shapes);

  /**
   * Sets the map of the timeline of the Animation to the given map.
   *
   * @param timeline the map of the timeline for the Animation
   */
  void setTimeline(HashMap<String, TreeMap<Integer, AnimationShape>> timeline);

  /**
   * Adds the keyFrame of the specific shape and time.
   *
   * @param name     the name of shape that is added in the frame
   * @param time     the time of the shape that should be the key frame
   * @param newShape the shape that is added
   * @throws IllegalArgumentException if the name ,time, or shape are invalid or if the time is
   *                                  already existing for the key frame
   */
  void addFrame(String name, int time, AnimationShape newShape);

  /**
   * Removes the keyFrame of the specific shape and time.
   *
   * @param name the name of the shape that is being removed
   * @param time the time of the shape that is being removed
   * @throws IllegalArgumentException if the name and time is invalid or the name and time is not
   *                                  exist
   */
  void removeFrame(String name, int time);

  /**
   * Modify the keyFrame of the specific shape and time.
   *
   * @param name     the name of the shape that is being modified
   * @param time     the time of the shape that is being modified
   * @param newShape the new shape that is modified
   * @throws IllegalArgumentException if the name and time is invalid or the name and time is not
   *                                  exist or if the shape is invalid
   */
  void modifyFrame(String name, int time, AnimationShape newShape);

  /**
   * Make the valid shape if user give the name, pos, color, height and width.
   *
   * @param name   the name of the shape that user want to make
   * @param pos    the position of the shape that user want to make
   * @param color  the color of the shape that user want to make
   * @param height the height of the shape that user want to make
   * @param width  the width of the shape that user want to make
   * @return AnimationShape that user want to make by using the properties
   * @throws IllegalArgumentException if the given parameters are invalid (eg, name, pos color is
   *                                  null or size is less or equal to zero etc)
   */
  AnimationShape makeShape(String name, Posn2D pos, ShapeColor color, int height, int width);
}
