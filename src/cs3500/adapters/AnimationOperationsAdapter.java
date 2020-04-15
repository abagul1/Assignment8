package cs3500.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

import cs3500.IAnimation;
import cs3500.animator.provider.view.AnimationBackground;
import cs3500.animator.provider.view.AnimationOperations;
import cs3500.animator.provider.view.AnimationShape;
import cs3500.animator.provider.view.EllipseShape;
import cs3500.animator.provider.view.Posn2D;
import cs3500.animator.provider.view.RectangleShape;
import cs3500.animator.provider.view.ShapeColor;
import cs3500.motions.Motion;

/**
 * Represents an adapter for a model, couples the IAnimation with provider's
 * AnimationOperations.
 */
public class AnimationOperationsAdapter implements AnimationOperations {
  private IAnimation a;

  /**
   * Constructor for animation operations adapter.
   * @param a Consumer's model, IAnimation
   */
  public AnimationOperationsAdapter(IAnimation a) {
    this.a = a;
  }


  @Override
  public void declareShape(String name, String type) {
    a.insertElement(name, type);
  }

  @Override
  public void addShape(AnimationShape newShape, String name, int time) {
    //Not needed for this assignment
  }

  /**
   * Gets the type of an element.
   * @param name name of shape
   * @return type
   */
  public String getType(String name) {
    return a.getElement(name).getType();
  }

  @Override
  public void removeShape(String name) {
    a.deleteElement(name);
  }

  @Override
  public void move(String name, int startingTime, int endingTime, int newX, int newY) {
    //Not needed for this assignment
  }

  @Override
  public void changeColor(String name, int startingTime, ShapeColor color, int endingTime) {
    //Not for key frames
  }

  @Override
  public void changeSize(String name, int startingTime, int height, int width, int endingTime) {
    //Not for key frames
  }

  @Override
  public void setBackground(int x, int y, int width, int height) {
    //Not needed for this assignment
  }

  @Override
  public void setExistingShape(LinkedHashMap<String, String> shapes) {
    //Not needed for this assignment
  }

  @Override
  public void setTimeline(HashMap<String, TreeMap<Integer, AnimationShape>> timeline) {
    //Not needed for this assignment
  }

  @Override
  public void addFrame(String name, int time, AnimationShape newShape) {
    a.insertKeyFrame(name, time, new Motion(a.getElement(name), null, time,
            newShape.getPosition().getX(), newShape.getPosition().getY(), newShape.getWidth(),
            newShape.getHeight(), newShape.getColor().getR(), newShape.getColor().getG(),
            newShape.getColor().getB()));
  }

  @Override
  public void removeFrame(String name, int time) {
    a.deleteKeyFrame(name, time);
  }

  @Override
  public void modifyFrame(String name, int time, AnimationShape newShape) {
    a.editKeyFrame(name, time, new Motion(a.getElement(name), null, time,
            newShape.getPosition().getX(), newShape.getPosition().getY(), newShape.getWidth(),
            newShape.getHeight(), newShape.getColor().getR(), newShape.getColor().getG(),
            newShape.getColor().getB()));
  }

  @Override
  public AnimationShape makeShape(String name, Posn2D pos, ShapeColor color,
                                  int height, int width) {
    if (this.getExistingShape().get(name).equals("rectangle")) {
      return new RectangleShape(pos, color, height, width);
    }
    else {
      return new EllipseShape(pos, color, height, width);
    }
  }

  @Override
  public String getAnimation() {
    return a.getVerboseAnimation();
  }

  @Override
  public AnimationShape getShape(int time, String name) {
    return this.getTimeline().get(name).get(time);
  }

  @Override
  public List<AnimationShape> getShape(int time) {
    HashMap<String, TreeMap<Integer, AnimationShape>> tl = this.getTimeline();
    List<AnimationShape> ls = new ArrayList<>();
    for (String name : tl.keySet()) {
      if (tl.get(name).containsKey(time)) {
        ls.add(tl.get(name).get(time));
      }
    }
    return ls;
  }

  @Override
  public AnimationBackground getBackground() {
    return new AnimationBackground(a.getX(), a.getY(), a.getWidth(), a.getHeight());
  }

  @Override
  public LinkedHashMap<String, String> getExistingShape() {
    HashMap<String, TreeMap<Integer, AnimationShape>> tl = this.getTimeline();
    LinkedHashMap<String, String> existingShapes = new LinkedHashMap<>();
    for (String key : tl.keySet()) {
      existingShapes.put(key, a.getElement(key).getType());
    }
    return existingShapes;
  }

  @Override
  public HashMap<String, TreeMap<Integer, AnimationShape>> getTimeline() {
    HashMap<String, TreeMap<Integer, AnimationShape>> timeline = new LinkedHashMap<>();
    for (String key : a.getElements().keySet()) {
      timeline.put(key, new TreeMap<>());
      AnimationShape prevShape = null;
      for (Motion m : a.getKeyFrame(key)) {
        AnimationShape as;
        float[] arr;
        if (a.getElement(key).getType().equals("rectangle")) {
          as = new RectangleShape(new Posn2D(m.getParams()[1], m.getParams()[2]),
                  new ShapeColor(m.getParams()[5], m.getParams()[6], m.getParams()[7]),
                  m.getParams()[4], m.getParams()[3]);
          arr = this.setDeltas(m.getPrevMotion(), m);
        } else {
          as = new EllipseShape(new Posn2D(m.getParams()[1], m.getParams()[2]),
                  new ShapeColor(m.getParams()[5], m.getParams()[6], m.getParams()[7]),
                  m.getParams()[4], m.getParams()[3]);
          arr = this.setDeltas(m.getPrevMotion(), m);
        }
        if (prevShape == null) {
          timeline.get(key).put(m.getParams()[0], as);
        }
        else {
          prevShape.setDeltaColor(arr[4], arr[5], arr[6]);
          prevShape.setDeltaPosition(arr[0], arr[1]);
          prevShape.setDeltaWidth(arr[2]);
          prevShape.setDeltaHeight(arr[3]);
          for (int i = m.getPrevMotion().getParams()[0] + 1; i < m.getParams()[0]; i++) {
            timeline.get(key).put(i, prevShape.showShape(i - m.getPrevMotion().getParams()[0]));
          }
        }
        prevShape = as;
      }
    }
    return timeline;
  }


  /**
   * Calculates the delta values to find tweening parameters between two keyframes.
   * @param prev previous motion
   * @param m current motion
   * @return an array of the deltas [dx, dy, dw, dh, dr, dg, db]
   */
  private float[] setDeltas(Motion prev, Motion m) {
    if (prev == null) {
      return new float[]{0,0,0,0,0,0,0};
    }

    float dx = (float) (m.getParams()[1] - prev.getParams()[1])
            / (m.getParams()[0] - prev.getParams()[0]);

    float dy = (float) (m.getParams()[2] - prev.getParams()[2])
            / (m.getParams()[0] - prev.getParams()[0]);

    float dw = (float) (m.getParams()[3] - prev.getParams()[3])
            / (m.getParams()[0] - prev.getParams()[0]);

    float dh = (float) (m.getParams()[4] - prev.getParams()[4])
            / (m.getParams()[0] - prev.getParams()[0]);

    float dr = (float) (m.getParams()[5] - prev.getParams()[5])
            / (m.getParams()[0] - prev.getParams()[0]);

    float dg = (float) (m.getParams()[6] - prev.getParams()[6])
            / (m.getParams()[0] - prev.getParams()[0]);

    float db = (float) (m.getParams()[7] - prev.getParams()[7])
            / (m.getParams()[0] - prev.getParams()[0]);

    return new float[]{dx, dy, dw, dh, dr, dg, db};

  }
}
