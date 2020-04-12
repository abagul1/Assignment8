package cs3500.motions;

import java.awt.Color;
import cs3500.IElement;
import cs3500.IKeyFrame;
import cs3500.elements.Posn;

/**
 * Class to represent a motion/keyframe, and calculate the changes of an element over time.
 */
public class Motion implements IKeyFrame {
  private IElement element;
  private Motion prevMotion;
  private Motion nextMotion;
  private int t;
  private int x;
  private int y;
  private int w;
  private int h;
  private int r;
  private int g;
  private int b;

  /**
   * Constructor for a motion.
   * @param e element to apply the motion
   * @param prevMotion previous keyframe
   * @param t ending tick
   * @param x ending x position
   * @param y ending y position
   * @param w ending width
   * @param h ending height
   * @param r ending red value
   * @param g ending green value
   * @param b ending blue value
   */
  public Motion(IElement e, Motion prevMotion,
                int t, int x, int y, int w, int h, int r, int g, int b) {
    this.element = e;
    this.prevMotion = prevMotion;
    this.t = t;
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.r = r;
    this.g = g;
    this.b = b;
    this.nextMotion = null;
  }

  @Override
  public void fire(int currentTick) {
    if (prevMotion == null && currentTick == t) {
      this.element.setPosn(new Posn(x, y));
      this.element.setHeight(h);
      this.element.setWidth(w);
      Color c = new Color(r, g, b, 255);
      this.element.setColor(c);
    }
    else if (prevMotion != null && currentTick >= prevMotion.getParams()[0] && currentTick <= t) {
      double x = (double) (prevMotion.getParams()[1]
              * (t - currentTick) / (t - prevMotion.getParams()[0]))
              + (double) (this.x * (currentTick - prevMotion.getParams()[0])
              / (t - prevMotion.getParams()[0]));
      double y = (double) (prevMotion.getParams()[2]
              * (t - currentTick) / (t - prevMotion.getParams()[0]))
              + (double) (this.y * (currentTick - prevMotion.getParams()[0])
              / (t - prevMotion.getParams()[0]));
      double w = (double) (prevMotion.getParams()[3]
              * (t - currentTick) / (t - prevMotion.getParams()[0]))
              + (double) (this.w * (currentTick - prevMotion.getParams()[0])
              / (t - prevMotion.getParams()[0]));
      double h = (double) (prevMotion.getParams()[4]
              * (t - currentTick) / (t - prevMotion.getParams()[0]))
              + (double) (this.h * (currentTick - prevMotion.getParams()[0])
              / (t - prevMotion.getParams()[0]));
      double r = (double) (prevMotion.getParams()[5]
              * (t - currentTick) / (t - prevMotion.getParams()[0]))
              + (double) (this.r * (currentTick - prevMotion.getParams()[0])
              / (t - prevMotion.getParams()[0]));
      double g = (double) (prevMotion.getParams()[6]
              * (t - currentTick) / (t - prevMotion.getParams()[0]))
              + (double) (this.g * (currentTick - prevMotion.getParams()[0])
              / (t - prevMotion.getParams()[0]));
      double b = (double) (prevMotion.getParams()[7]
              * (t - currentTick) / (t - prevMotion.getParams()[0]))
              + (double) (this.b * (currentTick - prevMotion.getParams()[0])
              / (t - prevMotion.getParams()[0]));

      this.element.setPosn(new Posn(x, y));
      this.element.setHeight(h);
      this.element.setWidth(w);
      int newRed = (int) r;
      int newGreen = (int) g;
      int newBlue = (int) b;

      if (newRed > 255) {
        newRed = 255;
      }
      if (newGreen > 255) {
        newGreen = 255;
      }
      if (newBlue > 255) {
        newBlue = 255;
      }
      if (newRed < 0) {
        newRed = 0;
      }
      if (newGreen < 0) {
        newGreen = 0;
      }
      if (newBlue < 0) {
        newBlue = 0;
      }
      Color c = new Color(newRed, newGreen, newBlue, 255);
      this.element.setColor(c);
    }
  }

  @Override
  public int[] getParams() {
    return new int[] {t, x, y, w, h, r, g, b};
  }

  @Override
  public void setNextMotion(Motion m) {
    this.nextMotion = m;
  }

  @Override
  public void setPrevMotion(Motion m) {
    this.prevMotion = m;
  }

  @Override
  public Motion getPrevMotion() {
    return prevMotion;
  }

  @Override
  public Motion getNextMotion() {
    return nextMotion;
  }
}
