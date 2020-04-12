package cs3500;

import java.awt.Color;
import java.awt.Graphics2D;

import cs3500.elements.Posn;

/**
 * Interface for an element.
 * An element is an entity that is capable of being represented visually.
 * This means that it possesses all of the information necessary to represent itself within a frame.
 */
public interface IElement {

  /**
   * Get the color of this element.
   * Imported from {@code java.awt.Color}.
   * A Color is in RGBA format, meaning it possesses a red, green, blue, and alpha component.
   * @return  the element's color.
   */
  Color getColor();

  /**
   * Get the position of this element.
   * See {@code Posn} for a description of a Posn.
   * @return  the position of the element.
   */
  Posn getPosn();

  /**
   * Get the ID of the element.
   * The ID of an element is a String that identifies the element. This allows the element to be
   * referenced from within a data structure such as a Map.
   * Once an element is created, its ID cannot be changed.
   * @return  the ID.
   */
  String getID();

  /**
   * Get the dimensions of this element.
   * The number of arguments within the array is variable, based on the implementation of the
   * specific element.
   * Specific element implementations must document the order that significant dimensions appear
   * in.
   * @return  the number of significant dimensions needed to describe an object
   */
  double[] getDimensions();

  /**
   * Sets the color of an element to the specified color.
   * As Color is in RGBA format, this can be used to alter the alpha value as well as the actual
   * color. See {@code java.awt.Color} for different color constructors, as well as various
   * enumerated colors.
   * @param c  the color to change the element to.
   */
  void setColor(Color c);

  /**
   * Sets the position of an element.
   * As a {@code Posn} is immutable, a new one must be constructed whenever the position of an
   * element must be changed.
   * @param p  the new position of the element.
   */
  void setPosn(Posn p);


  /**
   * Set the width of the element.
   * @param w new width of element
   */
  void setWidth(double w);

  /**
   * Set the height of the element.
   * @param h new height of element
   */
  void setHeight(double h);

  /**
   * Returns string of the type of shape created.
   * @return string of type of shape
   */
  String getType();

  /**
   * Paint the element onto the given 2D graphics object.
   * @param g2d  the 2D graphics object
   */
  void paint(Graphics2D g2d);
}
