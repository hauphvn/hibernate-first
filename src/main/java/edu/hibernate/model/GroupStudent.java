package edu.hibernate.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GroupStudent {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @ManyToMany
    private List<Student> studentList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public GroupStudent(String name, List<Student> studentList) {
        this.name = name;
        this.studentList = studentList;
    }

    public GroupStudent(String name) {
        this.name = name;
    }

    public GroupStudent() {
    }

    @Override
    public String toString() {
        return "GroupStudent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
