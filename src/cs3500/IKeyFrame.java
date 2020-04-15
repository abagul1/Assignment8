package cs3500;

import cs3500.motions.Motion;

/**
 * Interface to represent motions/Keyframes.
 */
public interface IKeyFrame {

  /**
   * Method to change elements when the motion is supposed to be executed.
   * @param currentTick current tick in the animation
   */
  void fire(int currentTick);

  /**
   * Gets the parameters of the keyframe.
   * Array order t,x,y,w,h,r,g,b.
   * @return an array of parameters
   */
  int[] getParams();

  /**
   * Set the next keyframe of the current keyframe.
   * @param m next motion
   */
  void setNextMotion(Motion m);

  /**
   * Set the previous keyframe of current the key frame.
   * @param m previous motion
   */
  void setPrevMotion(Motion m);

  /**
   * Get the previous keyframe of current the key frame.
   * @return previous motion
   */
  Motion getPrevMotion();

  /**
   * Get the next keyframe of the current key frame.
   * @return next motion
   */
  Motion getNextMotion();

}
