package luke.jaz.entity.builder;

import javax.servlet.http.HttpServletRequest;
import luke.jaz.entity.EntityState;
import luke.jaz.entity.Role;
import luke.jaz.entity.User;
import luke.jaz.servlet.parameter.UserParameter;

public class UserBuilder implements IEntityBuilder<User> {
    
    @Override
    public User build(HttpServletRequest request) {
        String name = request.getParameter(UserParameter.NAME);
        String password = request.getParameter(UserParameter.PASSWORD);
        String mail = request.getParameter(UserParameter.MAIL);
        return createUser(name, password, mail, -1, EntityState.NEW, Role.USUAL);
    }

    @Override
    public User build(User entity) {
        String name = entity.getName();
        String password = entity.getPassword();
        String mail = entity.getMail();
        int id = entity.getId();
        EntityState entityState = entity.getEntityState();
        Role role = entity.getRole();
        return createUser(name, password, mail, id, entityState, role);
    }
    
    private User createUser(String name, String password, String mail, 
            int id, EntityState entityState, Role role){
        User user = new User();
        user.setName(name);
        user.setMail(mail);
        user.setPassword(password);
        user.setId(id);
        user.setEntityState(entityState);
        user.setRole(role);
        return user;
    }

}
