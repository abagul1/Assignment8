package cs3500.animator.view;

import cs3500.animator.model.AnimationShape;
import cs3500.animator.model.EllipseShape;
import cs3500.animator.model.RectangleShape;

/**
 * An implementation of the SVGShapeVisitor, which handles the visitor methods.
 */
public class SVGShapeVisitor implements ISVGShapeVisitor {
  private String name;


  /**
   * A constructor for the SVGShapeVisitor.
   *
   * @param name the name of the AnimationShape that is being visited.
   */
  public SVGShapeVisitor(String name) {
    this.name = name;
  }

  @Override
  public String visitorRectangle(RectangleShape r) {
    return "\n<rect" + makeTag("id", name) + makeTag("x", r.getPosition().getX())
            + makeTag("y", r.getPosition().getY()) + makeTag("width", r.getWidth())
            + makeTag("height", r.getHeight()) + makeTag("fill", r.getColor().svgString())
            + makeTag("visibility", "visible") + " >\n";
  }

  @Override
  public String visitorEllipse(EllipseShape e) {
    return "\n<ellipse" + makeTag("id", name) + makeTag("cx", e.getPosition().getX())
            + makeTag("cy", e.getPosition().getY()) + makeTag("rx", (e.getWidth() / 2))
            + makeTag("ry", (e.getHeight() / 2))
            + makeTag("fill", e.getColor().svgString())
            + makeTag("visibility", "visible") + " >\n";
  }

  @Override
  public String apply(AnimationShape s) {
    return s.acceptSVG(this);
  }

  /**
   * make the tag for svg format string.
   *
   * @param tag   the tag which is an attribute of the shape in svg
   * @param value the value of an attribute of the shape in svg
   * @return the string format for svg view
   */
  private String makeTag(String tag, String value) {
    return String.format(" %s=\"%s\"", tag, value);
  }

  /**
   * make the tag for svg format string.
   *
   * @param tag   the tag which is an attribute of the shape in svg
   * @param value the value of an attribute of the shape in svg
   * @return the string format for svg view
   */
  private String makeTag(String tag, int value) {
    return makeTag(tag, Integer.toString(value));
  }
}
