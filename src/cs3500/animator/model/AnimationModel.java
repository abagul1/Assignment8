package cs3500.animator.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import cs3500.IAnimation;
import cs3500.IElement;
import cs3500.animator.util.AnimationBuilder;
import cs3500.elements.Ellipse;
import cs3500.elements.Posn;
import cs3500.elements.Rectangle;
import cs3500.motions.Motion;


/**
 * Represents the Animation with all the elements and operations.
 */
public class AnimationModel implements IAnimation {

  private Map<String, IElement> elements;
  private List<Motion> motions;
  private Map<String, List<String>> verboseOps;
  private Map<String, String> declaredShapes;
  private Map<String, List<Motion>> keyframes;

  private int currentTick;

  private final int windowWidth;
  private final int windowHeight;
  private final int leftX;
  private final int topY;

  /**
   * Constructor for animation model.
   * @param width width of animation panel
   * @param height height of animation panel
   */
  public AnimationModel(int x, int y, int width, int height) {
    elements = new LinkedHashMap<>();
    verboseOps = new LinkedHashMap<>();
    declaredShapes = new LinkedHashMap<>();
    keyframes = new LinkedHashMap<>();
    motions = new ArrayList<>();
    currentTick = 0;
    windowWidth = width;
    windowHeight = height;
    leftX = x;
    topY = y;
  }

  @Override
  public void motion(String name,
                     int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1,
                     int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {

    this.checkNotNull();
    if (!elements.containsKey(name)) {
      throw new IllegalArgumentException("Name doesnt exist");
    }

    this.setKeyframes(name, t2, x2, y2, w2, h2, r2, g2, b2);
    this.addVerboseMotion(name, t1, x1, y1, w1, h1, r1, g1, b1, t2, x2, y2, w2, h2, r2, g2, b2);
  }

  /**
   * Adds a keyframe to the map of key frames.
   * @param name name of shape
   * @param t ending tick
   * @param x ending x position
   * @param y ending y position
   * @param w ending width
   * @param h ending height
   * @param r ending red
   * @param g ending green
   * @param b ending blue
   */
  private void setKeyframes(String name, int t, int x, int y, int w, int h,
                           int r, int g, int b) {
    int listSize = keyframes.get(name).size();
    if (listSize > 0) {
      keyframes.get(name).add(new Motion(elements.get(name), keyframes.get(name).get(listSize - 1),
              t, x, y, w, h, r, g, b));
      keyframes.get(name).get(listSize - 1).setNextMotion(keyframes.get(name).get(listSize));
    } else {
      keyframes.get(name).add(new Motion(elements.get(name), null,
              t, x, y, w, h, r, g, b));
    }
  }


  /**
   * Adds a verbose description of the motion.
   * @param id element id
   * @param t1 tick to start
   * @param x1 starting x pos
   * @param y1 starting y pos
   * @param w1 starting width
   * @param h1 starting height
   * @param r1 starting red value
   * @param g1 starting green value
   * @param b1 starting blue value
   * @param t2 ending tick
   * @param x2 ending x value
   * @param y2 ending y value
   * @param w2 ending width
   * @param h2 ending height
   * @param r2 ending red value
   * @param g2 ending green value
   * @param b2 ending blue value
   */
  private void addVerboseMotion(String id,
                                int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1,
                                int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    if (verboseOps == null) {
      throw new IllegalStateException("Error: Verbose Ops is null");
    }

    StringBuilder str = new StringBuilder();
    str.append("motion").append(" ").append(id)
            .append(" ").append(t1)
            .append(" ").append(x1)
            .append(" ").append(y1)
            .append(" ").append(w1)
            .append(" ").append(h1)
            .append(" ").append(r1)
            .append(" ").append(g1)
            .append(" ").append(b1)
            .append(" ").append(t2)
            .append(" ").append(x2)
            .append(" ").append(y2)
            .append(" ").append(w2)
            .append(" ").append(h2)
            .append(" ").append(r2)
            .append(" ").append(g2)
            .append(" ").append(b2);

    if (!verboseOps.containsKey(id)) {
      throw new IllegalStateException("Element can't be moved before it exists");
    }
    verboseOps.get(id).add(str.toString());
  }

  /**
   * Checks that the elements and operations fields are not null.
   */
  private void checkNotNull() {
    if (elements == null) {
      throw new IllegalStateException("Error: Element map is null");
    }
    if (motions == null) {
      throw new IllegalStateException("Error: Motions is null");
    }
  }

  @Override
  public void insertElement(String id, String type) {
    if (id == null || type == null) {
      throw new IllegalArgumentException("Cannot add a null element to the animation");
    }
    if (elements == null) {
      throw new IllegalStateException("Error: Element map is null");
    }
    if (motions == null) {
      throw new IllegalStateException("Error: motions is null");
    }
    if (declaredShapes.containsKey(id)) {
      throw new IllegalArgumentException("Cannot have duplicate elements");
    }

    switch (type) {
      case "rectangle":
        elements.put(id, new Rectangle(id, new Color(0, 0, 0, 0),
                new Posn(0, 0),
                0, 0));
        break;
      case "ellipse":
        elements.put(id, new Ellipse(id, new Color(0, 0, 0,0), new Posn(0, 0),
                0,
                0));
        break;
      default:
        throw new IllegalArgumentException("This type of shape doesn't exist: "
                + type);

    }
    keyframes.put(id, new ArrayList<>());
    declaredShapes.put(id, type);
    this.addVerboseInsert(id, type);
  }

  @Override
  public void deleteElement(String id) {
    elements.remove(id);
    keyframes.remove(id);
    declaredShapes.remove(id);
    verboseOps.remove(id);
  }

  /**
   * Create a textual description of the insert operation.
   * @param id element id
   * @param type type of element
   */
  private void addVerboseInsert(String id, String type) {
    if (verboseOps == null) {
      throw new IllegalStateException("Error: Verbose Ops is null");
    }

    StringBuilder str = new StringBuilder();
    str.append("shape ").append(id).append(" ").append(type);

    if (verboseOps.containsKey(id)) {
      throw new IllegalArgumentException("Element ID already exists");
    }
    else {
      verboseOps.put(id, new ArrayList<>());
      verboseOps.get(id).add(str.toString());
    }
  }

  @Override
  public String getVerboseAnimation() {
    StringBuilder str = new StringBuilder();
    for (String id : verboseOps.keySet()) {
      for (String i : verboseOps.get(id)) {
        str.append(i).append("\n");
      }
      str.append("\n");
    }

    return str.toString();
  }

  @Override
  public void executeOperations() {
    while (checkDone()) {
      for (String key : keyframes.keySet()) {
        for (Iterator<Motion> iterator = keyframes.get(key).iterator(); iterator.hasNext(); ) {
          Motion m = iterator.next();
          if (m.getPrevMotion() == null) {
            m.fire(currentTick);
          }
          else if (m.getPrevMotion().getParams()[0] <= currentTick
                  && m.getParams()[0] >= currentTick) {
            if (m.getPrevMotion().getParams()[0] == m.getParams()[0]) {
              throw new IllegalArgumentException("Cannot have two keyframes overlap");
            }
            m.fire(currentTick);
          }
          if (m.getParams()[0] == currentTick - 1) {
            iterator.remove();
          }
        }
      }
      currentTick++;
    }
  }

  /**
   * Checks if the animation has finished.
   * @return true or false
   */
  private boolean checkDone() {
    for (String key : keyframes.keySet()) {
      if (!keyframes.get(key).isEmpty()) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void executeOperationsUntil(int tick) {
    List<Motion> currentMotions = new ArrayList<>();
    for (currentTick = 0; currentTick < tick; currentTick++) {
      if (!this.checkDone()) {
        break;
      }
      for (String key : keyframes.keySet()) {
        for (Iterator<Motion> iterator = keyframes.get(key).iterator(); iterator.hasNext(); ) {
          Motion m = iterator.next();
          if (m.getPrevMotion() == null) {
            m.fire(currentTick);
          }
          else if (m.getPrevMotion().getParams()[0] <= currentTick
                  && m.getParams()[0] >= currentTick) {
            if (m.getPrevMotion().getParams()[0] == m.getParams()[0]) {
              throw new IllegalArgumentException("Cannot have two keyframes overlap");
            }
            m.fire(currentTick);
          }
          if (m.getParams()[0] == currentTick) {
            iterator.remove();
          }
        }
      }
      currentMotions.clear();
      currentTick++;
    }
  }

  @Override
  public void executeOneTick() {
    for (String key : keyframes.keySet()) {
      for (Iterator<Motion> iterator = keyframes.get(key).iterator(); iterator.hasNext(); ) {
        Motion m = iterator.next();
        if (m.getPrevMotion() == null) {
          m.fire(currentTick);
        }
        else if (m.getPrevMotion().getParams()[0] <= currentTick
                && m.getParams()[0] >= currentTick) {
          if (m.getPrevMotion().getParams()[0] == m.getParams()[0]) {
            throw new IllegalArgumentException("Cannot have two keyframes overlap");
          }
          m.fire(currentTick);
        }
      }
    }
    currentTick++;
  }

  @Override
  public String[] getShapes() {
    String[] str = new String[declaredShapes.size()];
    int i = 0;
    for (String key : declaredShapes.keySet()) {
      str[i] = key;
      i++;
    }
    return str;
  }

  @Override
  public List<Motion> getKeyFrame(String id) {
    return keyframes.get(id);
  }


  @Override
  public IElement getElement(String id) {
    try {
      return elements.get(id);
    } catch (NullPointerException npe) {
      throw new IllegalArgumentException("Element does not exist");
    }
  }

  @Override
  public Map<String,IElement> getElements() {
    return elements;
  }

  @Override
  public List<String> getMotionsForElement(String id) {
    return verboseOps.get(id);
  }

  @Override
  public int getHeight() {
    return windowHeight;
  }

  @Override
  public int getWidth() {
    return windowWidth;
  }

  @Override
  public int getX() {
    return leftX;
  }

  @Override
  public int getY() {
    return topY;
  }

  @Override
  public void resetAnimation() {
    for (String name : elements.keySet()) {
      Color c = new Color(0,0,0,0);
      elements.get(name).setColor(c);
    }
    this.currentTick = 0;
  }

  @Override
  public void insertKeyFrame(String name, int tick, Motion m) {
    if (keyframes.get(name).isEmpty()) {
      keyframes.get(name).add(m);
      addVerboseMotion(name, m.getParams()[0], m.getParams()[1],
              m.getParams()[2],  m.getParams()[3], m.getParams()[4], m.getParams()[5],
              m.getParams()[6], m.getParams()[7], m.getParams()[0], m.getParams()[1],
              m.getParams()[2],  m.getParams()[3], m.getParams()[4], m.getParams()[5],
              m.getParams()[6], m.getParams()[7]);
      return;
    }
    for (int i = 0; i < keyframes.get(name).size(); i++) {
      if (keyframes.get(name).get(keyframes.get(name).size() - 1).getParams()[0] < tick) {
        m.setPrevMotion(keyframes.get(name).get(keyframes.get(name).size() - 1));
        keyframes.get(name).get(keyframes.get(name).size() - 1).setNextMotion(m);
        keyframes.get(name).add(m);
        addVerboseMotion(name, m.getParams()[0], m.getParams()[1],
                  m.getParams()[2],  m.getParams()[3], m.getParams()[4], m.getParams()[5],
                  m.getParams()[6], m.getParams()[7], m.getPrevMotion().getParams()[0],
                  m.getPrevMotion().getParams()[1], m.getPrevMotion().getParams()[2],
                  m.getPrevMotion().getParams()[3], m.getPrevMotion().getParams()[4],
                  m.getPrevMotion().getParams()[5], m.getPrevMotion().getParams()[6],
                  m.getPrevMotion().getParams()[7]);
        break;
      }
      else if (keyframes.get(name).get(i).getParams()[0] > tick) {
        if (keyframes.get(name).get(i).getPrevMotion() == null) {
          keyframes.get(name).get(i).setPrevMotion(m);
          m.setNextMotion(keyframes.get(name).get(i));
          keyframes.get(name).add(i, m);
          addVerboseMotion(name, m.getParams()[0], m.getParams()[1],
                  m.getParams()[2],  m.getParams()[3], m.getParams()[4], m.getParams()[5],
                  m.getParams()[6], m.getParams()[7], m.getParams()[0], m.getParams()[1],
                  m.getParams()[2],  m.getParams()[3], m.getParams()[4], m.getParams()[5],
                  m.getParams()[6], m.getParams()[7]);
          break;
        }
        else {
          keyframes.get(name).get(i).setPrevMotion(m);
          keyframes.get(name).get(i - 1).setNextMotion(m);
          m.setPrevMotion( keyframes.get(name).get(i - 1));
          m.setNextMotion(keyframes.get(name).get(i));
          keyframes.get(name).add(i, m);
          addVerboseMotion(name, m.getParams()[0], m.getParams()[1],
                  m.getParams()[2],  m.getParams()[3], m.getParams()[4], m.getParams()[5],
                  m.getParams()[6], m.getParams()[7], m.getPrevMotion().getParams()[0],
                  m.getPrevMotion().getParams()[1], m.getPrevMotion().getParams()[2],
                  m.getPrevMotion().getParams()[3], m.getPrevMotion().getParams()[4],
                  m.getPrevMotion().getParams()[5], m.getPrevMotion().getParams()[6],
                  m.getPrevMotion().getParams()[7]);
        }
        break;
      }
    }
  }

  @Override
  public void deleteKeyFrame(String name, int tick) {
    Iterator<Motion> it = keyframes.get(name).iterator();
    while (it.hasNext()) {
      Motion m = it.next();
      if (m.getParams()[0] == tick) {
        if (m.getPrevMotion() == null && m.getNextMotion() == null) {
          it.remove();
          deleteVerboseMotion(name, tick);
        }
        else if (m.getPrevMotion() == null) {
          m.getNextMotion().setPrevMotion(null);
          it.remove();
          deleteVerboseMotion(name, tick);
        }
        else if (m.getNextMotion() == null) {
          m.getPrevMotion().setNextMotion(null);
          it.remove();
          deleteVerboseMotion(name, tick);
        }
        else {
          m.getNextMotion().setPrevMotion(m.getPrevMotion());
          m.getPrevMotion().setNextMotion(m.getNextMotion());
          it.remove();
          deleteVerboseMotion(name, tick);
        }
      }
    }
  }

  /**
   * Deletes a key frame from the verbose motion.
   * @param name name of shape
   * @param tick tick of keyframe to be deleted
   */
  private void deleteVerboseMotion(String name, int tick) {
    Iterator<String> it = verboseOps.get(name).iterator();
    while (it.hasNext()) {
      String str = it.next();
      int[] motions = motionParser(str);
      if (motions[8] == tick) {
        it.remove();
      }
    }
  }

  /**
   * Parses a single motion command and stores all the key values in an array.
   * @param s string to parse
   * @return an array of shape variables
   */
  private int[] motionParser(String s) {
    int[] arr = new int[16];
    String str = s.substring(8);
    Scanner scan = new Scanner(str);
    int i = 0;
    scan.next();
    while (scan.hasNextInt()) {
      arr[i] = scan.nextInt();
      i++;
    }
    return arr;
  }

  @Override
  public void editKeyFrame(String name, int tick, Motion m) {
    for (Motion motions : keyframes.get(name)) {
      if (motions.getParams()[0] == tick) {
        m.setNextMotion(motions.getNextMotion());
        m.setPrevMotion(motions.getPrevMotion());
        keyframes.get(name).set(keyframes.get(name).indexOf(motions), m);
        addVerboseMotion(name, m.getParams()[0], m.getParams()[1],
                m.getParams()[2],  m.getParams()[3], m.getParams()[4], m.getParams()[5],
                m.getParams()[6], m.getParams()[7], m.getPrevMotion().getParams()[0],
                m.getPrevMotion().getParams()[1], m.getPrevMotion().getParams()[2],
                m.getPrevMotion().getParams()[3], m.getPrevMotion().getParams()[4],
                m.getPrevMotion().getParams()[5], m.getPrevMotion().getParams()[6],
                m.getPrevMotion().getParams()[7]);
        verboseOps.get(name).set(keyframes.get(name).indexOf(motions) + 1,
                verboseOps.get(name).get(verboseOps.get(name).size() - 1));
        verboseOps.get(name).remove(verboseOps.get(name).size() - 1);
      }
    }
  }

  @Override
  public boolean isDone(int currentTick) {
    for (String key : keyframes.keySet()) {
      if (!keyframes.get(key).isEmpty()
              && keyframes.get(key).get(keyframes.get(key).size() - 1)
              .getParams()[0] > currentTick) {
        return false;
      }
    }
    return true;
  }

  /**
   * Builder class to create an animation.
   */
  public static final class Builder implements AnimationBuilder<IAnimation> {

    private IAnimation modelToBuild;

    @Override
    public IAnimation build() {
      if (isModelToBuildNull()) {
        throw new IllegalStateException("Builder model has not been initialized");
      }
      return modelToBuild;
    }

    @Override
    public AnimationBuilder<IAnimation> setBounds(int x, int y, int width, int height) {
      this.modelToBuild = new AnimationModel(x, y, width, height);
      return this;
    }

    @Override
    public AnimationBuilder<IAnimation> declareShape(String name, String type) {
      if (isModelToBuildNull()) {
        throw new IllegalStateException("Builder model has not been initialized");
      }
      this.modelToBuild.insertElement(name, type);
      return this;
    }

    @Override
    public AnimationBuilder<IAnimation> addMotion(String name, int t1, int x1, int y1, int w1,
                                                  int h1, int r1, int g1, int b1, int t2, int x2,
                                                  int y2, int w2, int h2, int r2, int g2, int b2) {
      if (isModelToBuildNull()) {
        throw new IllegalStateException("Builder model has not been initialized");
      }
      this.modelToBuild.motion(name, t1, x1, y1, w1, h1, r1, g1, b1,
                                     t2, x2, y2, w2, h2, r2, g2, b2);
      return this;
    }

    @Override
    public AnimationBuilder<IAnimation> addKeyframe(String name, int t, int x, int y, int w, int h,
                                                    int r, int g, int b) {
      return this;
    }

    /**
     * Checks if model is null.
     * @return whether the model is null or not
     */
    private boolean isModelToBuildNull() {
      return modelToBuild == null;
    }
  }
}
