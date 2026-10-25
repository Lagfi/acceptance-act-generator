public class Women {
    public static String makeDativeWoman(String ln, String fn, String mn) {
        if (ln.endsWith("ая")) {
            ln = ln.substring(0, ln.length() - 2) + "ой"; // Черновская > Черновской
        } else if (ln.endsWith("а") || ln.endsWith("я")) {
            ln = ln.substring(0, ln.length() - 1) + "ой"; // Шилина > Шилиной
        } else if (ln.endsWith("ь")) {
            ln = ln.substring(0, ln.length() - 1) + "и";
        }
        if (fn.endsWith("ия")) {
            fn = fn.substring(0, fn.length() - 2) + "ии"; // Мария > Марии
        } else if (fn.endsWith("а")) {
            fn = fn.substring(0, fn.length() - 1) + "е";   // Снежанна > Снежанне
        } else if (fn.endsWith("я")) {
            fn = fn.substring(0, fn.length() - 1) + "е";   // Таня > Тане
        }
        if (mn.endsWith("вна") || mn.endsWith("чна")) {
            mn = mn.substring(0, mn.length() - 1) + "е";   // Андреевна > Андреевне
        }
        return ln + " " + fn + " " + mn;
    }

    public static String makeShortWoman(String ln, String fn, String mn) {
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

    public static String makeGenitiveWoman(String ln, String fn, String mn) {
        if (ln.endsWith("ая")) {
            ln = ln.substring(0, ln.length() - 2) + "ой";
        } else if (ln.endsWith("а") || ln.endsWith("я")) {
            ln = ln.substring(0, ln.length() - 1) + "ой";
        } else if (ln.endsWith("ь")) {
            ln = ln.substring(0, ln.length() - 1) + "и";
        }

        if (fn.endsWith("ия")) {
            fn = fn.substring(0, fn.length() - 2) + "ии";
        } else if (fn.endsWith("а")) {
            fn = fn.substring(0, fn.length() - 1) + "ы";
            if (fn.endsWith("гы") || fn.endsWith("кы") || fn.endsWith("хы")) {
                fn = fn.substring(0, fn.length() - 1) + "и";
            }
        } else if (fn.endsWith("я")) {
            fn = fn.substring(0, fn.length() - 1) + "и";  // Катя > Кати
        } else if (fn.endsWith("ь")) {
            fn = fn.substring(0, fn.length() - 1) + "и";
        }

        if (mn.endsWith("вна") || mn.endsWith("чна")) {
            mn = mn.substring(0, mn.length() - 1) + "ы";
        }
        return ln + " " + fn + " " + mn;
    }
    public static String makeFullWoman(String ln, String fn, String mn) {
        return ln + " " + fn + " " + mn;
    }
}