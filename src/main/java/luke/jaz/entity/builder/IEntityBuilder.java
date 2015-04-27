package luke.jaz.entity.builder;

import javax.servlet.http.HttpServletRequest;
import luke.jaz.entity.Entity;
import luke.jaz.entity.EntityState;

public interface IEntityBuilder <TEntity extends Entity> {
    
    public TEntity build(HttpServletRequest request);
    
    public TEntity build(TEntity entity);
    
}
