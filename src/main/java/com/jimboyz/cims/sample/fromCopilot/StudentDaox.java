package com.jimboyz.cims.sample.fromCopilot;

import com.jimboyz.cims.model.dao.Student;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.hibernate.Session;

public class StudentDaox {
    private final Session session;

    public StudentDaox(Session session) {
        this.session = session;
    }

    public SearchResult<Student> searchEmployees(String firstname, String lastname, String gender, String id, int page, int pageSize) {
        SearchSession searchSession = Search.session(session);

        return searchSession.search(Student.class)
                .where(f -> {
                    var bool = f.bool();
                    if (firstname != null && !firstname.isEmpty()) {
                        System.out.println(firstname);
                        bool.must(f.match().field("firstname").matching(firstname));
                    }
                    if (lastname != null && !lastname.isEmpty()) {
                        System.out.println(lastname);
                        bool.must(f.match().field("lastname").matching(lastname));
                        System.out.println(bool.must(f.match().field("lastname").matching(lastname)));
                    }
                    if (gender != null && !gender.isEmpty()) {
                        System.out.println(gender);
                        bool.must(f.match().field("gender").matching(gender));
                    }
                    if (id != null) {
                        System.out.println(id);
                        bool.must(f.match().field("idNumber").matching(id));
                    }

//                    if (firstname != null && !firstname.isEmpty()) {
//                        System.out.println(firstname);
//                        bool.must(f.match().field("firstname").matching("Jimboy"));
//                    }
//                    if (lastname != null && !lastname.isEmpty()) {
//                        System.out.println(lastname);
//                        bool.must(f.match().field("lastname").matching("Sarona"));
//                    }
//                    if (gender != null && !gender.isEmpty()) {
//                        System.out.println(gender);
//                        bool.must(f.match().field("gender").matching("Male"));
//                    }
//                    if (id != null) {
//                        System.out.println(id);
//                        bool.must(f.match().field("idNumber").matching("777"));
//                    }

                    return bool;
                })
                .fetch((page - 1) * pageSize, pageSize); // Pagination logic
    }
}

