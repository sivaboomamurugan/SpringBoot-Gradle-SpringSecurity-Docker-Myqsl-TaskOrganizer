package com.siva.taskorganizer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author by Siva Murugan
 */
@Entity(name = "user_task_mapping")
public class UserTaskMapping {

    public UserTaskMapping() {

    }

    public UserTaskMapping(User user, Task task, boolean isCompleted ) {
        this.user = user;
        this.task = task;
        this.completed = isCompleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="userid", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="taskid", nullable = false)
    private Task task;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Column(name = "completed", columnDefinition = "BOOLEAN")
    private boolean completed;

}
