package com.jimboyz.cims.model.dao;
import org.hibernate.Session;
import org.hibernate.search.engine.search.query.SearchResult;

import java.util.List;

public interface StudentDAO {
    Long addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(long id);
    Student findStudentById(long id);
    Student findStudentWithMedicalRecord(long id);
    List<Student> getAllStudents();
    List<Student> getPaginatedStudents(int page, int pageSize);
    List<Student> getPaginatedStudents(String firstName, String lastName, String gender, String yearLevel, String idNo);
    SearchResult<Student> searchPerform(String firstname, String lastname, String gender, String yearLevel, String idNo, String depCourse, int page, int pageSize);
//    List<Student> getAllStudents(String firstName, String lastName, String gender); // to be delete
//    List<Student> getPaginatedStudents(String firstname, String lastname, String gender, int page, int pageSize);// to be delete
    List<Student> getAllStudents(String firstname, String lastname, String gender, String course, String yearLevel, String idNum, int pageSize, int pageNumber);
}
