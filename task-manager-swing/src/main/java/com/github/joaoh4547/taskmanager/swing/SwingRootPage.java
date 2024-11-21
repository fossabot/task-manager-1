package com.github.joaoh4547.taskmanager.swing;

import javax.swing.*;
import java.awt.*;

public abstract class SwingRootPage
  extends JFrame {

  public SwingRootPage() {
    setLayout(new BorderLayout());

    Container pane = getContentPane();
    setTitle(getAppName());
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setJMenuBar(MenuGenerator.generateMenu(this));
    setDefaultLookAndFeelDecorated(true);
    JPanel panel = new JPanel();
    panel.add(new JLabel(getPageName()));
    pane.add(panel);
  }

  public abstract String getAppName();

  public String getPageName() {
    return getAppName();
  }
}
