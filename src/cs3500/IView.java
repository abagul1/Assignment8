package cs3500;

/**
 * Represents the view within a Model-View-Controller framework for our animator.
 */
public interface IView {
  /**
   * Refresh the view to reflect any changes in the game state.
   */
  void refresh();

  /**
   * Make the view visible to start the game session.
   */
  void makeVisible();

  /**
   * Listener to check for mouse clicks on the view.
   * @param listener controller
   */
  void addClickListener(IController listener);

  /**
   * Command to execute the desired view.
   */
  void execute();
}
