public class Man {
    public static String makeDativeMan(String ln, String fn, String mn) {
        if (ln.endsWith("ов") || ln.endsWith("ев")) {ln = ln + "у";}
        if (fn.endsWith("а") || fn.endsWith("я")) {
            fn = fn.substring(0, fn.length() - 1) + "е";
        } else if (fn.endsWith("й") || fn.endsWith("ь")) {
            fn = fn.substring(0, fn.length() - 1) + "ю";
        } else if (!fn.endsWith("и") && !fn.endsWith("о")) {
            fn = fn + "у";
        }
        if (mn.endsWith("ич")) {mn = mn + "у";}
        return ln + " " + fn + " " + mn;
    }
    public static String makeShortMan(String ln, String fn, String mn) {
        String nameInit = "";
        String middleInit = "";
        if (!fn.isEmpty()) {
            nameInit = fn.charAt(0) + ".";
        }
        if (!mn.isEmpty()) {
            middleInit = mn.charAt(0) + ".";
        }
        return ln + " " + nameInit + middleInit;
    }
    public static String makeGenitiveMan(String ln, String fn, String mn) {
        if (ln.endsWith("ов") || ln.endsWith("ев")) {ln = ln + "а";}
        if (fn.endsWith("а")) {
            fn = fn.substring(0, fn.length() - 1) + "ы";
        } else if (fn.endsWith("я")) {
            fn = fn.substring(0, fn.length() - 1) + "и";
        } else if (fn.endsWith("й")) {
            fn = fn.substring(0, fn.length() - 1) + "я";
        } else if (!fn.endsWith("и") && !fn.endsWith("о")) {fn = fn + "а";}
        if (mn.endsWith("ич")) {mn = mn + "а";}
        return ln + " " + fn + " " + mn;
    }
    public static String makeFullMan(String ln, String fn, String mn) {
        return ln + " " + fn + " " + mn;
    }
}
