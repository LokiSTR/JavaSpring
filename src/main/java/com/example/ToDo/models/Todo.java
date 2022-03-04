package com.example.ToDo.models;

public class Todo {

    String desc;
    String person;
    
    public Todo(String desc, String person){
        setDesc(desc);
        setPerson(person);
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getPerson() {
        return person;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
