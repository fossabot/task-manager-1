package com.github.joaoh4547.taskmanager.components.ui.button;

import javax.swing.*;

import com.github.joaoh4547.taskmanager.components.ui.ComponentWrapper;

public class SwingButton
  extends AbstractButtonComponent
  implements ComponentWrapper<JButton> {

  private JButton button;

  @Override
  public JButton getComponentWrapper() {
    return button;
  }
}
