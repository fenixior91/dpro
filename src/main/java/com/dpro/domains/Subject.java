package com.dpro.domains;

public class Subject {

    private long id;
    private String name;
    private int ects;
    private int hours;
    private SubjectType subjectType;

    /**
     * @return identyfikator przedmiotu
     */
    public long getId() {
        return id;
    }

    /**
     * @return nazwa przedmiotu
     */
    public String getName() {
        return name;
    }

    /**
     * @return punkty ects
     */
    public int getEcts() {
        return ects;
    }

    /**
     * @return liczba godzin
     */
    public int getHours() {
        return hours;
    }

    /**
     * @return typ przedmiotu
     */
    public SubjectType getSubjectType() {
        return subjectType;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setSubjectType(SubjectType subjectType) {
        this.subjectType = subjectType;
    }
}
