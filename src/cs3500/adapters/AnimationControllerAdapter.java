package cs3500.adapters;

import javax.swing.Timer;

import cs3500.animator.view.provider.AnimationController;
import cs3500.animator.view.provider.AnimationOperations;
import cs3500.animator.view.provider.AnimationShape;
import cs3500.animator.view.provider.EllipseShape;
import cs3500.animator.view.provider.Posn2D;
import cs3500.animator.view.provider.RectangleShape;
import cs3500.animator.view.provider.ShapeColor;
import cs3500.animator.view.provider.compositeview.AnimationView;

public class AnimationControllerAdapter implements AnimationController {
  private AnimationView v;
  private AnimationOperations a;
  private int speed;
  private boolean paused;
  private Timer t;

  public AnimationControllerAdapter(AnimationView v, AnimationOperations a, int tempo) {
    this.v = v;
    this.a = a;
    this.paused = true;
    this.speed = tempo;
  }

  @Override
  public void deleteShape(String name) {
    a.removeShape(name);
  }

  @Override
  public void deleteFrame(String name, int time) {
    a.removeFrame(name, time);
  }

  @Override
  public void addShape(String name, String type) {
    a.declareShape(name, type);
  }

  @Override
  public void addFrame(String name, int time, Posn2D pos, ShapeColor color, int height, int width) {
    a.addFrame(name, time, a.makeShape(name, pos, color, height, width));
  }

  @Override
  public void modifyFrame(String name, int time, Posn2D pos, ShapeColor color,
                          int height, int width) {
    a.modifyFrame(name, time, a.makeShape(name, pos, color, height, width));
  }

  @Override
  public void playAnimation() {
    v.doAction(this);
    t = new Timer(1000 / speed, e -> execute());
    t.start();
    v.setTimer(t);
  }

  /**
   * Executes the view.
   */
  private void execute() {
    if (!paused) {
      v.makeVisible();
      v.refresh();
    }
  }

  @Override
  public void faster() {
    t.setDelay(1000 / speed++);
  }

  @Override
  public void slower() {
    if (speed > 0) {
      t.setDelay(1000 / speed--);
    }
  }

  @Override
  public void restart() {
    v.setModel(a);
  }

  @Override
  public void loop() {
    v.setIsLoop(!v.getIsLoop());
  }

  @Override
  public void pause() {
    this.paused = true;
  }

  @Override
  public void resume() {
    this.paused = false;
  }

  @Override
  public void save(String fileName) {

  }

  @Override
  public void load(String fileName) {

  }
}
