package luke.jaz.repository.dummy;

import java.util.HashMap;
import java.util.Map;
import luke.jaz.entity.Address;
import luke.jaz.entity.User;

public class DummyDB {
    
    private Map<Integer, User> usersDB;
    private Map<Integer, Address> addressDB;

    private DummyDB() {
        this.usersDB = new HashMap<>();
        this.addressDB = new HashMap<>();
    }
    
    private static class LazyDBHolder {
        private static final DummyDB INSTANCE = new DummyDB();
    }
 
    public static synchronized DummyDB getInstance() {
        return LazyDBHolder.INSTANCE;
    }

    public Map<Integer, User> getUsersDB() {
        return usersDB;
    }

    public Map<Integer, Address> getAddressDB() {
        return addressDB;
    }

}
