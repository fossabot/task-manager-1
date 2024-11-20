package com.github.joaoh4547.taskmanager.core.process;

import java.util.UUID;

import com.github.joaoh4547.taskmanager.data.AbstractRepository;

public class ProcessDAO
  extends AbstractRepository<Process, UUID> {

  protected ProcessDAO() {
    super(Process.class);
  }

  public static ProcessDAO getInstance() {
    return new ProcessDAO();
  }

}
