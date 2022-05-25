package hu.webuni.exam.logistics.model;

import javax.persistence.*;

@Entity
public class Section {

    @Id
    @GeneratedValue
    private long id;

    private long number;

    @ManyToOne
    @JoinColumn(name = "from_milestone_id")
    private MileStone fromMilestone;

    @ManyToOne
    @JoinColumn(name = "to_milestone_id")
    private MileStone toMilestone;

    public Section() {
    }

    public Section(long id, long number, MileStone fromMilestone, MileStone toMilestone) {
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

    public MileStone getFromMilestone() {
        return fromMilestone;
    }

    public void setFromMilestone(MileStone fromMilestone) {
        this.fromMilestone = fromMilestone;
    }

    public MileStone getToMilestone() {
        return toMilestone;
    }

    public void setToMilestone(MileStone toMilestone) {
        this.toMilestone = toMilestone;
    }
}
