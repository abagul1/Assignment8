package cs3500.adapters;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

import cs3500.IAnimation;
import cs3500.animator.view.provider.AnimationBackground;
import cs3500.animator.view.provider.AnimationOperations;
import cs3500.animator.view.provider.AnimationShape;
import cs3500.animator.view.provider.Posn2D;
import cs3500.animator.view.provider.ShapeColor;

public class AnimationOperationsAdapter implements AnimationOperations {
  IAnimation a;

  public AnimationOperationsAdapter(IAnimation a) {
    this.a = a;
  }

  @Override
  public void declareShape(String name, String type) {
    a.insertElement(name, type);
  }

  @Override
  public void addShape(AnimationShape newShape, String name, int time) {

  }

  @Override
  public void removeShape(String name) {
    a.deleteElement(name);
  }

  @Override
  public void move(String name, int startingTime, int endingTime, int newX, int newY) {

  }

  @Override
  public void changeColor(String name, int startingTime, ShapeColor color, int endingTime) {

  }

  @Override
  public void changeSize(String name, int startingTime, int height, int width, int endingTime) {

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

  }

  @Override
  public void removeFrame(String name, int time) {

  }

  @Override
  public void modifyFrame(String name, int time, AnimationShape newShape) {

  }

  @Override
  public AnimationShape makeShape(String name, Posn2D pos, ShapeColor color, int height, int width) {
    return null;
  }

  @Override
  public String getAnimation() {
    return null;
  }

  @Override
  public AnimationShape getShape(int time, String name) {
    return null;
  }

  @Override
  public List<AnimationShape> getShape(int time) {
    return null;
  }

  @Override
  public AnimationBackground getBackground() {
    return null;
  }

  @Override
  public LinkedHashMap<String, String> getExistingShape() {
    return null;
  }

  @Override
  public HashMap<String, TreeMap<Integer, AnimationShape>> getTimeline() {
    return null;
  }
}
