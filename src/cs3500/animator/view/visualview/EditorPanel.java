package cs3500.animator.view.visualview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import cs3500.IAnimation;
import cs3500.animator.view.svgview.SVGView;
import cs3500.animator.view.textview.TextView;
import cs3500.motions.Motion;

/**
 * Editor panel for to support edit functionality that the user can do with an animation.
 * Handles the redrawing of panels on the frame, and some of the logic of editing.
 */
public class EditorPanel extends JPanel {
  private IAnimation rom;
  private String selectedShape;

  /**
   * Constructor for a editor view.
   * @param m model to use
   */
  public EditorPanel(IAnimation m) {
    super();
    this.rom = m;
  }

  /**
   * Window toggle between the animation screen and the edit screen.
   * @param wt window type
   */
  public void setWindow(WindowType wt) {
    switch (wt) {
      case ANIMATION:
        this.removeAll();
        this.setAnimationWindow();
        break;
      case SHAPEMENU:
        this.setShapeWindow();
        break;
      default:
        throw new IllegalArgumentException("Window type doesn't exist");
    }
  }

  /**
   * Method to redraw the panel, and allow the user to select from all the shapes in the animation.
   * Allows the user to edit keyframes of a specific shape, create a new shape, or delete a
   * shape.
   */
  private void setShapeWindow() {
    this.removeAll();
    JPanel shapePanel = new JPanel();
    this.setBackground(Color.black);
    JButton edit = new JButton("Edit KeyFrames");
    JButton create = new JButton("Create Shape");
    JButton delete = new JButton("Delete Shape");

    String[] shapes = rom.getShapes();
    JList<String> shapeList = new JList(shapes);
    shapeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    shapeList.setVisibleRowCount(8);
    JScrollPane shapeScroll = new JScrollPane(shapeList,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    MouseListener ml = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        String data;
        if (shapeList.getSelectedIndex() != -1 && e.getSource() == edit) {
          data = shapeList.getSelectedValue();
          selectedShape = shapeList.getSelectedValue();
          List<Motion> keyFrames = rom.getKeyFrame(data);
          setKeyFramesWindow(keyFrames);
        }
        else if (e.getSource() == create) {
          createShape();
        }
        else if (shapeList.getSelectedIndex() != -1 && e.getSource() == delete) {
          data = shapeList.getSelectedValue();
          rom.deleteElement(data);
          finalScreen("Shape is deleted, click start to go back to the animation,"
                  + " or edit to continue editing.");
        }
      }
    };
    create.addMouseListener(ml);
    delete.addMouseListener(ml);
    edit.addMouseListener(ml);
    shapePanel.add(edit);
    shapePanel.add(create);
    shapePanel.add(delete);
    shapePanel.add(shapeScroll);
    shapePanel.repaint();
    this.add(shapePanel, Component.CENTER_ALIGNMENT);
    this.revalidate();
    this.repaint();
  }

  /**
   * Method allows the user to save their animation to a specified path.
   * @param speed of the current animation
   */
  public void setSaveWindow(int speed) {
    this.removeAll();
    this.setBackground(Color.WHITE);
    JButton save = new JButton("Save File");
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(save);
    JPanel fieldPanel = new JPanel();
    JPanel filePanel = new JPanel();
    JTextField fileName = new JTextField();
    fileName.setColumns(20);
    JLabel fileLabel = new JLabel("Output Path: ");
    filePanel.add(fileName, BorderLayout.CENTER);
    filePanel.add(fileLabel, BorderLayout.WEST);
    fieldPanel.add(filePanel);

    String[] m = {"SVG", "Text"};
    JList<String> saveType = new JList(m);
    saveType.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    saveType.setVisibleRowCount(2);
    fieldPanel.add(saveType);

    MouseListener ml = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (saveType.getSelectedIndex() != -1 && e.getSource() == save) {
          String name = fileName.getText();
          String t = saveType.getSelectedValue();
          switch (t) {
            case "SVG":
              SVGView svg = new SVGView(rom, name, speed);
              svg.execute();
              break;
            case "Text":
              TextView tv = new TextView(rom, name);
              tv.execute();
              break;
            default:
              throw new IllegalArgumentException("Save type is not valid");
          }
          finalScreen("Animation is saved, click start to go back to the animation,"
                  + " or edit to continue editing.");
        }
      }
    };
    save.addMouseListener(ml);
    this.add(fieldPanel, Component.CENTER_ALIGNMENT);
    this.add(buttonPanel, Component.CENTER_ALIGNMENT);
    this.revalidate();
    this.repaint();
  }

  /**
   * Method allows the user to create a new shape for the animation.
   */
  private void createShape() {
    this.removeAll();
    this.setBackground(Color.WHITE);
    JButton create = new JButton("Create Element");
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(create);
    JPanel fieldPanel = new JPanel();

    JPanel idPanel = new JPanel(new BorderLayout());
    JTextField id = new JTextField();
    id.setColumns(10);
    JLabel idLabel = new JLabel("Name: ");
    idPanel.add(id, BorderLayout.CENTER);
    idPanel.add(idLabel, BorderLayout.WEST);
    fieldPanel.add(idPanel);

    JPanel typePanel = new JPanel(new BorderLayout());
    JTextField type = new JTextField();
    type.setColumns(10);
    JLabel typeLabel = new JLabel("Type: ");
    typePanel.add(type, BorderLayout.CENTER);
    typePanel.add(typeLabel, BorderLayout.WEST);
    fieldPanel.add(typePanel);

    MouseListener ml = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getSource() == create) {
          String name = id.getText();
          String t = type.getText();
          rom.insertElement(name, t);
          finalScreen("Shape is inserted, click start to go back to the animation,"
                  + " or edit to continue editing.");
        }
      }
    };
    create.addMouseListener(ml);
    this.add(fieldPanel, Component.CENTER_ALIGNMENT);
    this.add(buttonPanel, Component.CENTER_ALIGNMENT);
    this.revalidate();
    this.repaint();
  }

  private void setAnimationWindow() {
    removeAll();
    this.setBackground(Color.WHITE);
    this.revalidate();
    this.repaint();
  }

  /**
   * Window that presents the user with all the keyframes of a given shape. Allows user
   * to choose what to do with the key frames, whether it be edit, insert, or delete.
   * @param lm list of key frames
   */
  private void setKeyFramesWindow(List<Motion> lm) {
    this.removeAll();
    this.setBackground(Color.WHITE);
    String[] m = new String[lm.size()];
    int i = 0;
    for (Motion motion : lm) {
      StringBuilder str = new StringBuilder();
      str.append("T: ").append(motion.getParams()[0]).append(" ")
              .append("X: ").append(motion.getParams()[1]).append(" ")
              .append("Y: ").append(motion.getParams()[2]).append(" ")
              .append("W: ").append(motion.getParams()[3]).append(" ")
              .append("H: ").append(motion.getParams()[4]).append(" ")
              .append("R: ").append(motion.getParams()[5]).append(" ")
              .append("G: ").append(motion.getParams()[6]).append(" ")
              .append("B: ").append(motion.getParams()[7]).append(" ");
      m[i] = str.toString();
      i++;
    }
    JList<String> keyFramesList = new JList(m);
    keyFramesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    keyFramesList.setVisibleRowCount(8);
    JScrollPane keyFrameScroll = new JScrollPane(keyFramesList,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    JPanel buttonPanel = new JPanel();
    JButton insert = new JButton("Insert");
    JButton edit = new JButton("Edit");
    JButton delete = new JButton("Delete");
    MouseListener ml = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        String data;
        if (e.getSource() == insert) {
          insertKeyFrame();
        }
        else if (keyFramesList.getSelectedIndex() != -1 && e.getSource() == edit) {
          data = keyFramesList.getSelectedValue();
          editKeyFrame(data);
        }
        else if (keyFramesList.getSelectedIndex() != -1 && e.getSource() == delete) {
          data = keyFramesList.getSelectedValue();
          deleteKeyFrame(data);
        }
      }
    };
    insert.addMouseListener(ml);
    edit.addMouseListener(ml);
    delete.addMouseListener(ml);
    buttonPanel.add(insert);
    buttonPanel.add(edit);
    buttonPanel.add(delete);
    this.add(keyFrameScroll, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.WEST);
    this.revalidate();
    this.repaint();
  }

  /**
   * Method to insert a key frame, called from the controller. Sets the new background.
   */
  private void insertKeyFrame() {
    this.removeAll();
    this.setBackground(Color.WHITE);
    this.drawInsertTextFields();
  }

  /**
   * Confirmation screen once something is edited.
   * @param message to be presented
   */
  private void finalScreen(String message) {
    this.removeAll();
    this.setBackground(Color.WHITE);
    JLabel m = new JLabel(message);
    this.add(m);
    this.revalidate();
    this.repaint();
  }

  /**
   * Method to edit a key frame, called from the controller. Sets the new background.
   * @param data is information about the keyframe to be edited
   */
  private void editKeyFrame(String data) {
    this.removeAll();
    this.setBackground(Color.WHITE);
    this.drawEditTextFields(data);
  }

  /**
   * Method to delete a key frame, and redraw view fields.
   * @param data is information about the keyframe to be deleted
   */
  private void deleteKeyFrame(String data) {
    String str = data.substring(3);
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == 'X') {
        str = str.substring(0, i - 1);
        int tick = Integer.parseInt(str);
        rom.deleteKeyFrame(selectedShape, tick);
        break;
      }
    }
    this.finalScreen("Keyframe is deleted, click start to go back to the animation,"
            + " or edit to continue editing.");
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;
    g2d.translate(-rom.getX(), -rom.getY());
    for (String key : rom.getElements().keySet()) {
      rom.getElement(key).paint(g2d);
    }
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(rom.getWidth(), rom.getHeight());
  }

  /**
   * Creates the fields for the user to input the parameters of the keyframe to be inserted.
   * Also draws the board to present this view, and calls the methods to insert the keyframe.
   */
  private void drawInsertTextFields() {
    JPanel fieldPanel = new JPanel();

    JPanel tickPanel = new JPanel(new BorderLayout());
    JTextField tick = new JTextField();
    tick.setColumns(4);
    JLabel tickLabel = new JLabel("T: ");
    tickPanel.add(tick, BorderLayout.CENTER);
    tickPanel.add(tickLabel, BorderLayout.WEST);
    fieldPanel.add(tickPanel);

    JPanel xPanel = new JPanel(new BorderLayout());
    JTextField xPos = new JTextField();
    xPos.setColumns(4);
    JLabel xPosLabel = new JLabel("X:");
    xPanel.add(xPos, BorderLayout.CENTER);
    xPanel.add(xPosLabel, BorderLayout.WEST);
    fieldPanel.add(xPanel);

    JPanel yPanel = new JPanel(new BorderLayout());
    JTextField yPos = new JTextField();
    yPos.setColumns(4);
    JLabel yPosLabel = new JLabel("Y:");
    yPanel.add(yPos, BorderLayout.CENTER);
    yPanel.add(yPosLabel, BorderLayout.WEST);
    fieldPanel.add(yPanel);

    JPanel wPanel = new JPanel(new BorderLayout());
    JTextField width = new JTextField();
    width.setColumns(4);
    JLabel widthLabel = new JLabel("W:");
    wPanel.add(width, BorderLayout.CENTER);
    wPanel.add(widthLabel, BorderLayout.WEST);
    fieldPanel.add(wPanel);

    JPanel hPanel = new JPanel(new BorderLayout());
    JTextField height = new JTextField();
    height.setColumns(4);
    JLabel heightLabel = new JLabel("H:");
    hPanel.add(height, BorderLayout.CENTER);
    hPanel.add(heightLabel, BorderLayout.WEST);
    fieldPanel.add(hPanel);

    JPanel rPanel = new JPanel(new BorderLayout());
    JTextField red = new JTextField();
    red.setColumns(4);
    JLabel redLabel = new JLabel("R:");
    rPanel.add(red, BorderLayout.CENTER);
    rPanel.add(redLabel, BorderLayout.WEST);
    fieldPanel.add(rPanel);

    JPanel gPanel = new JPanel(new BorderLayout());
    JTextField green = new JTextField();
    green.setColumns(4);
    JLabel greenLabel = new JLabel("G:");
    gPanel.add(green, BorderLayout.CENTER);
    gPanel.add(greenLabel, BorderLayout.WEST);
    fieldPanel.add(gPanel);

    JPanel bPanel = new JPanel(new BorderLayout());
    JTextField blue = new JTextField();
    blue.setColumns(4);
    JLabel blueLabel = new JLabel("B:");
    bPanel.add(blue, BorderLayout.CENTER);
    bPanel.add(blueLabel, BorderLayout.WEST);
    fieldPanel.add(bPanel);

    JButton insert = new JButton("Insert KeyFrame");
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(insert);
    MouseListener ml = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getSource() == insert) {
          Motion m = new Motion(rom.getElement(selectedShape), null,
                  Integer.parseInt(tick.getText()),
                  Integer.parseInt(xPos.getText()), Integer.parseInt(yPos.getText()),
                  Integer.parseInt(width.getText()), Integer.parseInt(height.getText()),
                  Integer.parseInt(red.getText()),
                  Integer.parseInt(green.getText()), Integer.parseInt(blue.getText()));
          rom.insertKeyFrame(selectedShape, Integer.parseInt(tick.getText()), m);
          finalScreen("Keyframe is inserted, click start to go back to the animation,"
                  + "or edit to continue editing.");
        }
      }
    };
    insert.addMouseListener(ml);
    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    this.add(fieldPanel, Component.CENTER_ALIGNMENT);
    this.add(buttonPanel, Component.CENTER_ALIGNMENT);
    this.revalidate();
    this.repaint();
  }

  /**
   * Creates the fields for the user to input the parameters of the keyframe to be edited.
   * Also draws the board to present this view, and calls the methods to edit the keyframe.
   * @param data of current key frame state
   */
  private void drawEditTextFields(String data) {
    JPanel fieldPanel = new JPanel();
    String str = data.substring(3);
    Motion motionToEdit = null;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == 'X') {
        str = str.substring(0, i - 1);
        int tick = Integer.parseInt(str);
        for (Motion m : rom.getKeyFrame(selectedShape)) {
          if (m.getParams()[0] == tick) {
            motionToEdit = m;
            break;
          }
        }
        break;
      }
    }

    JPanel tickPanel = new JPanel(new BorderLayout());
    JTextField tick = new JTextField();
    tick.setText(Integer.toString(motionToEdit.getParams()[0]));
    tick.setColumns(4);
    JLabel tickLabel = new JLabel("T: ");
    tickPanel.add(tick, BorderLayout.CENTER);
    tickPanel.add(tickLabel, BorderLayout.WEST);
    fieldPanel.add(tickPanel);

    JPanel xPanel = new JPanel(new BorderLayout());
    JTextField xPos = new JTextField();
    xPos.setText(Integer.toString(motionToEdit.getParams()[1]));
    xPos.setColumns(4);
    JLabel xPosLabel = new JLabel("X:");
    xPanel.add(xPos, BorderLayout.CENTER);
    xPanel.add(xPosLabel, BorderLayout.WEST);
    fieldPanel.add(xPanel);

    JPanel yPanel = new JPanel(new BorderLayout());
    JTextField yPos = new JTextField();
    yPos.setText(Integer.toString(motionToEdit.getParams()[2]));
    yPos.setColumns(4);
    JLabel yPosLabel = new JLabel("Y:");
    yPanel.add(yPos, BorderLayout.CENTER);
    yPanel.add(yPosLabel, BorderLayout.WEST);
    fieldPanel.add(yPanel);

    JPanel wPanel = new JPanel(new BorderLayout());
    JTextField width = new JTextField();
    width.setText(Integer.toString(motionToEdit.getParams()[3]));
    width.setColumns(4);
    JLabel widthLabel = new JLabel("W:");
    wPanel.add(width, BorderLayout.CENTER);
    wPanel.add(widthLabel, BorderLayout.WEST);
    fieldPanel.add(wPanel);

    JPanel hPanel = new JPanel(new BorderLayout());
    JTextField height = new JTextField();
    height.setText(Integer.toString(motionToEdit.getParams()[4]));
    height.setColumns(4);
    JLabel heightLabel = new JLabel("H:");
    hPanel.add(height, BorderLayout.CENTER);
    hPanel.add(heightLabel, BorderLayout.WEST);
    fieldPanel.add(hPanel);

    JPanel rPanel = new JPanel(new BorderLayout());
    JTextField red = new JTextField();
    red.setText(Integer.toString(motionToEdit.getParams()[5]));
    red.setColumns(4);
    JLabel redLabel = new JLabel("R:");
    rPanel.add(red, BorderLayout.CENTER);
    rPanel.add(redLabel, BorderLayout.WEST);
    fieldPanel.add(rPanel);

    JPanel gPanel = new JPanel(new BorderLayout());
    JTextField green = new JTextField();
    green.setText(Integer.toString(motionToEdit.getParams()[6]));
    green.setColumns(4);
    JLabel greenLabel = new JLabel("G:");
    gPanel.add(green, BorderLayout.CENTER);
    gPanel.add(greenLabel, BorderLayout.WEST);
    fieldPanel.add(gPanel);

    JPanel bPanel = new JPanel(new BorderLayout());
    JTextField blue = new JTextField();
    blue.setText(Integer.toString(motionToEdit.getParams()[7]));
    blue.setColumns(4);
    JLabel blueLabel = new JLabel("B:");
    bPanel.add(blue, BorderLayout.CENTER);
    bPanel.add(blueLabel, BorderLayout.WEST);
    fieldPanel.add(bPanel);

    JButton edit = new JButton("Submit Edits");
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(edit);
    MouseListener ml = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getSource() == edit) {
          Motion m = new Motion(rom.getElement(selectedShape), null,
                  Integer.parseInt(tick.getText()),
                  Integer.parseInt(xPos.getText()), Integer.parseInt(yPos.getText()),
                  Integer.parseInt(width.getText()), Integer.parseInt(height.getText()),
                  Integer.parseInt(red.getText()),
                  Integer.parseInt(green.getText()), Integer.parseInt(blue.getText()));
          rom.editKeyFrame(selectedShape, Integer.parseInt(tick.getText()), m);
          finalScreen("Keyframe is edited, click start to go back to the animation,"
                  + "or edit to continue editing.");
        }
      }
    };
    edit.addMouseListener(ml);
    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    this.add(fieldPanel, Component.CENTER_ALIGNMENT);
    this.add(buttonPanel, Component.CENTER_ALIGNMENT);
    this.revalidate();
    this.repaint();
  }
}
