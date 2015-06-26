package luke.jaz.util;

public final class UserPoolOfIds {
    
    private static int counter = 0;

    private UserPoolOfIds() {
    }
    
    public static synchronized int generateId(){
        return counter++;
    }

}
