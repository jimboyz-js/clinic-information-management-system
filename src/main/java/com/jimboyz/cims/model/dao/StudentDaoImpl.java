package com.jimboyz.cims.model.dao;

import com.jimboyz.cims.AppProperties;
import com.jimboyz.cims.db.HibernateUtil;
import com.jimboyz.cims.err.ErrorDialog;
import com.jimboyz.cims.err.Message;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class StudentDaoImpl implements StudentDAO{

    private final AppProperties appProperties = new AppProperties();

    @Transactional
    @Override
    public Long addStudent(Student student) {
        Transaction tx = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            tx = session.beginTransaction();

            Student studentMerge = session.merge(student);//persist
            session.flush();
            tx.commit();
            System.setProperty("app.data.save", "true");

            return studentMerge.getId();
        } catch (HibernateException e) {
            if(tx != null){
                tx.rollback();
            }
            System.setProperty("app.data.save", "false");
            ErrorDialog.show(e);
        }

        return null;
    }

    @Override
    public void updateStudent(Student student) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction tx = session.beginTransaction();
            session.merge(student);
            tx.commit();
            System.setProperty("app.data.update", "true");
        } catch (Exception e) {
            System.setProperty("app.data.update", "false");
            ErrorDialog.show(e);
        }
    }

    @Override
    public void deleteStudent(long id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction tx = session.beginTransaction();
            Student student = session.get(Student.class, id);
            session.remove(student);
            tx.commit();
            System.setProperty("app.data.delete", "true");
        } catch (Exception e) {
            System.setProperty("app.data.delete", "false");
            ErrorDialog.show(e);
        }
    }

    @Override
    public Student findStudentById(long id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.get(Student.class, id);
        } catch (Exception e) {
            ErrorDialog.show(e);
        }

        return null;
    }

    @Override
    public Student findStudentWithMedicalRecord(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT s FROM Student s JOIN FETCH s.medical_data WHERE s.id = :id", Student.class).setParameter("id", id).getSingleResult();

        } catch (NoResultException e) {
            Message.displayMessage("No details found. \nPlease add medical data.");
        } catch (Exception e) {
            ErrorDialog.show(e);
        }

        return null;
    }

    @Override
    public List<Student> getAllStudents() {

        List<Student> students = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
//            session.beginTransaction();
            Query<Student> query = session.createQuery("FROM Student", Student.class);
            query.setCacheable(true);
            query.setMaxResults(7);
            students = query.getResultList();
//            session.getTransaction().commit();

        } catch (Exception e) {
            ErrorDialog.show(e);
        }

        return students;
    }

    @Override
    public List<Student> getPaginatedStudents(int page, int pageSize) {
        return List.of();
    }

    @Override
    public List<Student> getPaginatedStudents(String firstName, String lastName, String gender, String yearLevel, String idNo) {

        return List.of();
    }

    @Override
    public SearchResult<Student> searchPerform(String firstname, String lastname, String gender, String yearLevel, String idNo, String depCourse, int page, int pageSize) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            SearchSession searchSession = Search.session(session);

//            searchSession.massIndexer(Student.class).start();
            AtomicBoolean flag = new AtomicBoolean(appProperties.isSessionMassIndexerStartAndWait);
            try {
                if(flag.get()) searchSession.massIndexer().startAndWait();
            } catch (Exception ignore) {}

            return searchSession.search(Student.class)
                    .where(f -> {
                        var bool = f.bool();
                        if (firstname != null && !firstname.isEmpty()) {
                            bool.must(f.match().field("firstname").matching(firstname));
                        }
                        if (lastname != null && !lastname.isEmpty()) {
                            bool.must(f.match().field("lastname").matching(lastname));
                        }
                        if (gender != null && !gender.isEmpty()) {
                            bool.must(f.match().field("gender").matching(gender));
                        }

                        if(yearLevel != null && !yearLevel.isEmpty()) {
                            bool.must(f.match().field("grade").matching(yearLevel));
                        }

                        if (idNo != null && !idNo.isEmpty()) {
                            bool.must(f.match().field("idNumber").matching(idNo));
                        }

                        if(depCourse != null && !depCourse.isEmpty()) {
                            String s = switch (depCourse) {
                                case "College", "High School", "TRS" -> "department";
                                case "ACT", "BSIT", "BSBA", "BSCRIM", "JUNIOR High", "SENIOR High", "DHMT", "HRM", "Other Course" -> "course";
                                default -> null;
                            };

                            if(s != null) {
                                flag.set(true);
                                bool.must(f.match().field(s).matching(depCourse));
                            }
                        }

                        return bool;
                    })
                    .fetch((page - 1) * pageSize, pageSize); // Pagination logic
        }
    }

    @Override
    public List<Student> getAllStudents(String firstname, String lastname, String gender, String course, String yearLevel, String idNum, int pageSize, int pageNumber) {
        return List.of();
    }

    public Session session() {
        return HibernateUtil.getSessionFactory().openSession();
    }
}
