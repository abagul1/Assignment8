package cs3500.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import cs3500.IAnimation;
import cs3500.animator.view.provider.AnimationBackground;
import cs3500.animator.view.provider.AnimationOperations;
import cs3500.animator.view.provider.AnimationShape;
import cs3500.animator.view.provider.EllipseShape;
import cs3500.animator.view.provider.Posn2D;
import cs3500.animator.view.provider.RectangleShape;
import cs3500.animator.view.provider.ShapeColor;
import cs3500.motions.Motion;

public class AnimationOperationsAdapter implements AnimationOperations {
  private IAnimation a;
  private HashMap<String, TreeMap<Integer, AnimationShape>> tl;

  public AnimationOperationsAdapter(IAnimation a) {
    this.a = a;
    this.tl = new LinkedHashMap<>();
    this.initTimeline();
  }

  private void initTimeline() {
    for (String key : a.getElements().keySet()) {
      tl.put(key, new TreeMap<>());
      for (Motion m : a.getKeyFrame(key)) {
        AnimationShape as;
        if (a.getElement(key).getType().equals("rectangle")) {
          as = new RectangleShape(new Posn2D(m.getParams()[1], m.getParams()[2]),
                  new ShapeColor(m.getParams()[5], m.getParams()[6], m.getParams()[7]),
                  m.getParams()[4], m.getParams()[3]);
        }
        else {
          as = new EllipseShape(new Posn2D(m.getParams()[1], m.getParams()[2]),
                  new ShapeColor(m.getParams()[5], m.getParams()[6], m.getParams()[7]),
                  m.getParams()[4], m.getParams()[3]);
        }
        tl.get(key).put(m.getParams()[0], as);
      }
    }
  }

  @Override
  public void declareShape(String name, String type) {
    LinkedHashMap<String, String> newES;
    newES = this.getExistingShape();
    newES.put(name, type);
    this.setExistingShape(newES);
    tl.put(name, new TreeMap<>());
  }

  @Override
  public void addShape(AnimationShape newShape, String name, int time) {

  }

  /**
   * Gets the type of an element
   * @param name name of shape
   * @return type
   */
  public String getType(String name) {
    return a.getElement(name).getType();
  }

  @Override
  public void removeShape(String name) {
    LinkedHashMap<String, String> newES;
    newES = this.getExistingShape();
    newES.remove(name);
    this.setExistingShape(newES);
    tl.remove(name);
  }

  @Override
  public void move(String name, int startingTime, int endingTime, int newX, int newY) {

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

  }

  @Override
  public void setExistingShape(LinkedHashMap<String, String> shapes) {

  }

  @Override
  public void setTimeline(HashMap<String, TreeMap<Integer, AnimationShape>> timeline) {

  }

  @Override
  public void addFrame(String name, int time, AnimationShape newShape) {
    this.tl.get(name).put(time, newShape);
  }

  @Override
  public void removeFrame(String name, int time) {
    this.tl.get(name).remove(time);
  }

  @Override
  public void modifyFrame(String name, int time, AnimationShape newShape) {
    this.tl.get(name).put(time, newShape);
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
    return this.tl.get(name).get(time);
  }

  @Override
  public List<AnimationShape> getShape(int time) {
    List<AnimationShape> ls = new ArrayList<>();
    for (String name : this.tl.keySet()) {
      if (this.tl.get(name).containsKey(time)) {
        ls.add(this.tl.get(name).get(time));
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
    LinkedHashMap<String, String> existingShapes = new LinkedHashMap<>();
    for (String key : this.tl.keySet()) {
      Map.Entry<Integer, AnimationShape> entry = tl
              .get(key).entrySet().iterator().next();
      existingShapes.put(key, entry.getValue().getType());
    }
    return existingShapes;
  }

  @Override
  public HashMap<String, TreeMap<Integer, AnimationShape>> getTimeline() {
    return tl;
  }
}
