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
        String surname = request.getParameter(UserParameter.SURNAME);
        String mail = request.getParameter(UserParameter.MAIL);
        String company = request.getParameter(UserParameter.COMPANY);
        String source = request.getParameter(UserParameter.SOURCE);
        String hobby = request.getParameter(UserParameter.HOBBY);
        return createUser(name, surname, mail, company, source, hobby, -1, EntityState.NEW, Role.USUAL);
    }

    @Override
    public User build(User entity) {
        String name = entity.getName();
        String surname = entity.getSurname();
        String mail = entity.getMail();
        String company = entity.getCompany();
        String source = entity.getSource();
        String hobby = entity.getHobby();
        int id = entity.getId();
        EntityState entityState = entity.getEntityState();
        Role role = entity.getRole();
        return createUser(name, surname, mail, company, source, hobby, id, entityState, role);
    }
    
    private User createUser(String name, String surname, String mail, 
            String company, String source, String hobby, int id, EntityState entityState, Role role){
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setMail(mail);
        user.setCompany(company);
        user.setSource(source);
        user.setHobby(hobby);
        user.setId(id);
        user.setEntityState(entityState);
        user.setRole(role);
        return user;
    }

}
