package hu.webuni.exam.logistics.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Section {

    @Id
    @GeneratedValue
    private long id;

    private long number;

    private long fromMilestone;

    private long toMilestone;

    public Section() {
    }

    public Section(long id, long number, long fromMilestone, long toMilestone) {
        this.id = id;
        this.number = number;
        this.fromMilestone = fromMilestone;
        this.toMilestone = toMilestone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public long getFromMilestone() {
        return fromMilestone;
    }

    public void setFromMilestone(long fromMilestone) {
        this.fromMilestone = fromMilestone;
    }

    public long getToMilestone() {
        return toMilestone;
    }

    public void setToMilestone(long toMilestone) {
        this.toMilestone = toMilestone;
    }
}
