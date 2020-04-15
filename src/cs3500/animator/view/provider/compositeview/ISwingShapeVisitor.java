package cs3500.animator.view.provider.compositeview;


import cs3500.animator.view.provider.AnimationShape;
import cs3500.animator.view.provider.EllipseShape;
import cs3500.animator.view.provider.RectangleShape;


/**
 * Represents an interface for a Visitor for Swing view, handles visits to the shapes in Swing
 * view.
 */
public interface ISwingShapeVisitor {

  /**
   * Visitor for Rectangle shape, storing the view output for the shape.
   *
   * @param r the RectangleShape that is being visited
   */
  void visitorRectangle(RectangleShape r);

  /**
   * Visitor for Ellipse shape, storing the view output for the shape.
   *
   * @param e the EllipseShape that is being visited
   */
  void visitorEllipse(EllipseShape e);

  /**
   * Applies the methods to the given AnimationShape, depending on what type of shape it is.
   *
   * @param s the Animation shape that is being applied to
   */
  void apply(AnimationShape s);
}
