package luke.jaz.jsp;

public class JspUrlBuilder {
    
    private static final String POINT = ".";

    private JspUrlBuilder() {
    }
    
   public static String build(String name){
       return POINT+name;
   }

}
