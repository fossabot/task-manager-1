package com.github.joaoh4547.taskmanager.swing;

import com.github.joaoh4547.taskmanager.core.Application;

import javax.swing.*;

public class TaskManagerSwingApp
  extends Application {

  @Override
  public void run() {
    SwingRootPage app = new SwingRootPage() {
      @Override
      public String getAppName() {
        return "Task - Manage Swing App";
      }
    };

    app.setVisible(true);
  }
}
