package luke.jaz.repository.dummy;

import java.util.ArrayList;
import java.util.List;
import luke.jaz.entity.Address;
import luke.jaz.entity.builder.IEntityBuilder;
import luke.jaz.repository.IAddressRepository;
import luke.jaz.repository.unitofwork.IUnitOfWork;
import luke.jaz.repository.unitofwork.IUnitOfWorkRepository;

public class DummyAddressRepository implements IAddressRepository, IUnitOfWorkRepository<Address> {
    
    private final IEntityBuilder<Address> builder;
    private final IUnitOfWork unitOfWork;
    private final DummyDB dummyDB;
    
    public DummyAddressRepository(IEntityBuilder<Address> builder, IUnitOfWork unitOfWork) {
        this.dummyDB = DummyDB.getInstance();
        this.unitOfWork = unitOfWork;
        this.builder = builder;
    }
    
    @Override
    public void delete(Address entity) {
        this.unitOfWork.markAsDeleted(entity, this);
    }

    @Override
    public void save(Address entity) {
        this.unitOfWork.markAsNew(entity, this);
    }

    @Override
    public void update(Address entity) {
        this.unitOfWork.markAsDirty(entity, this);
    }

    @Override
    public Address get(int id) {
        for (Address el : dummyDB.getAddressDB().values()) {
            if(el.getId() == id){
                return builder.build(el);
            }
        }
        return null;
    }
    
    @Override
    public List<Address> getAll() {
        List<Address> result = new ArrayList<>();
        for (Address el : dummyDB.getAddressDB().values()) {
            result.add(builder.build(el));
        }
        return result;
    }
    
    @Override
    public void persistAdd(Address entity) {
        System.out.println("Add address to DB: "+entity);
        dummyDB.getAddressDB().put(entity.getId(), entity);
    }

    @Override
    public void persistUpdate(Address entity) {
        dummyDB.getAddressDB().put(entity.getId(), entity);
    }

    @Override
    public void persistDelete(Address entity) {
        dummyDB.getAddressDB().remove(entity.getId());
    }

}
