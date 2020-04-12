package cs3500.animator.view.textview;

import java.io.FileWriter;
import java.io.IOException;

import cs3500.IController;
import cs3500.ReadOnlyAnimation;
import cs3500.animator.view.AbstractTextView;

/**
 * Represents the textual view for the animation.
 */
public class TextView extends AbstractTextView {

  private String out;


  /**
   * Constructor for the text view.
   * @param a read only animation.
   * @param out output destination for text output.
   */
  public TextView(ReadOnlyAnimation a, String out) {
    super(a);
    this.out = out;
  }

  @Override
  public void execute() {
    try {
      FileWriter fileWriter = new FileWriter(out);
      fileWriter.write(super.getVerboseAnimation());
      fileWriter.close();
    }
    catch (IOException e) {
      throw new IllegalArgumentException("file writer is not setup properly");
    }
  }

  @Override
  public void refresh() {
    throw new UnsupportedOperationException("text view cannot refresh");
  }

  @Override
  public void makeVisible() {
    throw new UnsupportedOperationException("text view cannot makeVisible");
  }

  @Override
  public void addClickListener(IController listener) {
    throw new UnsupportedOperationException("text view cannot addClickListener");
  }
}
