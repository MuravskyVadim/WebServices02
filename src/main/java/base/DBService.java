package base;

import dbService.DBException;

public interface DBService {
    UserProfile getUser(String login) throws DBException;

    void create() throws DBException;

    Long addUser(UserProfile userProfile) throws DBException;
}
