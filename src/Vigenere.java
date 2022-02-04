package com.beispiel.myproject.MyPoject.model;

import java.util.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Vigenere {

	//public static Iterator<Integer> iter = keyIterableGenerator().iterator();
	public static int i = 0;

	/// this puts us back to the beginning of the cipher array during encryption --> why return?

	public static Integer resetEncrypt() {
		if (i==length()-1) {
			i=0;
			return i;}
		else
			i++;
		return i;
	}




	// This generates and returns the string
	public static String keyword() {
		return "WIMBLEDON";
	}
	// returns length of key --> useful!
	public static int length() {
		return keyword().length();
	}
	/// regular alphabet 4 decryption later
	public static List<Integer> alphaLine() {
		return IntStream
				.range(65, 91)
				.boxed()
				.collect(Collectors.toList());
	}
	// this makes a single line of the cipher enc list
	public static List<Integer> cipherLine (int x) {
		return IntStream
				.concat(IntStream.range(x,91), IntStream.range(65, x))
				.boxed()
				.collect(Collectors.toList());
	}
	/// this is for converting strings to lists of ints and creating vigenere header
	public static List<Integer> stringToList(String s){
		return s.toUpperCase()
				.chars()
				.boxed()
				.collect(Collectors.toList());
	}
	//this makes the cipher encryption list
	public static List<List<Integer>> cipherArray(List <Integer> li){ 
		return li.stream()
				.map(x -> cipherLine(x)).collect(Collectors.toList());	
	}
	/// this is for encrytption/ decrytption changing one character for another
	public static Integer encrypt (List <Integer> list, Integer i) { 
		return list.get(i);
	}


	/// useful for turning the number output into something readable
	public static Character intToChar(Integer i) {
		return Character.valueOf((char)i.intValue());
	}

	/// not 100 per cent sure this will be useful either, but again it may come in handy at some point
	public static List<Character> stringToCharacterList(String s){
		return s.toUpperCase()
				.chars()
				.boxed()
				.collect(Collectors.toList())
				.parallelStream().map(x -> intToChar(x)).collect(Collectors.toList());
	}

	// gets the regular alphabet positions of the letters in the string to encrypt letter by letter
	public static Integer getRegularAlpPos(Integer i) {
		return alphaLine().indexOf(i);

	}

	/// returns a list of the regular alphabet positions in the encryption list
	public static List<Integer> alphabetPositions(List <Integer> list){
		return list.parallelStream().map(x -> getRegularAlpPos(x)).collect(Collectors.toList());
	}


	///// i'm not sure if this will be useful or not but i'll keep it just in case. it gets a list of the 
	public static List<List<Integer>> regularPositionList(List<List<Integer>> list){ 
		return list.
				stream().
				map(li -> li.parallelStream().map(i -> getRegularAlpPos(i)).collect(Collectors.toList()))
				.collect(Collectors.toList());
	}
	// this method is designed to encrypt a single character, it takes a list(normally the encryption line and a positon to fetch the number from
	public static Integer encryptChar(List<List<Integer>> list, Integer pos, Integer keyPos) {
		return list.get(keyPos).get(pos);
	}
	// this method actually does the encryption 
	public static List<Integer> encrypt(){

		return alphabetPositions(stringToList("This is a string to encrypt"))
				.stream()
				.filter (x -> (x>-1))
				.map(x -> encryptChar(cipherArray(stringToList(keyword())),x,resetEncrypt()))
				.collect(Collectors.toList());
	}

	public static void main(String [] args) throws Throwable {

		for(Integer in :encrypt()) {
			System.out.print(intToChar(in)+" ");//// line isn't changing at the moment
		}
		System.out.println();

		//// creating the cipher encryption array
		for (List <Integer> li : cipherArray(stringToList(keyword()))){
			for(Integer i : li) {
				System.out.print((char) Integer.toUnsignedLong(i));	
			}
			System.out.println();
		}


		Pattern p = Pattern.compile("");
		Matcher m = p.matcher("");

	}
	public static void method() throws Throwable {
		throwingErr();
	}
	public static void throwingErr() throws Throwable {
		out();
		throw new Throwable();
	}
	public static void out() {

		for (int i = 0;i<20;i++) {
			System.out.print("Hi");
		}
	}

}

