package com.siva.taskorganizer.domain;

/**
 * @author by Siva Murugan
 */
public class UserTaskMappingModel {

    private boolean completed;

    private Task task;

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
