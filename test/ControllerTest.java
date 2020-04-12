import org.junit.Test;

import cs3500.IAnimation;
import cs3500.IController;
import cs3500.animator.controller.AnimationController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.view.visualview.EnhancedVisualView;
import static org.junit.Assert.assertEquals;

/**
 * Test class for a controller.
 */
public class ControllerTest {
  IAnimation a = new AnimationModel(500,500,100,100);
  IController c = new AnimationController(new EnhancedVisualView(a));

  @Test
  public void testGetPaused() {
    assertEquals(true, c.getPaused());
  }

  @Test
  public void testSetPaused() {
    c.setPaused();
    assertEquals(false, c.getPaused());
  }

  @Test
  public void testGetLooped() {
    assertEquals(false, c.getLoop());
  }

  @Test
  public void testSetLooped() {
    c.setLoop();
    assertEquals(true, c.getLoop());
  }

  @Test
  public void testGetSpeed() {
    assertEquals(1, c.getSpeed());
  }


}
