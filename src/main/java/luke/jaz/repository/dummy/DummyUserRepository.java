package luke.jaz.repository.dummy;

import java.util.ArrayList;
import java.util.List;
import luke.jaz.entity.User;
import luke.jaz.entity.builder.IEntityBuilder;
import luke.jaz.entity.builder.UserBuilder;
import luke.jaz.repository.IUserRepository;
import luke.jaz.repository.unitofwork.IUnitOfWork;
import luke.jaz.repository.unitofwork.IUnitOfWorkRepository;
import luke.jaz.repository.unitofwork.UnitOfWork;
import luke.jaz.util.PoolOfIds;

public class DummyUserRepository implements IUserRepository, IUnitOfWorkRepository<User> {
    
    private final IEntityBuilder<User> builder;
    private final IUnitOfWork unitOfWork;
    private final DummyDB dummyDB;
    
    public DummyUserRepository(IEntityBuilder<User> builder) {
        this.dummyDB = DummyDB.getInstance();
        this.unitOfWork = UnitOfWork.getInstance();
        this.builder = builder;
    }
    
    @Override
    public void delete(User entity) {
        this.unitOfWork.markAsDeleted(entity, this);
    }

    @Override
    public void save(User entity) {
        this.unitOfWork.markAsNew(entity, this);
    }

    @Override
    public void update(User entity) {
        this.unitOfWork.markAsDirty(entity, this);
    }

    @Override
    public User get(int id) {
        for (User user : dummyDB.getUsersDB().values()) {
            if(user.getId() == id){
                return builder.build(user);
            }
        }
        return null;
    }
    
    @Override
    public User get(String login, String pwd) {
        for (User user : dummyDB.getUsersDB().values()) {
            if(user.getName().equals(login) && user.getSource().equals(pwd)){
                return builder.build(user);
            }
        }
        return null;
    }
    
    @Override
    public boolean isUserNameFree(String login) {
        for (User user : dummyDB.getUsersDB().values()) {
            if(user.getName().equals(login)){
                return false;
            }
        }
        return true;
    }

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        for (User user : dummyDB.getUsersDB().values()) {
            result.add(builder.build(user));
        }
        return result;
    }
    
    @Override
    public void persistAdd(User entity) {
        dummyDB.getUsersDB().put(PoolOfIds.generateId(), entity);
    }

    @Override
    public void persistUpdate(User entity) {
        dummyDB.getUsersDB().put(entity.getId(), entity);
    }

    @Override
    public void persistDelete(User entity) {
        dummyDB.getUsersDB().remove(entity.getId());
    }

}
