package cs3500.animator.view.provider.compositeview;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.Timer;

import cs3500.animator.view.provider.AnimationController;
import cs3500.animator.view.provider.AnimationShape;
import cs3500.animator.view.provider.ReadOnlyAnimationModel;

/**
 * An implementation of the AnimationView that renders the Animation in an SVG format.
 */
public class AnimationSVGView implements AnimationView {

  private final ReadOnlyAnimationModel model;
  private final Appendable ap;
  private int speed;

  /**
   * A constructor for the SVG view of the Animation.
   *
   * @param model the read-only model that will be viewed
   * @param ap    an appendable that will be used to build up the view
   * @param speed the speed of the animation movement
   */
  public AnimationSVGView(ReadOnlyAnimationModel model, Appendable ap, int speed) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }
    if (ap == null) {
      throw new IllegalArgumentException("Appendable cannot be null.");
    }
    if (speed < 1) {
      throw new IllegalArgumentException("Speed cannot be less than one.");
    }
    this.model = model;
    this.ap = ap;
    this.speed = 1000 / speed;
  }

  @Override
  public void makeVisible() {
    throw new UnsupportedOperationException("Not supported by SVG view");
  }

  @Override
  public void refresh() {
    throw new UnsupportedOperationException("Not supported by SVG view");
  }

  @Override
  public Appendable render() {
    try {
      // draw canvas
      ap.append("<svg width=\"" + model.getBackground().getDimension().width + "\" height=\""
          + model.getBackground().getDimension().height
          + "\" version=\"1.1\"\n\txmlns=\"http://www.w3.org/2000/svg\">\n");

      HashMap<String, TreeMap<Integer, AnimationShape>> timeline = model.getTimeline();
      LinkedHashMap<String, String> existingShape = model.getExistingShape();
      if (timeline.size() == 0) {
        return ap;
      }

      for (String name : existingShape.keySet()) {
        ap.append(new SVGShapeVisitor(name)
            .apply(timeline.get(name).get(timeline.get(name).firstKey())) + "\n");

        Set<Integer> temp = timeline.get(name).keySet();
        List<Integer> time = new ArrayList<Integer>();
        time.addAll(temp);

        Collection<AnimationShape> temp2 = timeline.get(name).values();
        List<AnimationShape> shapes = new ArrayList<AnimationShape>();
        shapes.addAll(temp2);

        for (int i = 0; i < timeline.get(name).size() - 1; i++) {
          attribute(shapes.get(i), shapes.get(i + 1), time.get(i), time.get(i + 1), ap);
        }
        if (model.getExistingShape().get(name).equals("rectangle")) {
          ap.append("\n</rect>\n");
        }
        if (model.getExistingShape().get(name).equals("ellipse")) {
          ap.append("\n</ellipse>\n");
        }
      }
      ap.append("\n</svg>");
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed.", ioe);
    }
    return ap;
  }

  /**
   * Find out the attribute that has changes during the given time.
   *
   * @param s1 the shape at the starting time when it has an action
   * @param s2 the shape at the ending time when it has an action
   * @param t1 the starting time
   * @param t2 the ending time
   * @param ap appendable that build up the action
   * @throws IOException fail to append to the original appendable
   */
  private void attribute(AnimationShape s1, AnimationShape s2, int t1, int t2, Appendable ap)
      throws IOException {
    String begin = t1 * speed + ".0ms";
    String dur = (t2 - t1) * speed + ".0ms";
    if (s1.getType().equals("rectangle")) {
      attributeRec(s1, s2, ap, begin, dur);
    } else if (s1.getType().equals("ellipse")) {
      attributeEll(s1, s2, ap, begin, dur);
    } else {
      throw new IllegalArgumentException("This shape Type does not exist");
    }
  }

  /**
   * Find out the attribute that has changes during the given time when the shape is rectangle.
   *
   * @param s1    the shape at the starting time when it has an action
   * @param s2    the shape at the ending time when it has an action
   * @param ap    appendable that build up the action
   * @param begin the starting time
   * @param dur   the duration of the given action
   * @throws IOException fail to append to the original appendable
   */
  private void attributeRec(AnimationShape s1, AnimationShape s2, Appendable ap,
      String begin, String dur) throws IOException {
    if (s1.getPosition().getX() != s2.getPosition().getX()) {
      String result = "\t<animate" + makeTag("attributeType", "xml")
          + makeTag("begin", begin) + makeTag("dur", dur)
          + makeTag("attributeName", "x")
          + makeTag("from", s1.getPosition().getX())
          + makeTag("to", s2.getPosition().getX()) + makeTag("fill", "freeze")
          + " />\n";
      ap.append(result);
    }
    if (s1.getPosition().getY() != s2.getPosition().getY()) {
      String result = "\t<animate" + makeTag("attributeType", "xml")
          + makeTag("begin", begin) + makeTag("dur", dur)
          + makeTag("attributeName", "y")
          + makeTag("from", s1.getPosition().getY())
          + makeTag("to", s2.getPosition().getY()) + makeTag("fill", "freeze")
          + " />\n";
      ap.append(result);
    }
    if (s1.getWidth() != s2.getWidth()) {
      String result = "\t<animate" + makeTag("attributeType", "xml")
          + makeTag("begin", begin) + makeTag("dur", dur)
          + makeTag("attributeName", "width")
          + makeTag("from", s1.getWidth()) + makeTag("to", s2.getWidth())
          + makeTag("fill", "freeze") + " />\n";
      ap.append(result);
    }
    if (s1.getHeight() != s2.getHeight()) {
      String result = "\t<animate" + makeTag("attributeType", "xml")
          + makeTag("begin", begin) + makeTag("dur", dur)
          + makeTag("attributeName", "height")
          + makeTag("from", s1.getHeight()) + makeTag("to", s2.getHeight())
          + makeTag("fill", "freeze") + " />\n";
      ap.append(result);
    }
    if (s1.getColor().getR() != s2.getColor().getR()
        || s1.getColor().getG() != s2.getColor().getG()
        || s1.getColor().getB() != s2.getColor().getB()) {
      String result = "\t<animate" + makeTag("attributeType", "xml")
          + makeTag("begin", begin) + makeTag("dur", dur)
          + makeTag("attributeName", "fill")
          + makeTag("from", s1.getColor().svgString())
          + makeTag("to", s2.getColor().svgString()) + makeTag("fill", "freeze")
          + " />\n";
      ap.append(result);
    }
    if (s1.equals(s2)) {
      String result = "\t<animate" + makeTag("attributeType", "xml")
          + makeTag("begin", begin) + makeTag("dur", dur)
          + makeTag("attributeName", "x")
          + makeTag("from", s1.getPosition().getX())
          + makeTag("to", s2.getPosition().getX()) + makeTag("fill", "freeze")
          + " />\n";
      ap.append(result);
    }
  }

  /**
   * Find out the attribute that has changes during the given time when the shape is ellipse.
   *
   * @param s1    the shape at the starting time when it has an action
   * @param s2    the shape at the ending time when it has an action
   * @param ap    appendable that build up the action
   * @param begin the starting time
   * @param dur   the duration of the given action
   * @throws IOException fail to append to the original appendable
   */
  private void attributeEll(AnimationShape s1, AnimationShape s2, Appendable ap,
      String begin, String dur) throws IOException {
    if (s1.getPosition().getX() != s2.getPosition().getX()) {
      String result = "\t<animate" + makeTag("attributeType", "xml")
          + makeTag("begin", begin) + makeTag("dur", dur)
          + makeTag("attributeName", "cx")
          + makeTag("from", s1.getPosition().getX())
          + makeTag("to", s2.getPosition().getX()) + makeTag("fill", "freeze")
          + " />\n";
      ap.append(result);
    }
    if (s1.getPosition().getY() != s2.getPosition().getY()) {
      String result = "\t<animate" + makeTag("attributeType", "xml")
          + makeTag("begin", begin) + makeTag("dur", dur)
          + makeTag("attributeName", "cy")
          + makeTag("from", s1.getPosition().getY())
          + makeTag("to", s2.getPosition().getY()) + makeTag("fill", "freeze")
          + " />\n";
      ap.append(result);
    }
    if (s1.getWidth() != s2.getWidth()) {
      String result = "\t<animate" + makeTag("attributeType", "xml")
          + makeTag("begin", begin) + makeTag("dur", dur)
          + makeTag("attributeName", "rx")
          + makeTag("from", (s1.getWidth() / 2)) + makeTag("to", (s2.getWidth() / 2))
          + makeTag("fill", "freeze") + " />\n";
      ap.append(result);
    }
    if (s1.getHeight() != s2.getHeight()) {
      String result = "\t<animate" + makeTag("attributeType", "xml")
          + makeTag("begin", begin) + makeTag("dur", dur)
          + makeTag("attributeName", "ry")
          + makeTag("from", (s1.getHeight() / 2)) + makeTag("to", (s2.getHeight() / 2))
          + makeTag("fill", "freeze") + " />\n";
      ap.append(result);
    }
    if (s1.getColor().getR() != s2.getColor().getR()
        || s1.getColor().getG() != s2.getColor().getG()
        || s1.getColor().getB() != s2.getColor().getB()) {
      String result = "\t<animate" + makeTag("attributeType", "xml")
          + makeTag("begin", begin) + makeTag("dur", dur)
          + makeTag("attributeName", "fill")
          + makeTag("from", s1.getColor().svgString())
          + makeTag("to", s2.getColor().svgString()) + makeTag("fill", "freeze")
          + " />\n";
      ap.append(result);
    }
    if (s1.equals(s2)) {
      String result = "\t<animate" + makeTag("attributeType", "xml")
          + makeTag("begin", begin) + makeTag("dur", dur)
          + makeTag("attributeName", "cx")
          + makeTag("from", s1.getPosition().getX())
          + makeTag("to", s2.getPosition().getX()) + makeTag("fill", "freeze")
          + " />\n";
      ap.append(result);
    }
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

  @Override
  public Timer getTimer() {
    throw new UnsupportedOperationException("Not supported by SVG view");
  }

  @Override
  public void setIsLoop(boolean isLoop) {
    throw new UnsupportedOperationException("Not supported by SVG view");
  }

  @Override
  public boolean getIsLoop() {
    throw new UnsupportedOperationException("Not supported by SVG view");
  }

  @Override
  public int getTick() {
    throw new UnsupportedOperationException("Not supported by SVG view");
  }

  @Override
  public void reset() {
    throw new UnsupportedOperationException("Not supported by SVG view");
  }

  @Override
  public ReadOnlyAnimationModel getModel() {
    throw new UnsupportedOperationException("Not supported by SVG view");
  }

  @Override
  public void setModel(ReadOnlyAnimationModel model) {
    throw new UnsupportedOperationException("Not supported by SVG view");
  }

  @Override
  public void setTimer(Timer timer) {
    throw new UnsupportedOperationException("Not supported by SVG view");
  }

  @Override
  public void doAction(AnimationController controller) {
    throw new UnsupportedOperationException("Not supported by SVG view");
  }
}