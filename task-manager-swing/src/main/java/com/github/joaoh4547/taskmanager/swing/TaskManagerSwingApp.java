package com.github.joaoh4547.taskmanager.swing;

import com.github.joaoh4547.taskmanager.core.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.plaf.multi.MultiLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class TaskManagerSwingApp
  extends Application {

  private static final Logger logger = LoggerFactory
      .getLogger(TaskManagerSwingApp.class);

  @Override
  public void run()
           {
    SwingRootPage app = new SwingRootPage() {
      @Override
      public String getAppName() {
        return "Task - Manage Swing App";
      }
    };


    try {

      UIManager.setLookAndFeel(NimbusLookAndFeel.class.getName());
      // "javax.swing.plaf.nimbus.NimbusLookAndFeel";

    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }

    app.setVisible(true);
  }
}
