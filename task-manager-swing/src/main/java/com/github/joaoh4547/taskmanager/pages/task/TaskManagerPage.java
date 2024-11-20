package com.github.joaoh4547.taskmanager.pages.task;

import com.github.joaoh4547.taskmanager.core.process.Process;
import com.github.joaoh4547.taskmanager.core.process.ProcessDAO;
import com.github.joaoh4547.taskmanager.swing.AbstractSwingPage;
import com.github.joaoh4547.taskmanager.swing.Page;

import javax.swing.*;
import java.util.Collection;

@Page(name = "Processos", pageGroup = "Monitoramento",
      description = "Gerenciar Processos")
public class TaskManagerPage
  extends AbstractSwingPage {

  public TaskManagerPage() {
    super();
    JScrollPane scrollPane = new JScrollPane();
    ProcessTable table = new ProcessTable();
    table.setTableData(loadProcesses());
//    table.sizeColumnsToFit();
    add(scrollPane);
  }

  private Collection<Process> loadProcesses() {
    return ProcessDAO.getInstance().loadAll();
  }
}
