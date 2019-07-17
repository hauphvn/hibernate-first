import edu.hibernate.model.*;
import edu.hibernate.util.Constant;
import javassist.bytecode.ExceptionsAttribute;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import sun.java2d.pipe.hw.ExtendedBufferCapabilities;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Management {
//    static SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
//
//    protected void exit() {
//        if (sessionFactory != null){
//            sessionFactory.close();
//        }
//    }
//
//    protected void create() {
//        Course course = new Course(2,"Spring");
////        Syllabus syllabus = new Syllabus("Spring Content",5);
////        List<Student> studentList = new ArrayList<Student>();
////        studentList.add(new Student("Jack","hcmus100"));
////        studentList.add(new Student("John","hcmus101"));
////        Course course = new Course("Spring has a conntent",new Date(), syllabus, studentList);
//       if (sessionFactory != null){
//           Session session = sessionFactory.openSession();
//           session.beginTransaction();
//
//           session.save(course);
//
//           session.getTransaction().commit();
//           session.close();
//       }
//    }
//
//    protected void read() {
////        Session session = sessionFactory.openSession();
////
////        long bookId = 20;
////        Book book = session.get(Book.class, bookId);
////
////        System.out.println("Title: " + book.getTitle());
////        System.out.println("Author: " + book.getAuthor());
////        System.out.println("Price: " + book.getPrice());
////
////        session.close();
//        Session  session = sessionFactory.openSession();
//        Course course = (Course)session.get(Course.class,1);
//        System.out.println(course);
//        session.close();
//    }
//
//    protected void update() {
////        Book book = new Book();
////        book.setId(20);
////        book.setTitle("Ultimate Java Programming");
////        book.setAuthor("Nam Ha Minh");
////        book.setPrice(19.99f);
////
////        Session session = sessionFactory.openSession();
////        session.beginTransaction();
////
////        session.update(book);
////
////        session.getTransaction().commit();
////        session.close();
//    }
//
//    protected void delete() {
////        Book book = new Book();
////        book.setId(20);
////
////        Session session = sessionFactory.openSession();
////        session.beginTransaction();
////
////        session.delete(book);
////
////        session.getTransaction().commit();
////        session.close();
//    }

    public static void main(String[] args) {
//        updateStudentUsingHQL();
//        queryStudentUsingHQL();
//        createStudent();
//        createStudentAndGroup();
//        getStudent();
//        updateStudent();
//        System.out.println("after updated");
//        getStudent();
//        System.out.println("after deleted");
//        deleteStudent();
////        getStudent();
//        deleteStudentUsingHQL();
//        useNamedQuery();
//        useFirstLevelCache();
        useSecondLevelCache();
        ConnectionUtil.getSessionFactory().close();

    }

    private static void useSecondLevelCache(){
        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
         // Thuc hien goi vao co so du lieu lan thu 1
            Session session = sessionFactory.openSession();
            session.getTransaction();
            Student student = (Student)session.get(Student.class,2);
            System.out.println(student.getName());
            session.close();

            //// Thuc hien goi vao co so du lieu lan thu 2
            session = sessionFactory.openSession();
            session.getTransaction();
            student = (Student)session.get(Student.class,2);
            System.out.println(student.getName());
            session.close();
    }

    private static void useFirstLevelCache(){
        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
        try {

            // Thuc hien goi vao co so du lieu lan thu 1
            Session session = sessionFactory.openSession();
            session.getTransaction();
            Student student = (Student)session.get(Student.class,2);
            System.out.println(student.getName());
            session.close();

            //// Thuc hien goi vao co so du lieu lan thu 2
            session = sessionFactory.openSession();
            session.getTransaction();
            student = (Student)session.get(Student.class,2);
            System.out.println(student.getName());
            session.close();


        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    private static void useNamedQuery(){
        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
        try {
            Session session = sessionFactory.openSession();
            session.getTransaction();
            Query query = session.getNamedQuery(Constant.STUDENT_BY_NAME);
            query.setParameter("name","Van a");
            List<Student> studentList = (List<Student>)query.getResultList();
            for (Student student :
                    studentList) {
                System.out.println(student.getName());
            }
            session.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    private static void useCriteria(){
        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            CriteriaBuilder criteriaBuilderStudent = session.getCriteriaBuilder();
            CriteriaQuery<Student> studentCriteriaQuery = criteriaBuilderStudent.createQuery(Student.class);
            Root<Student> studentRoot = studentCriteriaQuery.from(Student.class);
            session.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    private static void deleteStudentUsingHQL(){
        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            String queryStr = "delete from Student where id = :id";
            Query query = session.createQuery(queryStr);
            query.setParameter("id",1);
            int rowResult = query.executeUpdate();
            System.out.println("Row success: " + rowResult);
            session.getTransaction().commit();
            session.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    private static void updateStudentUsingHQL(){
        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            String queryStr = "update Student set name = :name where id = :id";
            Query query = session.createQuery(queryStr);
            query.setParameter("name","New name jack");
            query.setParameter("id",1);
            int rowResult = query.executeUpdate();
            System.out.println("Row success: " + rowResult);
            session.getTransaction().commit();
            session.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    private static void queryStudentUsingHQL(){
        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            /* Day la cach dung ? trong cau query*/
//            String queryStr = "FROM Student WHERE id = ? and name like ?";
//            Query query = session.createQuery(queryStr);
//            query.setParameter(0,1);
//            query.setParameter(1,"%b");

            /*Day la cach dung :id parameter trong cau query*/
            String queryStr = "FROM Student WHERE id = :id and name like :name";
            Query query = session.createQuery(queryStr);
            query.setParameter("name", "%b");
            query.setParameter("id", 2);

            List<Student> studentList = (List<Student>)query.getResultList();
            for (Student student :
                    studentList) {
                System.out.println(student.getName());
            }
            session.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    private static void deleteStudent() {
        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();

        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Student student = (Student) session.get(Student.class, 2);
            session.delete(student);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }


    }

    private static void updateStudent() {
        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();

        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Student student = (Student) session.get(Student.class, 1);
            student.setName("Jack");
            session.update(student);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }


    }

    private static void getStudent() {
        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();

        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Student student = (Student) session.get(Student.class, 1);
            System.out.println(student.getName() + " : " + student.getCode());
            session.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }


    }

    private static void createStudentAndGroup() {
        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();

        Student student1 = new Student("Van a", "Code Van a");
        Student student2 = new Student("Van b", "Code Van b");
        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);

        GroupStudent group1 = new GroupStudent("GroupHTTT");
        GroupStudent group2 = new GroupStudent("GroupCNTT");
        List<GroupStudent> groupStudents = new ArrayList<>();
        groupStudents.add(group1);
        groupStudents.add(group2);

        student1.setGroupStudentList(groupStudents);
        student2.setGroupStudentList(groupStudents);

        group1.setStudentList(studentList);
        group2.setStudentList(studentList);

        try {

            Session session = sessionFactory.openSession();
            session.beginTransaction();

            session.save(student1);
            session.save(student2);
            session.save(group1);
            session.save(group2);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private static void createStudent() {
        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Orange"));
        courses.add(new Course("Tomato"));
        courses.add(new Course("Mango"));
        Address address = new Address("Q8", "HCM");
        Student student = new Student("Donal Trump", "codeObama", address, courses);
        try {

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            for (Course course :
                    courses) {
                session.save(course);
            }
            session.save(address);
            session.save(student);
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }



}
