package luke.jaz.entity.builder;

import javax.servlet.http.HttpServletRequest;
import luke.jaz.entity.EntityState;
import luke.jaz.entity.Role;
import luke.jaz.entity.User;
import luke.jaz.parameter.servlet.UserParameter;

public class UserBuilder implements IEntityBuilder<User> {
    
    @Override
    public User build(HttpServletRequest request) {
        String name = request.getParameter(UserParameter.NAME);
        String surname = request.getParameter(UserParameter.SURNAME);
        String employer = request.getParameter(UserParameter.EMPLOYER);
        String source = request.getParameter(UserParameter.SOURCE);
        String hobby = request.getParameter(UserParameter.HOBBY);
        String password = request.getParameter(UserParameter.PASSWORD);
        String mail = request.getParameter(UserParameter.MAIL);
        return createUser(name, password, surname, employer, source, hobby, mail, -1, EntityState.NEW, Role.USUAL);
    }

    @Override
    public User build(User entity) {
        String name = entity.getName();
        String surname = entity.getSurname();
        String employer = entity.getEmployer();
        String source = entity.getSource();
        String hobby = entity.getHobby();
        String password = entity.getPassword();
        String mail = entity.getMail();
        int id = entity.getId();
        EntityState entityState = entity.getEntityState();
        Role role = entity.getRole();
        return createUser(name, password, surname, employer, source, hobby, mail, id, entityState, role);
    }
    
    private User createUser(String name, String password, String surname, String employer, String source,
            String hobby, String mail, 
            int id, EntityState entityState, Role role){
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmployer(employer);
        user.setSource(source);
        user.setHobby(hobby);
        user.setMail(mail);
        user.setPassword(password);
        user.setId(id);
        user.setEntityState(entityState);
        user.setRole(role);
        return user;
    }

}
