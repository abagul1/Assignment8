package cs3500.animator.view.visualview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;

import cs3500.IAnimation;
import cs3500.IController;
import cs3500.IView;

/**
 * Visual view with edit capabilities.
 */
public class EnhancedVisualView extends JFrame implements IView {
  private IAnimation m;
  private JButton start;
  private JButton pause;
  private JButton restart;
  private JButton plus;
  private JButton minus;
  private JButton looping;
  private JButton edit;
  private JButton save;
  EditorPanel editPanel;

  /**
   * Constructor for a visual view with edit capabilities.
   * @param m model to be used
   */
  public EnhancedVisualView(IAnimation m) {
    super();

    if (m == null) {
      JOptionPane.showMessageDialog(null, "Model cannot be null",
              "Error", JOptionPane.ERROR_MESSAGE);
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.m = m;
    editPanel = new EditorPanel(m);
    JPanel aniPane = new JPanel();
    JPanel buttonPane = new JPanel();

    JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, buttonPane, aniPane);
    this.setPreferredSize(getPreferredSize());
    getContentPane().setLayout(new GridLayout());
    getContentPane().add(splitPane);
    aniPane.add(editPanel);
    splitPane.setDividerLocation(50);
    JScrollPane scrollPane = new JScrollPane(editPanel,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setPreferredSize(new Dimension(m.getWidth(), m.getHeight()));
    aniPane.add(scrollPane);
    this.add(splitPane);

    start = new JButton("Start");
    pause = new JButton("Pause");
    restart = new JButton("Restart");
    plus = new JButton("IncSpeed");
    minus = new JButton("DecSpeed");
    looping = new JButton("Loop");
    edit = new JButton("Edit");
    save = new JButton("Save");
    buttonPane.add(start);
    start.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    buttonPane.add(pause);
    pause.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
    buttonPane.add(restart);
    restart.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    buttonPane.add(looping);
    looping.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
    buttonPane.add(plus);
    plus.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    buttonPane.add(minus);
    minus.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    buttonPane.add(edit);
    edit.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    buttonPane.add(save);
    save.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    this.setTitle("Animation Station");
    this.setSize(m.getWidth(), m.getHeight());
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void addClickListener(IController listener) {
    MouseListener ml = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if (e.getSource() == pause && !listener.getPaused()) {
          pause.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
          listener.setPaused();
        }
        else if (e.getSource() == start) {
          if (listener.getPaused()) {
            listener.setPaused();
            pause.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
          }
          editPanel.setWindow(WindowType.ANIMATION);
        }
        else if (e.getSource() == restart) {
          m.resetAnimation();
          refresh();
          editPanel.setWindow(WindowType.ANIMATION);
        }
        else if (e.getSource() == plus) {
          listener.changeSpeed("+");
        }
        else if (e.getSource() == minus) {
          listener.changeSpeed("-");
        }
        else if (e.getSource() == looping) {
          if (!listener.getLoop()) {
            looping.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
            listener.setLoop();
          }
          else {
            looping.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            listener.setLoop();
          }
        }
        else if (e.getSource() == edit) {
          if (!listener.getPaused()) {
            listener.setPaused();
          }
          editPanel.setWindow(WindowType.SHAPEMENU);
          refresh();
        }
        else if (e.getSource() == save) {
          if (!listener.getPaused()) {
            listener.setPaused();
          }
          editPanel.setSaveWindow(listener.getSpeed());
          refresh();
        }
      }
    };
    pause.addMouseListener(ml);
    start.addMouseListener(ml);
    restart.addMouseListener(ml);
    plus.addMouseListener(ml);
    minus.addMouseListener(ml);
    looping.addMouseListener(ml);
    edit.addMouseListener(ml);
    save.addMouseListener(ml);
  }

  @Override
  public void execute() {
    this.refresh();
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(m.getWidth(), m.getHeight());
  }
}
