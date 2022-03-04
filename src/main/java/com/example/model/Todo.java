package com.example.model;

public class Todo {
    
    String desc;
    String person;

    public Todo(String desc, String person){
        setDesc(desc);
        setPerson(person);
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getDesc() {
        return desc;
    }

    public String getPerson() {
        return person;
    }
    public void setPerson(String person) {
        this.person = person;
    }


}
