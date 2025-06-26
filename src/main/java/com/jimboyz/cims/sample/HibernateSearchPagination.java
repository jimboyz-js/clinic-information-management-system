package com.jimboyz.cims.sample;

import com.jimboyz.cims.err.Message;
import com.jimboyz.cims.model.dao.Student;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class HibernateSearchPagination {
    private static final int PAGE_SIZE = 7; // 7 results per page
    private int currentPage = 1;
    private int totalPages = 0;
    private long totalRecords = 0;

    private final SessionFactory sessionFactory;

    public HibernateSearchPagination() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public List<Student> searchWithPagination(String text1, String text2, String text3) {
        try (Session session = sessionFactory.openSession()) {
            SearchSession searchSession = Search.session(session);
//            System.out.println("HELLO: " + text1);
            try {
                searchSession.massIndexer().startAndWait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // Query with multiple fields filtering
            SearchResult<Student> result = searchSession.search(Student.class)
                    .where(f -> f.bool()
//                            .must(f.match().field("firstname").matching(text1))
//                            .must(f.match().field("lastname").matching(text2))
//                            .must(f.match().field("gender").matching(text3))
                                    .must(f.match().field("firstname").matching("sarona"))
                                    .must(f.match().field("lastname").matching("jim"))
                                    .must(f.match().field("gender").matching("male"))
                    )
                    .fetch((currentPage - 1) * PAGE_SIZE, PAGE_SIZE);

            totalRecords = result.total().hitCount();
            totalPages = (int) Math.ceil((double) totalRecords / PAGE_SIZE);

//            Message.displayMessage("Hits: " + result.hits());
            return result.hits();
        }
    }

    public void goToNextPage() {
        if (currentPage < totalPages) {
            currentPage++;
        }
    }

    public void goToPreviousPage() {
        if (currentPage > 1) {
            currentPage--;
        }
    }

    public void goToFirstPage() {
        currentPage = 1;
    }

    public void goToLastPage() {
        currentPage = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalRecords() {
        return totalRecords;
    }
}

