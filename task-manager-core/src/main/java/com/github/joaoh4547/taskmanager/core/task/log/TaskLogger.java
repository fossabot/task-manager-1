package com.github.joaoh4547.taskmanager.core.task.log;

import com.github.joaoh4547.taskmanager.core.process.LogType;
import com.github.joaoh4547.taskmanager.core.process.Process;
import com.github.joaoh4547.taskmanager.core.process.ProcessDAO;
import com.github.joaoh4547.taskmanager.core.process.ProcessLog;
import com.github.joaoh4547.taskmanager.core.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TaskLogger<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskLogger.class);

    private final Task<T> task;

    public static <T> TaskLogger<T> getInstance(Task<T> task) {
        return new TaskLogger<>(task);
    }

    private TaskLogger(Task<T> task) {
        this.task = task;
    }

    public Task<T> getTask() {
        return task;
    }

    public void log(LogType logType, String message) {
        Process process = task.getProcess();
        ProcessLog log = new ProcessLog(message, logType, process);
        process.addLog(log);
        ProcessDAO.getInstance().save(process);
    }

    public void log(LogType logType, String message, boolean sendNotify) {
        log(logType, message);
    }
}
