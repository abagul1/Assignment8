package cs3500.animator.provider.view;


import cs3500.animator.provider.view.compositeview.ISVGShapeVisitor;
import cs3500.animator.provider.view.compositeview.ISwingShapeVisitor;

/**
 * The interface for shapes in an Animation, includes all the actions available for AnimationShape.
 */
public interface AnimationShape {

  /**
   * Gets the AnimationShape at a specific time.. i.
   *
   * @param time the time of the shape that is wanted
   * @return the AnimationShape at the given time
   * @throws IllegalStateException if time is negative
   */
  AnimationShape showShape(int time);

  /**
   * Gets the Posn2D position of the AnimationShape.
   *
   * @return the Posn2D position
   */
  Posn2D getPosition();

  /**
   * Gets the ShapeColor of the AnimationShape.
   *
   * @return the ShapeColor
   */
  ShapeColor getColor();

  /**
   * Gets the height of the AnimationShape.
   *
   * @return the height
   */
  int getHeight();

  /**
   * Gets the width of the AnimationShape.
   *
   * @return the width
   */
  int getWidth();

  /**
   * Sets the position of the AnimationShape.
   *
   * @param x the x position to be set
   * @param y the y position to be set
   * @throws IllegalStateException if the given parameter is negative
   */
  void setPosition(int x, int y);

  /**
   * Sets the ShapeColor of the AnimationShape.
   *
   * @param r the red value of the ShapeColor
   * @param g the green value of the ShapeColor
   * @param b the blue value of the ShapeColor
   * @throws IllegalStateException if the given parameter is negative
   */
  void setColor(int r, int g, int b);

  /**
   * Sets the size of the AnimationShape.
   *
   * @param h the height
   * @param w the width
   * @throws IllegalArgumentException if given height and width is less or equal to zero
   */
  void setSize(int h, int w);

  /**
   * Gets the change in X position.
   *
   * @return the change in x
   */
  float getDeltaX();

  /**
   * Gets the change in Y position.
   *
   * @return the change in y
   */
  float getDeltaY();

  /**
   * Gets the change in red color in ShapeColor.
   *
   * @return the change in red
   */
  float getDeltaR();

  /**
   * Gets the change in green color in ShapeColor.
   *
   * @return the change in green
   */
  float getDeltaG();

  /**
   * Gets the change in blue color in ShapeColor.
   *
   * @return the change in blue
   */
  float getDeltaB();

  /**
   * Gets the change in height.
   *
   * @return the change in height
   */
  float getDeltaHeight();

  /**
   * Gets the change in width.
   *
   * @return the change in width
   */
  float getDeltaWidth();

  /**
   * Sets the change in Posn2D of the AnimationShape.
   *
   * @param deltaX the change in x to be set
   * @param deltaY the change in y to be set
   */
  void setDeltaPosition(float deltaX, float deltaY);

  /**
   * Sets the change in ShapeColor of the AnimationShape.
   *
   * @param deltaR the change in red to be set
   * @param deltaG the change in green to be set
   * @param deltaB the change in blue to be set
   */
  void setDeltaColor(float deltaR, float deltaG, float deltaB);

  /**
   * Sets the change in height of the AnimationShape.
   *
   * @param deltaHeight the change in height to be set
   */
  void setDeltaHeight(float deltaHeight);

  /**
   * Sets the change in width of the AnimationShape.
   *
   * @param deltaWidth the change in width to be set
   */
  void setDeltaWidth(float deltaWidth);

  /**
   * Gets the type of shape that the AnimationShape is, in string.
   *
   * @return the string of the type of shape
   */
  String getType();

  /**
   * Accepts an ISwingShapeVisitor method, and performs the method of the visitor.
   *
   * @param visitor the ISwingShapeVisitor that is being accepted
   */
  void accept(ISwingShapeVisitor visitor);

  /**
   * Accepts an ISVGShapeVisitor method, and performs the method of the visitor.
   *
   * @param visitor the ISVGShapeVisitor that is being accepted
   * @return the String output of the visitor method
   */
  String acceptSVG(ISVGShapeVisitor visitor);
}