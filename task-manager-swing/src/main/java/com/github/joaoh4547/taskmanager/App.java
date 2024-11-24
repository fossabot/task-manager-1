package com.github.joaoh4547.taskmanager;

import com.github.joaoh4547.taskmanager.core.Application;
import com.github.joaoh4547.taskmanager.swing.TaskManagerSwingApp;

public class App {



    public static void main(String[] args) {
        Application app = new TaskManagerSwingApp();
        app.start();
    }
}
