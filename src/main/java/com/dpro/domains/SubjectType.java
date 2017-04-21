package com.dpro.domains;

public class SubjectType {

    private Long id;
    private String name;

    /**
     * @return identyfikator typu przedmiotu
     */
    public Long getId() {
        return id;
    }

    /**
     * @return nazwa typu przedmiotu
     */
    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
