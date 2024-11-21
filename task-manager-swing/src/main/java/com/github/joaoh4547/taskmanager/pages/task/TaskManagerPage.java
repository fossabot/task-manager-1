package com.github.joaoh4547.taskmanager.pages.task;

import com.github.joaoh4547.taskmanager.core.process.Process;
import com.github.joaoh4547.taskmanager.core.process.ProcessDAO;
import com.github.joaoh4547.taskmanager.swing.AbstractSwingPage;
import com.github.joaoh4547.taskmanager.swing.Page;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

@Page(name = "Processos", pageGroup = "Monitoramento",
      description = "Gerenciar Processos")
public class TaskManagerPage
  extends AbstractSwingPage {

  public TaskManagerPage() {
    super();

    Container pane = getContentPane();

    // JButton button = new JButton("Button 1 (PAGE_START)");
    // pane.add(button, BorderLayout.PAGE_START);
    //
    // // Make the center component big, since that's the
    // // typical usage of BorderLayout.
    // button = new JButton("Button 2 (CENTER)");
    // button.setPreferredSize(new Dimension(200, 100));
    // pane.add(button, BorderLayout.CENTER);

    JLabel label = new JLabel("Processos");
    label.setFont(new Font("arial", Font.BOLD, 24));
    pane.add(label, BorderLayout.PAGE_START);

    ProcessTable table = new ProcessTable();
    table.setTableData(loadProcesses());
    table.setPreferredSize(new Dimension(200, 100));
    pane.add(table, BorderLayout.CENTER);

    setSize(new Dimension(1200, 720));

    // add(new JLabel("Processos"));
    // ProcessTable table = new ProcessTable();
    // table.setTableData(loadProcesses());
    // scrollPane.add(table);
    // add(scrollPane);
  }

  private Collection<Process> loadProcesses() {
    return ProcessDAO.getInstance().loadAll();
  }
}
