import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Mono m = new Mono();
		System.out.println("Enter a keyword");   
        String keyword =  new Scanner(System.in).next().toUpperCase();
        String flag = "E";        
		String mess = "This is some bold cat test text to Encrypt!!"; 
		System.out.println(mess);		
		System.out.println(Utils.convertStringToList(mess));        
		System.out.println(m.encryptDecrypt(mess,flag,keyword));
        List <String> encrypted = m.encryptDecrypt(mess,flag,keyword);
    }






}
