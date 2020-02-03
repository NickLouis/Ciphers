public class Mono   {

	private  boolean getChar(String st, char a) {
		
		for (int i = 0;i<st.length();i++) {

			if(st.charAt(i)==a) {
				
				return true;
			}
		}
		return false;
	}

	public  char[] getMonoCipherArray( String input) {
		// need to change to upper case
		input = input.toUpperCase();
		char [] cha = new char[26];
		int pos = input.length();

		for (int j=0;j<cha.length;j++) {

			if (j<input.length()) {
				cha[j]= input.charAt(j);}

		}

		for (char a = 'A';a<='Z';a++) {

			if (!(getChar(input,a))) {
				cha[pos]= a;
				pos++;
			}
		}
		return cha;
	}

	public  char[] alphabet () {

		char [] alpha = new char [26];
		char a = 'A';
		for (int i=0;i<alpha.length;i++) {
			alpha[i] = a;
			a++;
		}
		return alpha;
	}


	public  int[] posArray(char[] enc) {
		char[] alpha = alphabet();
		int [] posArray = new int [enc.length];
		for(int i = 0;i<enc.length;i++) {

			for (int j=0;j<alpha.length;j++){

				if (enc[i]==alpha[j]) {

					posArray[i]=j;

				}
			}
		}
		return posArray;
	}

	
	public String encrypt(char [] toEnc, char [] cipher) {

		char[]out = new char[toEnc.length];
		char[] alpha = this.alphabet();

		System.out.println();
		for (int i=0;i<out.length;i++) {

			for (int j=0;j<cipher.length;j++) {


				if (toEnc[i]==alpha[j]) {

					out [i]=cipher[j];

				}
			}
			System.out.print(out[i]);
		}
		
		return charToString(out);
	}
	
	public String decrypt(char [] toDec, char [] cipher) {

		char[]out = new char[toDec.length];
		char[] alpha = alphabet();

		System.out.println();
		for (int i=0;i<out.length;i++) {

			for (int j=0;j<cipher.length;j++) {


				if (toDec[i]==cipher[j]) {

					out [i]=alpha[j];

				}
			}
			System.out.print(out[i]);
		}
		return charToString(out);
	}
	

	public String charToString(char [] ch) {
		String out = "";
		for (char car:ch)
			out+=car;
		return out;
	}

	public String startEncryption(String key, String message) {
		String mess = message.toUpperCase();
		char [] toEnc = mess.toCharArray();
		char [] cipher = getMonoCipherArray(key);
		String encrypted;
		encrypted = encrypt(toEnc,cipher);
		return encrypted; 
	}
	public String startDecryption(String key, String message) {
		
		String mess = message.toUpperCase();
		char [] toEnc = mess.toCharArray();
		char [] cipher = getMonoCipherArray(key);
		String decrypted;
		decrypted =  decrypt(toEnc,cipher);
		return decrypted; 
	}
}

