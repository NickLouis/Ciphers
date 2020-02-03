import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileHandler {


	//variables
	private FileReader fr;
	private PrintWriter fw;
	private Scanner cons = new Scanner (System.in);
	private Scanner files;

	public static boolean repeat (String y) {
		for (int i=0;i<y.length();i++) {
			for (int j=0;j<y.length();j++) {
				if (!(i==j) && y.charAt(i)==y.charAt(j)) {
					return false;
				}
			}
		}
		return true;
	}

	public String read () {


		System.out.println("please enter a file name");
		String s = cons.next();
		String message = "";
		String key ="";

		boolean goodKey = false;
		while (!goodKey) {
			System.out.println("Please enter a valid key. Valid keys contain no repeated letters");
			key = cons.next();
			goodKey = repeat(key);
		}

		try {
			fr = new FileReader(s);

			files = new Scanner(fr);

			while(files.hasNextLine()) {

				message+=files.nextLine();

			}
			System.out.println(message);

		}catch(FileNotFoundException e) {
			System.out.println("No Such File");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		if (s.charAt(s.length()-1)=='P') 
			//return new Mono().startEncryption(key, message)+"P";
			return new Vigenere().startEncryption(key,message) + "P";
		else
			return new Vigenere().startDecryption(key,message) + "C";
			//return  new Mono().startDecryption(key, message)+"C";
	}

	public void write() {
		String writable= read();
		LettAnalysis la = new LettAnalysis(writable);
		la.fileMake(writable);
		StringBuilder toWrite = new StringBuilder(writable);
		//	StringBuilder toWrite = new StringBuilder(read()); 
		File file;
		try {

			if (toWrite.charAt(toWrite.length()-1)=='P') {
				toWrite= toWrite.deleteCharAt(toWrite.length()-1);
				file = new File("EncryptC.txt");

				fw = new PrintWriter(file);
				fw.println(toWrite);
				fw.close();}


			else if (toWrite.charAt(toWrite.length()-1)=='C') {
				toWrite= toWrite.deleteCharAt(toWrite.length()-1);
				file = new File("DecryptD.txt");

				fw = new PrintWriter(file);
				fw.println(toWrite);
				fw.close();}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
