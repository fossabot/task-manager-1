package com.github.joaoh4547.taskmanager.pages.task;

import com.github.joaoh4547.taskmanager.components.swing.SwingTable;
import com.github.joaoh4547.taskmanager.core.process.Process;

public class ProcessTable
  extends SwingTable<Process> {

  @Override
  protected void createColumns() {
    addColumn("name", "Nome");
  }
}
