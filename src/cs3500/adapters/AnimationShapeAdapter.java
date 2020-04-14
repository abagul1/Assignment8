package cs3500.adapters;

import cs3500.animator.view.provider.AnimationShape;
import cs3500.animator.view.provider.Posn2D;
import cs3500.animator.view.provider.ShapeColor;
import cs3500.animator.view.provider.compositeview.ISVGShapeVisitor;
import cs3500.animator.view.provider.compositeview.ISwingShapeVisitor;

public class AnimationShapeAdapter implements AnimationShape {
  @Override
  public AnimationShape showShape(int time) {
    return null;
  }

  @Override
  public Posn2D getPosition() {
    return null;
  }

  @Override
  public ShapeColor getColor() {
    return null;
  }

  @Override
  public int getHeight() {
    return 0;
  }

  @Override
  public int getWidth() {
    return 0;
  }

  @Override
  public void setPosition(int x, int y) {

  }

  @Override
  public void setColor(int r, int g, int b) {

  }

  @Override
  public void setSize(int h, int w) {

  }

  @Override
  public float getDeltaX() {
    return 0;
  }

  @Override
  public float getDeltaY() {
    return 0;
  }

  @Override
  public float getDeltaR() {
    return 0;
  }

  @Override
  public float getDeltaG() {
    return 0;
  }

  @Override
  public float getDeltaB() {
    return 0;
  }

  @Override
  public float getDeltaHeight() {
    return 0;
  }

  @Override
  public float getDeltaWidth() {
    return 0;
  }

  @Override
  public void setDeltaPosition(float deltaX, float deltaY) {

  }

  @Override
  public void setDeltaColor(float deltaR, float deltaG, float deltaB) {

  }

  @Override
  public void setDeltaHeight(float deltaHeight) {

  }

  @Override
  public void setDeltaWidth(float deltaWidth) {

  }

  @Override
  public String getType() {
    return null;
  }

  @Override
  public String acceptSVG(ISVGShapeVisitor visitor) {
    return null;
  }

  @Override
  public void accept(ISwingShapeVisitor visitor) {

  }

}
