package com.utamatisi.app.models.domain;

import com.utamatisi.app.models.milestone.BusinessDateMilestonedImpl;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "todo")
@NamedQueries({
        @NamedQuery(name = "Todo.findAll", query = Todo.GENERIC_SELECT)
})
public class Todo  extends BusinessDateMilestonedImpl {
    public static final String GENERIC_SELECT = "select t from Todo t  where t.businessDateTo >= CURRENT_TIMESTAMP";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "task", nullable = false)
    private String task;

    @Column(name = "done", nullable = false)
    private boolean done;

    @Column(name = "inProgress")
    private boolean inProgress;

    public Todo() {
    }

    public Todo(String task, boolean done, boolean inProgress) {
        this.task = task;
        this.done = done;
        this.inProgress = inProgress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Todo)) {
            return false;
        }

        final Todo that = (Todo) o;

        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.done, that.done) &&
                Objects.equals(this.inProgress, that.inProgress) &&
                Objects.equals(this.task, that.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, task, done, inProgress);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }
}
