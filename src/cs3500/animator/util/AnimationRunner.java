package cs3500.animator.util;

import java.io.FileNotFoundException;
import java.io.FileReader;

import cs3500.IAnimation;
import cs3500.IController;
import cs3500.IView;
import cs3500.animator.controller.AnimationController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.view.ViewCreator;

/**
 * Main Class to run the program from.
 */
public final class AnimationRunner {

  /**
   * Main method to run program and serve as defacto controller.
   * @param args input arguments
   */
  public static void main(String[] args) {

    String inputFileName = "";
    String outputFileName = "System.out";
    String speed = "1";
    String viewType = "";
    int tempo;

    for (int i = 0; i < args.length - 1; i++)  {
      switch (args[i]) {
        case "-in":
          inputFileName = args[i + 1];
          i++;
          break;
        case "-out":
          outputFileName = args[i + 1];
          i++;
          break;
        case "-view":
          viewType = args[i + 1];
          i++;
          break;
        case "-speed":
          speed = args[i + 1];
          i++;
          break;
        default:
          throw new IllegalStateException("Invalid argument");
      }
    }

    try {
      tempo = Integer.parseInt(speed);
    }
    catch (NumberFormatException e) {
      throw new IllegalArgumentException("Tempo has to be a integer");
    }

    //Execute Program
    AnimationReader ar = new AnimationReader();
    AnimationBuilder<IAnimation> ab = new AnimationModel.Builder();
    ViewCreator vc = new ViewCreator();
    try {
      IAnimation a = ar.parseFile(new FileReader(inputFileName),
              ab);
      IView view = vc.create(viewType, a, outputFileName, tempo);
      IController controller = new AnimationController(view);
      controller.playAnimation(a, viewType, tempo);
    }
    catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File is invalid");
    }

  }
}
