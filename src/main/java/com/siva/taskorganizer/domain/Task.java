package com.siva.taskorganizer.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author by sivamurugan
 */
@Entity(name = "task")
public class Task {

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    private String task;

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskid;
}
