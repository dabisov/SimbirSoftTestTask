package helpers;

public class Utils {
    public static String getLastWordBySeparator(String line, String separator){
        String [] strings = line.split(separator);
        return strings[strings.length - 1];
    }
    public static String getFirstWordBySeparator(String line, String separator){
        String [] strings = line.split(separator);
        return strings[0];
    }
}
