package com.jimboyz.cims.model.dao.user;

import com.jimboyz.cims.db.DBConstraint;
import com.jimboyz.cims.db.HibernateUtil;
import com.jimboyz.cims.err.ErrorDialog;
import com.jimboyz.cims.err.Message;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class UserDaoImpl implements UserDAO{
    @Override
    public User findUserById(int id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.get(User.class, id);
        }
    }

    @Override
    public User findUserByUsername(String username) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("from User where username = :username", com.jimboyz.cims.model.dao.user.User.class);
            query.setParameter("username", username);

            return query.uniqueResult();

        } catch (Exception e) {
            ErrorDialog.show(e);
        }
        return null;
    }

    @Override
    public User findUserByTerminalKey(String terminalKey) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("from User where terminal_key = :terminalKey", com.jimboyz.cims.model.dao.user.User.class);
            query.setParameter("terminalKey", terminalKey);

            return query.uniqueResult();

        } catch (Exception e) {
            ErrorDialog.show(e);
        }
        return null;
    }

    @Override
    public User findUserByPassword(String password) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("from User where password = :password", com.jimboyz.cims.model.dao.user.User.class);
            query.setParameter("password", password);

            return query.uniqueResult();

        } catch (Exception e) {
            ErrorDialog.show(e);
        }
        return null;
    }

    @Override
    public boolean saveUser(User user) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(user);
            tx.commit();
            return true;
        } catch (PersistenceException e) {
            if(DBConstraint.isDuplicateConstraintViolation(e)) {
                Message.displayMessage("Key is already in use.");
            }
            return false;
        } catch (Exception e) {
            ErrorDialog.show(e);
            return false;
        }
    }

    @Override
    public boolean deleteUser(long id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            tx.commit();

            return true;
        }
    }

    @Override
    public boolean updateUser(User user) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(user);
            tx.commit();
            return true;
        } catch (PersistenceException e) {
            if(DBConstraint.isDuplicateConstraintViolation(e)) {
                Message.displayMessage("Username is already in use.");
            }
            return false;
        } catch (Exception e) {
            ErrorDialog.show(e);
            return false;
        }
    }

    @Override
    public List<User> getAllUser() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("from User", com.jimboyz.cims.model.dao.user.User.class);
            return query.list();
        }
    }
}
