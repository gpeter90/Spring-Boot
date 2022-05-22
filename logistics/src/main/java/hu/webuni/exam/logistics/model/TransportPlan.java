package hu.webuni.exam.logistics.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TransportPlan {

    @Id
    @GeneratedValue
    private long id;

    private long income;

    @ManyToOne
    private Section section;

    public TransportPlan() {
    }

    public TransportPlan(long id, long income, Section section) {
        this.id = id;
        this.income = income;
        this.section = section;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIncome() {
        return income;
    }

    public void setIncome(long income) {
        this.income = income;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}
