package cs3500;

import java.util.List;
import java.util.Map;

import cs3500.motions.Motion;

/**
 * Provides an interface with read only methods.
 */
public interface ReadOnlyAnimation {

  /**
   * Gets an element with the given id.
   * @param id id of element to return
   * @return an element from the animation
   */
  IElement getElement(String id);

  /**
   * Gets a list of all the elements in the animation.
   * @return a list of elements
   */
  Map<String, IElement> getElements();

  /**
   * Gets the list of motions associated with the given element.
   * @param id specific id of the element
   * @return a list of operations
   */
  List<String> getMotionsForElement(String id);

  /**
   * Converts all the moves into textual descriptions of the entire
   * animation. The motions are grouped by element.
   * @return a textual description of the animation
   */
  String getVerboseAnimation();

  /**
   * Gets the height of the animation window.
   * @return the height
   */
  int getHeight();

  /**
   * Gets the width of the animation window.
   * @return the width
   */
  int getWidth();

  /**
   * gets the x starting point.
   * @return starting x.
   */
  int getX();

  /**
   * gets the y starting point.
   * @return starting y.
   */
  int getY();

  /**
   * Increments model state by one tick.
   */
  void executeOneTick();

  /**
   * Gets all the shapes in the animation.
   * @return array of shape names
   */
  String[] getShapes();

  /**
   * Gets a list of key frames associated with the given shape.
   * @param id id of shape
   * @return a list of key frames
   */
  List<Motion> getKeyFrame(String id);

}
