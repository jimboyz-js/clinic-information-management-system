package com.jimboyz.cims.model.dao.user;

import java.util.List;

public interface UserDAO {
    User findUserById(int id);
    User findUserByUsername(String username);
    User findUserByTerminalKey(String terminalKey);
    User findUserByPassword(String password);
    boolean saveUser(User user);
    boolean deleteUser(long id);
    boolean updateUser(User user);
    List<User> getAllUser();
}
