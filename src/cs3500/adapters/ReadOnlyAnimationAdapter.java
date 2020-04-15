package cs3500.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import cs3500.ReadOnlyAnimation;
import cs3500.animator.view.provider.AnimationBackground;
import cs3500.animator.view.provider.AnimationShape;
import cs3500.animator.view.provider.EllipseShape;
import cs3500.animator.view.provider.Posn2D;
import cs3500.animator.view.provider.ReadOnlyAnimationModel;
import cs3500.animator.view.provider.RectangleShape;
import cs3500.animator.view.provider.ShapeColor;
import cs3500.motions.Motion;

public class ReadOnlyAnimationAdapter implements ReadOnlyAnimationModel {
  ReadOnlyAnimation a;
  public ReadOnlyAnimationAdapter(ReadOnlyAnimation a) {
    this.a = a;
  }

  @Override
  public String getAnimation() {
    return a.getVerboseAnimation();
  }

  @Override
  public AnimationShape getShape(int time, String name) {
    return this.getTimeline().get(name).get(time);
  }

  @Override
  public List<AnimationShape> getShape(int time) {
    List<AnimationShape> ls = new ArrayList<>();
    for (String name : this.getTimeline().keySet()) {
      if (this.getTimeline().get(name).containsKey(time)) {
        ls.add(this.getTimeline().get(name).get(time).showShape(time));
      }
    }
    return ls;
  }

  @Override
  public AnimationBackground getBackground() {
    return new AnimationBackground(a.getX(), a.getY(), a.getWidth(), a.getHeight());
  }

  @Override
  public LinkedHashMap<String, String> getExistingShape() {
    Map<String, String> existingShapes = new LinkedHashMap<>();
    for (String key : this.getTimeline().keySet()) {
      Map.Entry<Integer, AnimationShape> entry = this.getTimeline()
              .get(key).entrySet().iterator().next();
      existingShapes.put(key, entry.getValue().getType());
    }
    return null;
  }

  @Override
  public HashMap<String, TreeMap<Integer, AnimationShape>> getTimeline() {
    HashMap<String, TreeMap<Integer, AnimationShape>> timeline = new HashMap<>();
    for (String key : a.getElements().keySet()) {
      timeline.put(key, new TreeMap<>());
      for (Motion m : a.getKeyFrame(key)) {
        AnimationShape as;
        if (a.getElement(key).getType().equals("rectangle")) {
          as = new RectangleShape(new Posn2D(m.getParams()[1], m.getParams()[2]),
                  new ShapeColor(m.getParams()[5], m.getParams()[6], m.getParams()[7]),
                  m.getParams()[4], m.getParams()[3]);
        }
        else {
          as = new EllipseShape(new Posn2D(m.getParams()[1], m.getParams()[2]),
                  new ShapeColor(m.getParams()[5], m.getParams()[6], m.getParams()[7]),
                  m.getParams()[4], m.getParams()[3]);
        }
        timeline.get(key).put(m.getParams()[0], as);
      }
    }
    return getTimeline();
  }
}
