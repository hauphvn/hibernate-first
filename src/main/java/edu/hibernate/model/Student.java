package edu.hibernate.model;

import edu.hibernate.util.Constant;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@NamedQueries({@NamedQuery(name = Constant.STUDENT_BY_NAME, query = "from Student where  name = :name")})
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)// Vi chi can doc
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String code;
    @OneToOne
    private Address address;
    @OneToMany
    List<Course> courseList = new ArrayList<>();
    @ManyToMany
    List<GroupStudent> groupStudentList = new ArrayList<>();

    public List<GroupStudent> getGroupStudentList() {
        return groupStudentList;
    }

    public void setGroupStudentList(List<GroupStudent> groupStudentList) {
        this.groupStudentList = groupStudentList;
    }

    public Address getAddress() {
        return address;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Student() {
        System.out.println("Constructor Student");
    }

    public Student(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public Student(String name, String code, Address address) {
        this.name = name;
        this.code = code;
        this.address = address;
    }

    public Student(String name, String code, Address address, List<Course> courseList) {
        this.name = name;
        this.code = code;
        this.address = address;
        this.courseList = courseList;
    }

    public Student(String name, List<GroupStudent> groupStudentList) {
        this.name = name;
        this.groupStudentList = groupStudentList;
    }
}
