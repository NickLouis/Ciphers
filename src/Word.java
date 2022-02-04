package com.beispiel.myproject.MyPoject.model;
import java.util.*;
import java.util.stream.Collectors;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Word {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Words must be called a thing")
	private String name;

	@Transient
	private List<String> anagrams;


	private Boolean palindrome;

	@Transient
	@JsonIgnore
	private Dictionary dictionary;





	////////////////////////////// Id Settings
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}




	//////////////////// Name Settings
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}




	////////////////// Anagram Settings/////////////////////
	public List<String> getAnagrams() {
		return anagrams;
	}

	public void setAnagrams() {

		anagrams = new ArrayList<String>();

		for (String word: dictionary.getDictionary()) {
			if (word.length()==name.length()) {
				String s = setOneAnagram(name, word);
				if (s!=null){
					anagrams.add(word);
				}
			}
		}
	}

	public String setOneAnagram(String word, String ana) {

		List <Integer> orig_set     = word.toUpperCase().chars().sorted().boxed().collect(Collectors.toList());
		List <Integer> checky_set   = ana.toUpperCase().chars().sorted().boxed().collect(Collectors.toList());
	
		if (orig_set.equals(checky_set)) {
			return ana;
		}
		return null;
	}




	/////////// Palindrome Settings
	public void setPalindrome() {
		for (int i = 0,j=name.length()-1;i<name.length()/2+1 && j>name.length()/2-1;i++, j--) {
			if (!(name.charAt(i)==name.charAt(j))) {
				palindrome = false;
				return;
			}
		}
		palindrome = true;
	}

	public Boolean getPalindrome() {
		return palindrome;
	}	



	///////// Dictionary Settings
	public Dictionary getDictionary() {
		return dictionary;
	}
	public void setDictionary() {
		this.dictionary = Dictionary.getInstance();
	}

}
