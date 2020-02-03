public class Vigenere  {

	public  char[] vignereLineCreator(char a) {
		char[] ch = new char[26];
		char j = a;
		for (int i = 0;i<ch.length;i++) {
			if (j>'Z') {j='A';}
			ch[i]=j;
			j++;	
		}
		return ch;
	}
	public  char[] generateAlphabet() {
		char [] alpha = new char [26];
		char a = 'A';
		for (int i=0;i<alpha.length;i++) {
			alpha[i] = a;
			a++;
		}
		return alpha;
	}


	public  char[][] vigenere(String y) {
		char [][] a = new char [y.length()][26];
		for (int i = 0;i<y.length();i++) {
			a[i] =   vignereLineCreator(y.charAt(i));
			System.out.println(a[i]);
		}
		return a;
	}
	public char[][] reverseVigenere(String y){
		char [][] a = new char [y.length()][26];
		for (int i = 0;i<y.length();i++) {
			a[i] =   generateAlphabet();
			System.out.println(a[i]);
		}
		return a;
	}

	public  int[] getPos(char[] encrypt) {
		int [] posArray = new int [encrypt.length];
		char [] alpha =generateAlphabet();
		//getting alphabet pos of letters in messages to encrypt
		for(int i = 0;i<encrypt.length;i++) {
			for (int j=0;j<alpha.length;j++){
				if (encrypt[i]==alpha[j]) {
					posArray[i]=j;
				}
			}
		}
		return posArray;
	}
	public  int[] getRevPos(char[] encrypt, String y) {
		int [] posArray = new int [encrypt.length];
		int k=0;

		for(int i = 0;i<encrypt.length;i++) {
			if(k==y.length()) {k=0;}
			char [] alpha =vignereLineCreator(y.charAt(k));
			k++;
			for (int j=0;j<26;j++){

				if (encrypt[i]==alpha[j]) {

					posArray[i]=j;
				}
			}
		}	
		return posArray;
	}
	
	
	public int [] punctuationPos(char [] in) {
		
		int x=0;
		
		for (char ch : in) {
			if (ch<'A'||ch>'Z') {
				x++;
			}
		}
		int [] out = new int [x];
		int j = 0;
		for (int i=0;i<in.length;i++) {
			if (in[i]<'A'||in[i]>'Z') {
				out[j]=i;
				j++;
			}
		}
		return out;
	}


	public String encrypt(char [] encrypt, String y) {
			
		int [] punctPositions = punctuationPos(encrypt);
		char [] punct= new char [punctPositions.length];
		char [] letters = new char[encrypt.length-punctPositions.length];
		int ind = 0;
		int jind = 0;
		for (char ch:encrypt) {
			if (ch<'A'||ch>'Z') {
				punct[ind]=ch;
				ind++;
			}
			else {letters[jind]=ch;
			jind++;}
		}
	
		int [] posArray = getPos(letters);
		char [][] alp = vigenere(y);
		String encrypted = "";
		int count = 0;
		int change = 0;
		
		for (int i= 0;i<letters.length;i++) {
			for (int j=0;j<letters.length;j++) {			
				if(!(encrypted.length()>letters.length-1)) {
					if (count==posArray.length) {count=0;}
					if (change==y.length()) {change=0;}	
					encrypted+=alp[change][posArray[count]];	
				}
				count++;
				change++; 		
			}
		}
		
		encrypted = reAddPunctuation(encrypted, punct, punctPositions);
		return encrypted;
	}
	
	public String reAddPunctuation(String encrypted, char [] punct, int [] punctPositions) {
		String puncrypted = "";
		char [] enc = encrypted.toCharArray();
		System.out.println("Length of punct Array = "+punct.length);
		System.out.println("Length of puntPositions Array = "+punctPositions.length);
		System.out.println();
		int j = 0;
		int k =0;
		for (int i = 0; i<(enc.length+punct.length);i++) {
			
			if (i==punctPositions[j]) {
				puncrypted+=punct[j];
				j++;
			}else {
				puncrypted+=enc[k];
				k++;}	
		}
		return puncrypted;
		
	}


	public String decrypt(char [] encrypt, String y) {
		
		int [] punctPositions = punctuationPos(encrypt);
		char [] punct= new char [punctPositions.length];
		char [] letters = new char[encrypt.length-punctPositions.length];
		int ind = 0;
		int jind = 0;
		for (char ch:encrypt) {
			if (ch<'A'||ch>'Z') {
				punct[ind]=ch;
				ind++;
			}
			else {letters[jind]=ch;
			jind++;}
		}
		
		int [] newPos = getRevPos(letters,y);
		char [][] alp = reverseVigenere(y);
		String decrypted = "";
		int count = 0;
		int change = 0;


		for (int i= 0;i<letters.length;i++) {
			for (int j=0;j<letters.length;j++) {
				if(!(decrypted.length()>letters.length-1)) {
					if (count==newPos.length) {count=0;}
					if (change==y.length()) {change=0;}	
					decrypted+=alp[change][newPos[count]];	
				}
				count++;
				change++; 
			}
		}
		decrypted = reAddPunctuation(decrypted, punct, punctPositions);
		return decrypted;
	}

	public String startEncryption(String key, String message) {
		key=key.toUpperCase();
		message = message.toUpperCase();
		char[] encrypt = message.toCharArray();
		System.out.println(message);
		String encrypted = encrypt(encrypt,key);
		System.out.println(encrypted);
		return encrypted;
	}

	public String startDecryption(String key, String message) {	
		key=key.toUpperCase();
		message = message.toUpperCase();
		System.out.println(message);
		char[] decrypt = message.toCharArray();
		String decrypted = decrypt(decrypt,key);
		System.out.println(decrypted);
		return decrypted;
	}
}





