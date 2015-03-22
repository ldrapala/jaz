package luke.jaz.util;

public final class PoolOfIds {
    
    private static int counter = 0;

    private PoolOfIds() {
    }
    
    public static synchronized int generateId(){
        return ++counter;
    }

}
