package edu.hibernate.model;

//import org.hibernate.annotations.Entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @Temporal(TemporalType.DATE)
    private Date createdDate;
//    @Embedded
//    private Syllabus syllabus;
//    @ElementCollection
//    private List<Student> studentList = new ArrayList<Student>();

    public Course(String name, Date createdDate, List<Student> studentList) {
        this.name = name;
        this.createdDate = createdDate;
//        this.studentList = studentList;
    }

//    public Syllabus getSyllabus() {
//        return syllabus;
//    }
//
//    public void setSyllabus(Syllabus syllabus) {
//        this.syllabus = syllabus;
//    }

//    public List<Student> getStudentList() {
//        return studentList;
//    }

//    public void setStudentList(List<Student> studentList) {
//        this.studentList = studentList;
//    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

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

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Course(String name, Date createdDate) {
        this.name = name;
        this.createdDate = createdDate;
    }

    public Course(String name) {
        this.name = name;
    }

//    public Course(String name, Date createdDate, Syllabus syllabus) {
//        this.name = name;
//        this.createdDate = createdDate;
//        this.syllabus = syllabus;
//    }

//    public Course(String name, Date createdDate, Syllabus syllabus, List<Student> studentList) {
//        this.name = name;
//        this.createdDate = createdDate;
//        this.syllabus = syllabus;
//        this.studentList = studentList;
//    }

    public Course() {
        super();
    }

    @Override
    public String toString() {
        return name;
    }
}
