import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.awt.Color;

import cs3500.animator.model.AnimationModel;
import cs3500.IAnimation;
import cs3500.IElement;
import cs3500.elements.Posn;
import cs3500.elements.Ellipse;
import cs3500.elements.Rectangle;
import cs3500.motions.Motion;

/**
 * Test class for animator model.
 */
public class AnimatorTest {
  private IAnimation am;
  private IElement r1 = new Rectangle("R", Color.RED,
          new Posn(200,200), 100,50);
  private IElement e1 = new Ellipse("C", Color.BLUE,
          new Posn(440, 70), 60, 120);


  @Test
  public void testMoveOp() {
    am = new AnimationModel(0, 0, 500, 500);
    am.insertElement("R", "rectangle");
    am.motion("R", 1, 200, 200, 50, 100, 255, 0 ,0,
            10, 300, 150, 50 ,100, 255, 0, 0);
    am.executeOperations();
    assertEquals(300, am.getElement("R").getPosn().getX(), 0.001);
    assertEquals(150, am.getElement("R").getPosn().getY(), 0.001);
  }

  @Test
  public void testInsertOp() {
    am = new AnimationModel(0, 0, 500, 500);
    am.insertElement("R", "rectangle");
    am.motion("R", 5, 200, 200, 50, 100, 255, 0 ,0,
            10, 200, 200, 50 ,100, 255, 0, 0);
    am.executeOperationsUntil(5);
    assertEquals(r1, am.getElement("R"));
  }

  @Test
  public void testInsertOpTwoElementsSameTime() {
    am = new AnimationModel(0, 0, 500, 500);
    am.insertElement("R", "rectangle");
    am.insertElement("C", "ellipse");
    am.motion("R", 5, 200, 200, 50, 100, 255, 0 ,0,
            10, 200, 200, 50 ,100, 255, 0, 0);
    am.motion("C", 6, 440, 70, 120, 60, 0, 0 ,255,
            20, 440, 70, 120 ,60, 0, 0, 255);
    am.executeOperations();
    assertEquals(am.getElement("R"), r1);
    assertEquals(am.getElement("C"), e1);
  }

  @Test
  public void testScaleOp() {
    am = new AnimationModel(0, 0, 500, 500);
    am.insertElement("R", "rectangle");
    am.motion("R", 5, 200, 200, 50, 100, 255, 0 ,0,
            10, 200, 200, 54 ,246, 255, 0, 0);
    am.executeOperations();
    assertEquals(246, am.getElement("R").getDimensions()[0], 0.001);
    assertEquals(54, am.getElement("R").getDimensions()[1], 0.001);
  }

  @Test
  public void testChangeColorOp() {
    am = new AnimationModel(0, 0, 500, 500);
    am.insertElement("R", "rectangle");
    am.motion("R", 5, 200, 200, 50, 100, 255, 0 ,0,
            10, 200, 200, 54 ,246, 255, 0, 0);
    am.motion("R", 10, 200, 200, 50, 100, 255, 0 ,0,
            12, 200, 200, 54 ,246, 0, 255, 255);
    am.executeOperationsUntil(11);
    assertEquals(0, am.getElement("R").getColor().getGreen(), 1);
    assertEquals(255, am.getElement("R").getColor().getRed(), 1);
    assertEquals(0, am.getElement("R").getColor().getBlue(), 1);
    am.executeOperations();
    assertEquals(255,am.getElement("R").getColor().getGreen(), 1);
    assertEquals(0,am.getElement("R").getColor().getRed(), 1);
    assertEquals(255,am.getElement("R").getColor().getBlue(), 1);
  }

  @Test
  public void testGetVerboseAnimationMove() {
    am = new AnimationModel(0, 0, 500, 500);
    am.insertElement("R", "rectangle");
    am.motion("R", 1, 50, 50, 5, 10, 0, 255,0,
            5, 60, 30, 5 ,10, 0, 255, 0);
    String str = am.getVerboseAnimation();
    assertEquals("shape R rectangle\n"
            + "motion R 1 50 50 5 10 0 255 0 5 60 30 5 10 0 255 0\n\n", str);
  }


  @Test
  public void testGetVerboseAnimationScale() {
    am = new AnimationModel(0, 0, 500, 500);
    am.insertElement("R", "rectangle");
    am.motion("R", 1, 50, 50, 5, 10, 0, 255,0,
            5, 60, 30, 10 ,20, 0, 255, 0);
    String str = am.getVerboseAnimation();
    assertEquals("shape R rectangle\n"
            + "motion R 1 50 50 5 10 0 255 0 5 60 30 10 20 0 255 0\n\n", str);
  }

  @Test
  public void testGetVerboseAnimationChangeColor() {
    am = new AnimationModel(0, 0, 500, 500);
    am.insertElement("R", "rectangle");
    am.motion("R", 1, 50, 50, 5, 10, 0, 255,0,
            5, 60, 30, 10 ,20, 255, 0, 0);
    String str = am.getVerboseAnimation();
    assertEquals("shape R rectangle\n"
            + "motion R 1 50 50 5 10 0 255 0 5 60 30 10 20 255 0 0\n\n", str);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSimultaneousMove() {
    am = new AnimationModel(0, 0, 500, 500);
    am.insertElement("R", "rectangle");
    am.motion("R", 1, 50, 50, 5, 10, 0, 255,0,
            5, 60, 30, 10 ,20, 255, 0, 0);
    am.motion("R", 1, 50, 50, 5, 10, 0, 255,0,
            5, 60, 30, 10 ,20, 255, 0, 0);
    am.executeOperations();
  }


  @Test
  public void testMove2() {
    am = new AnimationModel(0, 0, 500, 500);
    am.insertElement("R", "rectangle");
    am.motion("R", 1, 200, 200, 50, 100, 255, 0 ,0,
            10, 300, 150, 50 ,100, 255, 0, 0);
    am.motion("R", 10, 300, 150, 50, 100, 255, 0 ,0,
            13, 34, 27, 50 ,100, 255, 0, 0);
    am.executeOperations();
    assertEquals(34, am.getElement("R").getPosn().getX(), 0.001);
    assertEquals(27, am.getElement("R").getPosn().getY(), 0.001);
  }

  @Test
  public void testChangeColorOp2() {
    am = new AnimationModel(0, 0, 500, 500);
    am.insertElement("R", "rectangle");
    am.motion("R", 5, 200, 200, 50, 100, 255, 0 ,0,
            10, 200, 200, 54 ,246, 255, 0, 0);
    am.motion("R", 10, 200, 200, 50, 100, 255, 0 ,0,
            12, 200, 200, 54 ,246, 0, 255, 255);
    am.motion("R", 12, 200, 200, 50, 100, 0, 255 ,255,
            14, 200, 200, 54 ,246, 23, 45, 154);
    am.executeOperationsUntil(11);
    assertEquals(0, am.getElement("R").getColor().getGreen(), 2);
    assertEquals(255, am.getElement("R").getColor().getRed(), 2);
    assertEquals(0, am.getElement("R").getColor().getBlue(), 2);
    am.executeOperations();
    assertEquals(45,am.getElement("R").getColor().getGreen(), 2);
    assertEquals(23,am.getElement("R").getColor().getRed(), 2);
    assertEquals(154,am.getElement("R").getColor().getBlue(), 2);
  }

  @Test
  public void testScaleOp2() {
    am = new AnimationModel(0, 0, 500, 500);
    am.insertElement("R", "rectangle");
    am.motion("R", 5, 200, 200, 50, 100, 255, 0 ,0,
            10, 200, 200, 54 ,246, 255, 0, 0);
    am.motion("R", 10, 200, 200, 54, 246, 255, 0 ,0,
            13, 200, 200, 12 ,65, 255, 0, 0);
    am.executeOperations();
    assertEquals(65, am.getElement("R").getDimensions()[0], 0.001);
    assertEquals(12, am.getElement("R").getDimensions()[1], 0.001);
  }

  @Test
  public void testInsertKeyFrame() {
    am = new AnimationModel(0, 0, 500, 500);
    am.insertElement("R", "rectangle");
    am.motion("R", 5, 200, 200, 50, 100, 255, 0 ,0,
            10, 200, 200, 54 ,246, 255, 0, 0);
    Motion m = new Motion(r1, null, 2, 100, 100,
            75, 89, 255, 0 ,0);
    am.insertKeyFrame("R", m.getParams()[0], m);
    assertEquals(am.getKeyFrame("R").get(0), m);
    Motion m2 = new Motion(r1, null, 11, 100, 100,
            75, 89, 255, 0 ,0);
    am.insertKeyFrame("R", m2.getParams()[0], m2);
    assertEquals(11, am.getKeyFrame("R").get(2).getParams()[0]);
  }

  @Test
  public void testDeleteKeyFrame() {
    am = new AnimationModel(0, 0, 500, 500);
    am.insertElement("R", "rectangle");
    am.motion("R", 5, 200, 200, 50, 100, 255, 0 ,0,
            10, 200, 200, 54 ,246, 255, 0, 0);
    Motion m = new Motion(r1, null, 2, 100, 100,
            75, 89, 255, 0 ,0);
    am.insertKeyFrame("R", m.getParams()[0], m);
    Motion m2 = new Motion(r1, null, 11, 100, 100,
            75, 89, 255, 0 ,0);
    am.insertKeyFrame("R", m2.getParams()[0], m2);
    am.deleteKeyFrame("R", 10);
    assertEquals(11, am.getKeyFrame("R").get(1).getParams()[0]);
  }

  @Test
  public void modifyKeyFrame() {
    am = new AnimationModel(0, 0, 500, 500);
    am.insertElement("R", "rectangle");
    am.motion("R", 5, 200, 200, 50, 100, 255, 0 ,0,
            10, 200, 200, 54 ,246, 255, 0, 0);
    Motion m = new Motion(r1, null, 2, 100, 100,
            75, 89, 255, 0 ,0);
    am.insertKeyFrame("R", m.getParams()[0], m);
    Motion m2 = new Motion(r1, null, 10, 100, 100,
            100, 67, 255, 0 ,0);
    am.insertKeyFrame("R", m2.getParams()[0], m2);
    am.editKeyFrame("R", 10, m2);
    assertEquals(100, am.getKeyFrame("R").get(1).getParams()[3]);
    assertEquals(67, am.getKeyFrame("R").get(1).getParams()[4]);
  }

}
