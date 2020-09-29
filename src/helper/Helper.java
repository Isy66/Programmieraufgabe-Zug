package helper;

public class Helper {

    public static boolean isNumeric(String str) {
        if (str == null)
            return false;
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isPositiveNumeric(String str) {
        if (isNumeric(str)){
            double d = Double.parseDouble(str);
            if(d >= 0){
                return true;
            }
        }
        return false;
    }

    public static boolean isNumeric(String... str) {
        for (String s : str)
            if(!isNumeric(s)) return false;
        return true;
    }

    public static boolean isPositiveNumeric(String... strings) {
        for (String s : strings)
            if(!isPositiveNumeric(s)) return false;
        return true;
    }

    public static boolean isNull(Object... objects) {
        if(objects==null)
            return true;
        for (Object object : objects) {
            if(object == null) return true;
        }
        return false;
    }


}
