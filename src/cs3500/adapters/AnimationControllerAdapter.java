package cs3500.adapters;

import cs3500.animator.view.provider.AnimationController;
import cs3500.animator.view.provider.Posn2D;
import cs3500.animator.view.provider.ShapeColor;

public class AnimationControllerAdapter implements AnimationController {

  public AnimationControllerAdapter() {

  }

  @Override
  public void deleteShape(String name) {

  }

  @Override
  public void deleteFrame(String name, int time) {

  }

  @Override
  public void addShape(String name, String type) {

  }

  @Override
  public void addFrame(String name, int time, Posn2D pos, ShapeColor color, int height, int width) {

  }

  @Override
  public void modifyFrame(String name, int time, Posn2D pos, ShapeColor color, int height, int width) {

  }

  @Override
  public void playAnimation() {

  }

  @Override
  public void faster() {

  }

  @Override
  public void slower() {

  }

  @Override
  public void restart() {

  }

  @Override
  public void loop() {

  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void save(String fileName) {

  }

  @Override
  public void load(String fileName) {

  }
}
