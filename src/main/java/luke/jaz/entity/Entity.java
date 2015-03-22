package luke.jaz.entity;

public abstract class Entity {
    
    protected int id;
    protected EntityState entityState;

    public int getId() {
        return id;
    }

    public EntityState getEntityState() {
        return entityState;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEntityState(EntityState entityState) {
        this.entityState = entityState;
    }

}
