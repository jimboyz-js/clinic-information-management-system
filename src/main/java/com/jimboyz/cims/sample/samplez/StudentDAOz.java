package com.jimboyz.cims.sample.samplez;

import com.jimboyz.cims.model.dao.Student;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.Session;

public class StudentDAOz {

    private Session session;

    public StudentDAOz(Session session) {
        this.session = session;
    }

    public SearchResult<Student> searchStudents(String firstname, String lastname, String gender, String id, int pageNumber, int pageSize) {
        SearchSession searchSession = Search.session(session);

//        SearchSession searchSession = Search.session(session);
        try {
            searchSession.massIndexer().startAndWait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        var searchQuery = searchSession.search(Student.class)
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
                    if (id != null) {
                        bool.must(f.match().field("idNumber").matching(id));
                    }
                    return bool;
                })
                .fetch((pageNumber - 1) * pageSize, pageSize); // Pagination logic

        return searchQuery;
    }
}

