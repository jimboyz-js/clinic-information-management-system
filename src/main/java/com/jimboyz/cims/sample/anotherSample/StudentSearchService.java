package com.jimboyz.cims.sample.anotherSample;

import com.jimboyz.cims.model.dao.Student;
import org.hibernate.search.mapper.orm.session.SearchSession;
//import org.hibernate.search.mapper.orm.search.query.SearchResult;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class StudentSearchService {

    private final SessionFactory sessionFactory;

    public StudentSearchService() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public List<Student> searchEmployees(String firstName, String lastName, String gender, String id, int pageNumber, int pageSize) {
        try (Session session = sessionFactory.openSession()) {
            SearchSession searchSession = session.unwrap(SearchSession.class);

            // Building the search query
            var searchQuery = searchSession.search(Student.class)
                    .where(f -> {
                        var bool = f.bool();
                        if (firstName != null && !firstName.trim().isEmpty()) {
                            bool.must(f.match().field("firstName").matching(firstName));
                        }
                        if (lastName != null && !lastName.trim().isEmpty()) {
                            bool.must(f.match().field("lastName").matching(lastName));
                        }
                        if (gender != null && !gender.trim().isEmpty()) {
                            bool.must(f.match().field("gender").matching(gender));
                        }
                        if (id != null && !id.trim().isEmpty()) {
                            bool.must(f.match().field("id").matching(id));
                        }
                        return bool;
                    })
                    .sort(f -> f.field("id").asc()) // Sorting by ID
                    .fetch(pageNumber * pageSize, pageSize); // Pagination

            return searchQuery.hits();
        }
    }

    public long getTotalRecords(String firstName, String lastName, String gender, String id) {
        try (Session session = sessionFactory.openSession()) {
            SearchSession searchSession = session.unwrap(SearchSession.class);

            var searchQuery = searchSession.search(Student.class)
                    .where(f -> {
                        var bool = f.bool();
                        if (firstName != null && !firstName.trim().isEmpty()) {
                            bool.must(f.match().field("firstName").matching(firstName));
                        }
                        if (lastName != null && !lastName.trim().isEmpty()) {
                            bool.must(f.match().field("lastName").matching(lastName));
                        }
                        if (gender != null && !gender.trim().isEmpty()) {
                            bool.must(f.match().field("gender").matching(gender));
                        }
                        if (id != null && !id.trim().isEmpty()) {
                            bool.must(f.match().field("id").matching(id));
                        }
                        return bool;
                    })
                    .fetchTotalHitCount();

            return searchQuery;
        }
    }
}

