package cs3500.adapters;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

import cs3500.animator.view.provider.AnimationBackground;
import cs3500.animator.view.provider.AnimationShape;
import cs3500.animator.view.provider.ReadOnlyAnimationModel;

public class ReadOnlyAnimationAdapter implements ReadOnlyAnimationModel {

  @Override
  public String getAnimation() {
    return null;
  }

  @Override
  public AnimationShape getShape(int time, String name) {
    return null;
  }

  @Override
  public List<AnimationShape> getShape(int time) {
    return null;
  }

  @Override
  public AnimationBackground getBackground() {
    return null;
  }

  @Override
  public LinkedHashMap<String, String> getExistingShape() {
    return null;
  }

  @Override
  public HashMap<String, TreeMap<Integer, AnimationShape>> getTimeline() {
    return null;
  }
}
