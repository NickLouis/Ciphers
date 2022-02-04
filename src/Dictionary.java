package com.beispiel.myproject.MyPoject.model;

import java.io.*;
import java.util.*;


public class Dictionary {
	
	private static Dictionary instance = null;
	private List<String> dictionary = null;
	
	private Dictionary() {}
	
	public static Dictionary getInstance() {
		if (instance==null) {
			instance = new Dictionary();
		}
		return instance;
	}
	
	
	public List<String> getDictionary(){
		if (dictionary==null ) {
			BufferedReader br;
			dictionary = new ArrayList<String>();
			try {
				br = new BufferedReader(new FileReader(new File("src/main/resources/engmix.txt")));
				String s;
				while((s=br.readLine())!=null) {
					dictionary.add(s);
				}		
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return dictionary;
	}
	

}
