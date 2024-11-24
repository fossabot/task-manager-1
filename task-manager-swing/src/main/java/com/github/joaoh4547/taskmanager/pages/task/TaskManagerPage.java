package com.github.joaoh4547.taskmanager.pages.task;

import java.awt.*;
import java.util.Collection;

import javax.swing.*;

import com.github.joaoh4547.taskmanager.core.process.Process;
import com.github.joaoh4547.taskmanager.core.process.ProcessDAO;
import com.github.joaoh4547.taskmanager.swing.AbstractSwingPage;
import com.github.joaoh4547.taskmanager.swing.Page;

@Page(name = "Processos", pageGroup = "Monitoramento",
      description = "Gerenciar Processos")
public class TaskManagerPage
  extends AbstractSwingPage {

  public TaskManagerPage() {
    super();

    JLabel label = new JLabel("Processos");
    label.setFont(new Font("arial", Font.BOLD, 24));
    add(label, BorderLayout.PAGE_START);

    ProcessTable table = new ProcessTable();
    table.setTableData(loadProcesses());
    table.setPreferredSize(new Dimension(200, 100));
    add(table, BorderLayout.CENTER);

    setSize(new Dimension(1200, 720));

  }

  private Collection<Process> loadProcesses() {
    return ProcessDAO.getInstance().loadAll();
  }
}
