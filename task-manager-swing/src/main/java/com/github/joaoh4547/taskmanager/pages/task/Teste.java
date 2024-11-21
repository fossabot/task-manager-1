package com.github.joaoh4547.taskmanager.pages.task;

import java.awt.*;
import java.util.Collection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.github.joaoh4547.taskmanager.core.process.Process;
import com.github.joaoh4547.taskmanager.core.process.ProcessDAO;
import com.github.joaoh4547.taskmanager.swing.AbstractSwingPage;
import com.github.joaoh4547.taskmanager.swing.Page;

@Page(name = "Processos2", pageGroup = "Monitoramento",
      description = "Gerenciar Processos 2")
public class Teste
  extends AbstractSwingPage {

  Teste() {
    super();
    // Data to be displayed in the JTable
    String[][] data = { { "Kundan Kumar Jha", "4031", "CSE" },
        { "Anand Jha", "6014", "IT" } };

    // Column Names
    String[] columnNames = { "Name", "Roll Number", "Department" };


    DefaultTableModel model = new DefaultTableModel(data, columnNames);
    // Initializing the JTable
    JTable j = new JTable(model);
    j.setBounds(30, 40, 200, 300);

    // adding it to JScrollPane
    JScrollPane sp = new JScrollPane(j);
    add(sp);
  }

}
