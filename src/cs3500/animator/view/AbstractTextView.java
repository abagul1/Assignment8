package cs3500.animator.view;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cs3500.IElement;
import cs3500.IView;
import cs3500.ReadOnlyAnimation;

/**
 * Represents an abstract view for text and svg formats.
 */
public abstract class AbstractTextView implements IView {
  protected Map<String, IElement> elements;
  protected Map<String, List<String>> verboseOps;
  protected ReadOnlyAnimation a;

  /**
   * A constructor for an abstract text view.
   * @param a model to be used by the view
   */
  public AbstractTextView(ReadOnlyAnimation a) {

    this.a = a;
    this.elements = a.getElements();
    this.initVerboseOps();
  }

  /**
   * Initialize the verbose map of elements and motions associated with those elements.
   */
  private void initVerboseOps() {
    this.verboseOps = new LinkedHashMap<>();
    for (String key : elements.keySet()) {
      verboseOps.put(key, a.getMotionsForElement(key));
    }
  }

  /**
   * Gets the text form of the animation from the model.
   * @return a string with the text output of the animation
   */
  public String getVerboseAnimation() {
    StringBuilder str = new StringBuilder();
    str.append("canvas ").append(a.getX()).append(" ")
            .append(a.getY()).append(" ").append(a.getWidth()).append(" ")
            .append(a.getHeight()).append("\n");
    for (String id : verboseOps.keySet()) {
      for (String i : verboseOps.get(id)) {
        str.append(i).append("\n");
      }
      str.append("\n");
    }
    return str.toString();
  }

}
