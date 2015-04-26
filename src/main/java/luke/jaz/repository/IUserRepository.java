package luke.jaz.repository;

import luke.jaz.entity.User;

public interface IUserRepository extends IRepository<User> {
    
    User get(String login, String pwd);
    
    boolean isUserNameFree(String login);
    
    User get(String login);
    
}
