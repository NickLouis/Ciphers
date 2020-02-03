import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LettAnalysis {

	private final double [] avgCounts = {8.2, 1.5, 2.8, 4.3, 12.7, 2.2, 2.0, 6.1, 7.0, 0.2, 0.8, 4.0, 2.4, 
			6.7, 7.5, 1.9, 0.1, 6.0, 6.3, 9.1, 2.8, 1.0, 2.4, 0.2, 2.0, 0.1};

	File file = null;
	FileWriter fw=null;
	String s;
	public LettAnalysis(String s) {
		this.s=s;
	}



	public void fileMake(String arg) {

		double [] letters =  new double[26];

		String text = arg;
		text = text.toUpperCase();

		char [] alpha = new Vigenere().generateAlphabet();
		int lettCount = 0;

		for (char a='A';a<='Z';a++) {
			for (int i = 0;i<text.length()-1;i++) {
				if (text.charAt(i)==a) {
					letters[lettCount]++;
				}
			}
			lettCount++;
		}

		try {
			file = new File("Analyse.txt");
			fw = new FileWriter(file);


			String top = String.format("%5s %13s %15s %15s %15s","Letter", "Freq","Freq%","Ave Freq%","Diff" );
			fw.write(top);
			for (int i =0;i<letters.length;i++) {
				fw.write("\n\r\n\r");
				fw.write(String.format("%5c   %13f  %13f   %13f   %13f", alpha[i], letters[i], letters[i]/text.length()*100, avgCounts[i],letters[i]/text.length()*100-avgCounts[i] ));

			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fw.flush();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}			
	}	
}
