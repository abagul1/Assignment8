package cs3500.animator.view;

import java.util.Objects;

import cs3500.IAnimation;
import cs3500.IView;
import cs3500.animator.view.svgview.SVGView;
import cs3500.animator.view.textview.TextView;
import cs3500.animator.view.textview.TextViewSysOut;
import cs3500.animator.view.visualview.EnhancedVisualView;
import cs3500.animator.view.visualview.VisualView;

/**
 * Factory class to create the specified view.
 */
public class ViewCreator {

  /**
   * Creates a view and passes it a read only animation and output destination.
   * @param vt view type
   * @param a read only animation
   * @param out output file
   * @return a view
   */
  public static IView create(String vt, IAnimation a, String out, int speed) {
    Objects.requireNonNull(vt, "Must have non-null view type");
    if (speed < 1) {
      throw new IllegalArgumentException("Speed must be at least 1");
    }

    switch (vt) {
      case "text":
        if (out.equals("System.out")) {
          return new TextViewSysOut(a);
        }
        else {
          return new TextView(a, out);
        }
      case "svg":
        return new SVGView(a, out, speed);
      case "visual":
        return new VisualView(a);
      case "edit":
        return new EnhancedVisualView(a);
      default:
        throw new IllegalArgumentException("Invalid view type");
    }
  }

}
