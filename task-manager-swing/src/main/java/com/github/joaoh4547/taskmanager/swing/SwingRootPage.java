package com.github.joaoh4547.taskmanager.swing;

import javax.swing.*;

public abstract class SwingRootPage
  extends JFrame {

  public SwingRootPage() {
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setTitle(getAppName());
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setJMenuBar(MenuGenerator.generateMenu());
  }

  public abstract String getAppName();
}
