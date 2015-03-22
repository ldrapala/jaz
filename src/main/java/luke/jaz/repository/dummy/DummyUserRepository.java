package luke.jaz.repository.dummy;

import java.util.ArrayList;
import java.util.List;
import luke.jaz.entity.User;
import luke.jaz.entity.builder.IEntityBuilder;
import luke.jaz.repository.Repository;
import luke.jaz.repository.unitofwork.IUnitOfWork;
import luke.jaz.repository.unitofwork.IUnitOfWorkRepository;
import luke.jaz.util.PoolOfIds;

public class DummyUserRepository extends Repository<User> implements IUnitOfWorkRepository<User> {
    
    private final IEntityBuilder<User> builder;
    private final IUnitOfWork unitOfWork;
    private final DummyDB dummyDB;
    
    public DummyUserRepository(DummyDB dummyDB, 
            IEntityBuilder<User> builder, IUnitOfWork unitOfWork) {
        this.dummyDB = dummyDB;
        this.builder = builder;
        this.unitOfWork = unitOfWork;
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
