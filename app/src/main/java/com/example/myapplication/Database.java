package com.example.myapplication;

public class Database {
    private Integer id;
    private String name;
    private String test1;
    private String test2;
    private String homework;
    private String oee;
    private String evaluation;

    public Database(Integer id, String name, String test1,
                    String test2, String homework, String oee, String evaluation) {
        this.id = id;
        this.name = name;
        this.test1 = test1;
        this.test2 = test2;
        this.homework = homework;
        this.oee = oee;
        this.evaluation = evaluation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTest1() {
        return test1;
    }

    public void setTest1(String test1) {
        this.test1 = test1;
    }

    public String getTest2() {
        return test2;
    }

    public void setTest2(String test2) {
        this.test2 = test2;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

    public String getOee() {
        return oee;
    }

    public void setOee(String oee) {
        this.oee = oee;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }
}
