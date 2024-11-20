package com.github.joaoh4547.taskmanager.pages.task;

import com.github.joaoh4547.taskmanager.components.Table;
import com.github.joaoh4547.taskmanager.core.process.Process;

public class ProcessTable
  extends Table<Process> {

  public ProcessTable() {
    addColumn("name", "Nome");
  }

}
