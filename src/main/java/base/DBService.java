package base;

import dbService.DBException;

public interface DBService {
    UserProfile getUser(String login) throws DBException;

    void create() throws DBException;

    long addUser(UserProfile userProfile) throws DBException;
}
