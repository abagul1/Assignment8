package cs3500.animator.view.provider.compositeview;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

import cs3500.animator.view.provider.AnimationShape;
import cs3500.animator.view.provider.ReadOnlyAnimationModel;


/**
 * A Panel for the Animation, which extends JPanel, and draws the Animation in Swing.
 */
public class AnimationPanel extends JPanel {

  private ReadOnlyAnimationModel model;
  private int tick;

  /**
   * A constructor for the AnimationPanel, which has a read-only model.
   *
   * @param model the read-only model that the Panel will draw
   */
  public AnimationPanel(ReadOnlyAnimationModel model, int tick) {
    this.model = model;
    this.tick = tick;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    List<AnimationShape> shapes = model.getShape(tick);
    for (int i = 0; i < shapes.size(); i++) {
      new SwingShapeVisitor(g2).apply(shapes.get(i));
    }
  }

  /**
   * Sets the tick for the model.
   *
   * @param tick the tick representing time
   */
  public void setTick(int tick) {
    this.tick = tick;
  }

  /**
   * Sets the model to the given model.
   *
   * @param model the model that is to be played in animation
   */
  public void setModel(ReadOnlyAnimationModel model) {
    this.model = model;
  }
}