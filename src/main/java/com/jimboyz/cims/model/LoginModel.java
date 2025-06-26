package com.jimboyz.cims.model;

import com.jimboyz.cims.MySimpleDateFormat;
import com.jimboyz.cims.model.dao.user.User;
import com.jimboyz.cims.model.dao.user.UserDAO;
import com.jimboyz.cims.model.dao.user.UserDaoImpl;
import org.mindrot.jbcrypt.BCrypt;

public class LoginModel {

    private User user = null;

    public boolean isAuthenticated(String username, String password) {

        UserDAO userDao = new UserDaoImpl();
        user = userDao.findUserByUsername(username);

        if(user == null) {
            return false;
        }

//        Moved to AppController [loginSetup]
//        if(!DateValidator.isTodayWithinRange(MySimpleDateFormat.getInstance().format(user.getFromDateSuspended()), MySimpleDateFormat.getInstance().format(user.getToDateSuspended()))) {
//            return false;
//        }

        return username.equals(user.getUsername()) && BCrypt.checkpw(password, user.getPassword());
    }

    public boolean isAdminLogin() {
        return BCrypt.checkpw("admin777", user.getPassword()) && user.getUsername().equals("admin");
    }

    // Get Terminal Key
    public String getUserTerminalKey() {
        return user.getTerminal_key();
    }

    public User getUser() {
        return user;
    }

    public void logoutUser() {
        user = null;
    }
}
