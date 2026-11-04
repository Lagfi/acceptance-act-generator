import com.github.petrovich4j.Case;
import com.github.petrovich4j.Gender;
import com.github.petrovich4j.NameType;
import com.github.petrovich4j.Petrovich;


public class FioProcessor {
    String name;
    public static String fixRegister(String name){
        try {
            if (name == null || name.isEmpty()) {
                return name;
            }
            name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        }catch (Exception q){
            IO.println("Error 1 " + q);
        }
        return name;
    }
    public static String convertAndWriteFio(String rawFio, Case myCase) {
        try {
            rawFio = rawFio.trim().replaceAll(" +", " ");
            String[] bar = rawFio.split(" ");
            Gender workGender;
            if (bar.length >= 3 && bar[2].endsWith("ич")) {
                workGender = Gender.Male;
            } else if (bar.length >= 3 && bar[2].endsWith("на")) {
                workGender = Gender.Female;
            } else {
                workGender = Gender.Both;
            }
            Petrovich p = new Petrovich();
            String declinedLastName = "";
            String declinedName = "";
            String declinedPatrony = "";
            if (bar.length >= 3) {
                declinedLastName = p.say(fixRegister(bar[0]), NameType.LastName, workGender, myCase);
                declinedName = p.say(fixRegister(bar[1]), NameType.FirstName, workGender, myCase);
                declinedPatrony = p.say(fixRegister(bar[2]), NameType.PatronymicName, workGender, myCase);
                return declinedLastName + " " + declinedName + " " + declinedPatrony;
            } else if (bar.length >= 2) {
                declinedLastName = p.say(fixRegister(bar[0]), NameType.LastName, workGender, myCase);
                declinedName = p.say(fixRegister(bar[1]), NameType.FirstName, workGender, myCase);
                return declinedName + " " + declinedLastName;
            }

        }catch (Exception a){
            IO.println("Error 2 " + a);
        }
        return null;
    }
    public static String makeShort(String rawFio) {
        try {
        String[] fio;
        fio = rawFio.split(" ");
        if (fio.length >= 3){
            fio[0] = fixRegister(fio[0]);
            fio[1] = fixRegister(fio[1]).charAt(0) + ".";
            fio[2] = fixRegister(fio[2]).charAt(0) + ".";
            return fio[0] + " " + fio[1] + fio[2];
        }else if (fio.length >= 2){
            fio[0] = fixRegister(fio[0]);
            fio[1] = fixRegister(fio[1]).charAt(0) + ".";
            return fio[0] + " " + fio[1];
        }

    }catch (Exception z){
            IO.println("Error 3 " + z);
        }
        return "";
    }
}
