import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Mono implements Cipher {

    public List<String> generateAlpha() {
        return IntStream.rangeClosed('A', 'Z').mapToObj(y -> Character.toString((char) y)).collect(Collectors.toList());
    }

    List<String> generateCipherAlpha(String key, List<String> keyAsList) {
        keyAsList.addAll(generateAlpha().stream().filter(x -> !key.contains(x)).collect(Collectors.toList()));
        return keyAsList;
    }

    List<String> encryptDecrypt(String toEncrypt, String flag, String key) {

        if (flag.equals("E")) {
           return Utils.convertStringToList(toEncrypt.toUpperCase())
                    .stream()
                    .mapToInt(x -> generateAlpha().indexOf(x) == -1 ? (int) x.charAt(0) : generateAlpha().indexOf(x))
                    .mapToObj(y -> (y<0||y>25)?  String.valueOf((char)y)   :  generateCipherAlpha(key, Utils.convertStringToList(key)).get(y)        )
                    .collect(Collectors.toList());
        }
        return null;

    }
}
