import java.util.List;
import java.util.stream.Collectors;

public class Utils {

   static List<String> convertStringToList(String in) {
        return in.chars().mapToObj(c -> String.valueOf((char) c)).collect(Collectors.toList());
    }

   static String convertListToString(List<String> toConvert){
        return toConvert.stream().collect(Collectors.joining());
    }
    static boolean checkKey(String key){
        return convertStringToList(key.toUpperCase()).stream().filter(x -> !(x.charAt(0)>90||x.charAt(0)<65)).collect(Collectors.toSet()).size()==key.length();
    }


}
