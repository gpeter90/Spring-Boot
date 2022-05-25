package hu.webuni.exam.logistics.dto;

import hu.webuni.exam.logistics.model.Section;

public class TransportPlanDto {


    private long id;

    private long income;

    private Section section;

    public TransportPlanDto() {
    }

    public TransportPlanDto(long id, long income, Section section) {
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
