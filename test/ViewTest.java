import org.junit.Test;

import java.awt.Color;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cs3500.IAnimation;
import cs3500.IElement;
import cs3500.IView;
import cs3500.elements.Ellipse;
import cs3500.elements.Posn;
import cs3500.elements.Rectangle;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.view.AbstractTextView;
import cs3500.animator.view.ViewCreator;
import cs3500.animator.view.svgview.SVGView;
import cs3500.animator.view.textview.TextView;
import cs3500.animator.view.textview.TextViewSysOut;
import cs3500.animator.view.visualview.VisualView;

/**
 * Tests for views.
 */
public class ViewTest {

  private IAnimation am;
  private IView view;
  private IElement r1 = new Rectangle("R", Color.RED,
          new Posn(200,200), 100,50);
  private IElement e1 = new Ellipse("C", Color.BLUE,
          new Posn(440, 70), 60, 120);

  @Test
  public void testTextView() {
    am = new AnimationModel(0,0,500,500);
    am.insertElement("R", "rectangle");
    am.insertElement("C", "ellipse");
    am.motion("R", 1, 200,200, 10,50,255,0,0,
            3, 200,200,10,50,255,0,0);
    am.motion("R", 3, 200,200, 10,50,255,0,0,
            4, 300,300,10,50,255,0,0);
    am.motion("C", 1, 100,100, 10,50,0,0,255,
            3, 100,100,10,50,0,0,255);
    am.motion("C", 3, 100,100, 10,50,0,0,255,
            4, 100,100,20,100,0,0,255);
    AbstractTextView v = new TextView(am, "out.txt");
    assertEquals("canvas 0 0 500 500\n"
            + "shape R rectangle\n"
            + "motion R 1 200 200 10 50 255 0 0 3 200 200 10 50 255 0 0\n"
            + "motion R 3 200 200 10 50 255 0 0 4 300 300 10 50 255 0 0\n"
            + "\n"
            + "shape C ellipse\n"
            + "motion C 1 100 100 10 50 0 0 255 3 100 100 10 50 0 0 255\n"
            + "motion C 3 100 100 10 50 0 0 255 4 100 100 20 100 0 0 255\n\n",
            v.getVerboseAnimation());
    v.execute();
    assertTrue(Files.exists(Paths.get("out.txt")));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTextViewMalformedOutputFile() {
    am = new AnimationModel(0,0,500,500);
    am.insertElement("R", "rectangle");
    am.insertElement("C", "ellipse");
    am.motion("R", 1, 200,200, 10,50,255,0,0,
            3, 200,200,10,50,255,0,0);
    am.motion("C", 1, 100,100, 10,50,0,0,255,
            3, 100,100,10,50,0,0,255);
    AbstractTextView v = new TextView(am, "");
    v.execute();
  }

  @Test
  public void testTextViewSystemOut() {
    am = new AnimationModel(0,0,500,500);
    am.insertElement("R", "rectangle");
    am.insertElement("C", "ellipse");
    am.motion("R", 1, 200,200, 10,50,255,0,0,
            3, 200,200,10,50,255,0,0);
    am.motion("R", 3, 200,200, 10,50,255,0,0,
            4, 300,300,10,50,255,0,0);
    am.motion("C", 1, 100,100, 10,50,0,0,255,
            3, 100,100,10,50,0,0,255);
    am.motion("C", 3, 100,100, 10,50,0,0,255,
            4, 100,100,20,100,0,0,255);
    IView v = new ViewCreator().create("text",am,"System.out",1);
    assertEquals(TextViewSysOut.class, v.getClass());
    AbstractTextView vw = new TextViewSysOut(am);
    assertEquals("canvas 0 0 500 500\n"
                    + "shape R rectangle\n"
                    + "motion R 1 200 200 10 50 255 0 0 3 200 200 10 50 255 0 0\n"
                    + "motion R 3 200 200 10 50 255 0 0 4 300 300 10 50 255 0 0\n"
                    + "\n"
                    + "shape C ellipse\n"
                    + "motion C 1 100 100 10 50 0 0 255 3 100 100 10 50 0 0 255\n"
                    + "motion C 3 100 100 10 50 0 0 255 4 100 100 20 100 0 0 255\n\n",
            vw.getVerboseAnimation());
  }

  @Test
  public void testTextViewEmptyAnimation() {
    am = new AnimationModel(0,0,500,500);
    AbstractTextView v = new TextView(am, "out.txt");
    assertEquals("canvas 0 0 500 500\n", v.getVerboseAnimation());
  }

  @Test
  public void testSVGView() {
    am = new AnimationModel(0,0,500,500);
    am.insertElement("R", "rectangle");
    am.insertElement("C", "ellipse");
    am.motion("R", 1, 200,200, 10,50,255,0,0,
            3, 200,200,10,50,255,0,0);
    SVGView v = new SVGView(am, "out.txt", 1);
    assertEquals(svg(), v.getSVG());
    v.execute();
    assertTrue(Files.exists(Paths.get("out.txt")));
  }

  private String svg() {
    return "<svg width=\"500\" height=\"500\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\"> \n"
            + "<rect id=\"R\" x=\"200\" y=\"10 \" width=\"50\" height=\"255\"\n"
            + "        fill=\"rgb(0,0,3)\"  >\n"
            + "<animate attributeName=\"x\" attributeType=\"XML\"\n"
            + "             begin=\"0ms\" dur=\"0ms\" fill=\"freeze\" from=\"0\" to=\"0\" />\n"
            + "<animate attributeName=\"y\" attributeType=\"XML\"\n"
            + "             begin=\"0ms\" dur=\"0ms\" fill=\"freeze\" from=\"0\" to=\"0\" />\n"
            + "<animate attributeName=\"width\" attributeType=\"XML\"\n"
            + "             begin=\"0ms\" dur=\"0ms\" fill=\"freeze\" from=\"0\" to=\"0\" />\n"
            + "<animate attributeName=\"height\" attributeType=\"XML\"\n"
            + "             begin=\"0ms\" dur=\"0ms\" fill=\"freeze\" from=\"0\" to=\"0\" />\n"
            + "<animate attributeName=\"fill\" attributeType=\"CSS\"\n"
            + "           from=\"rgb(0,0,0)\" to=\"rgb(0,0,0)\"\n"
            + "           begin=\"0ms\" dur=\"0ms\" fill=\"freeze\" />\n"
            + "<animate attributeName=\"x\" attributeType=\"XML\"\n"
            + "             begin=\"200000ms\" dur=\"0ms\" "
            + "fill=\"freeze\" from=\"200\" to=\"200\" />\n"
            + "<animate attributeName=\"y\" attributeType=\"XML\"\n"
            + "             begin=\"200000ms\" dur=\"0ms\" "
            + "fill=\"freeze\" from=\"10\" to=\"10\" />\n"
            + "<animate attributeName=\"width\" attributeType=\"XML\"\n"
            + "             begin=\"200000ms\" dur=\"0ms\" "
            + "fill=\"freeze\" from=\"50\" to=\"50\" />\n"
            + "<animate attributeName=\"height\" attributeType=\"XML\"\n"
            + "             begin=\"200000ms\" dur=\"0ms\" "
            + "fill=\"freeze\" from=\"255\" to=\"255\" />\n"
            + "<animate attributeName=\"fill\" attributeType=\"CSS\"\n"
            + "           from=\"rgb(0,0,3)\" to=\"rgb(0,0,0)\"\n"
            + "           begin=\"200000ms\" dur=\"0ms\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "</svg>";
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSVGViewMalformedOutputFile() {
    am = new AnimationModel(0,0,500,500);
    am.insertElement("R", "rectangle");
    am.insertElement("C", "ellipse");
    am.motion("R", 1, 200,200, 10,50,255,0,0,
            3, 200,200,10,50,255,0,0);
    am.motion("C", 1, 100,100, 10,50,0,0,255,
            3, 100,100,10,50,0,0,255);
    AbstractTextView v = new SVGView(am, "", 1);
    v.execute();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSVGViewSpeedLessThan1() {
    am = new AnimationModel(0,0,500,500);
    IView v = new ViewCreator().create("svg", am,"text.txt",0);
  }

  @Test
  public void testSVGViewEmptyAnimation() {
    am = new AnimationModel(0,0,500,500);
    SVGView v = new SVGView(am, "out.txt", 1);
    assertEquals("<svg width=\"500\" height=\"500\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\"> \n"
            + "</svg>", v.getSVG());
  }

  @Test
  public void testFactoryViewText() {
    am = new AnimationModel(0,0,500,500);
    IView v = new ViewCreator().create("text",am,"text.txt",1);
    assertEquals(TextView.class, v.getClass());
  }

  @Test
  public void testFactoryViewTextSysOut() {
    am = new AnimationModel(0,0,500,500);
    IView v = new ViewCreator().create("text",am,"System.out",1);
    assertEquals(TextViewSysOut.class, v.getClass());
  }

  @Test
  public void testFactoryViewSVG() {
    am = new AnimationModel(0,0,500,500);
    IView v = new ViewCreator().create("svg", am,"text.svg",1);
    assertEquals(SVGView.class, v.getClass());
  }

  @Test
  public void testFactoryViewVisual() {
    am = new AnimationModel(0,0,500,500);
    IView v = new ViewCreator().create("visual",am,"text.txt",1);
    assertEquals(VisualView.class, v.getClass());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFactoryViewDefault() {
    am = new AnimationModel(0,0,500,500);
    IView v = new ViewCreator().create("abc",am,"text.txt",1);
  }
}
