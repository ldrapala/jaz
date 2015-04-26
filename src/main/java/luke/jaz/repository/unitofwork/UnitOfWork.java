package luke.jaz.repository.unitofwork;

import java.util.LinkedHashMap;
import java.util.Map;
import luke.jaz.entity.Entity;
import luke.jaz.entity.EntityState;

public class UnitOfWork implements IUnitOfWork {

    private final Map<Entity, IUnitOfWorkRepository> entities;

    public UnitOfWork() {
        this.entities = new LinkedHashMap<>();
    }
    
    @Override
    public void commit() {
        for (Entity entity : entities.keySet()) {
            switch (entity.getEntityState()) {
                case CHANGED:
                    entities.get(entity).persistUpdate(entity);
                    break;
                case DELETED:
                    entities.get(entity).persistDelete(entity);
                    break;
                case NEW:
                    entities.get(entity).persistAdd(entity);
                    break;
                case UNCHANGED:
                    break;
                default:
                    break;
            }
        }
        entities.clear();
    }

    @Override
    public void rollback() {
        entities.clear();
    }

    @Override
    public void markAsNew(Entity entity, IUnitOfWorkRepository repository) {
        entity.setEntityState(EntityState.NEW);
        entities.put(entity, repository);
    }

    @Override
    public void markAsDirty(Entity entity, IUnitOfWorkRepository repository) {
        entity.setEntityState(EntityState.CHANGED);
        entities.put(entity, repository);
    }

    @Override
    public void markAsDeleted(Entity entity, IUnitOfWorkRepository repository) {
        entity.setEntityState(EntityState.DELETED);
        entities.put(entity, repository);
    }

}

