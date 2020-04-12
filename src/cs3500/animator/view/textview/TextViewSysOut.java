package cs3500.animator.view.textview;

import cs3500.IController;
import cs3500.ReadOnlyAnimation;
import cs3500.animator.view.AbstractTextView;

/**
 * Represents the textual view for the animation when the output is the console.
 */
public class TextViewSysOut extends AbstractTextView {

  /**
   * Constructor for a text view from the console output.
   * @param a animation
   */
  public TextViewSysOut(ReadOnlyAnimation a) {
    super(a);
  }

  @Override
  public void execute() {
    System.out.println(super.getVerboseAnimation());
  }

  @Override
  public void refresh() {
    throw new UnsupportedOperationException("Text view cannot refresh");
  }

  @Override
  public void makeVisible() {
    throw new UnsupportedOperationException("Text view cannot makeVisible");
  }

  @Override
  public void addClickListener(IController listener) {
    throw new UnsupportedOperationException("Text view cannot addClickListener");
  }
}
