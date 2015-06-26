package luke.jaz.util;

public class AddressPoolOfIds {

    private static int counter = 0;

    private AddressPoolOfIds() {
    }

    public static synchronized int generateId() {
        return counter++;
    }

}
