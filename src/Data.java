import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Data {
    public static String getDateRu() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatru = DateTimeFormatter.ofPattern("d MMMM yyyy'г.'", Locale.of("ru"));
        return today.format(formatru);
    }
}