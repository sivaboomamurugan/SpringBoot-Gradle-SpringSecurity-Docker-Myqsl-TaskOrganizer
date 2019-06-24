package com.siva.taskorganizer.domain;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author by sivamurugan
 */
@Entity(name = "task")
public class Task {

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    private String taskname;

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(taskname, task.taskname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskname);
    }
}
