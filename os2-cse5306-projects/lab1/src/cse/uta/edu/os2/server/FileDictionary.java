package cse.uta.edu.os2.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FileDictionary {

	private static final String DICTIONARY_FILE="Dictionary.txt";
	private File file =null;
	private BufferedReader reader=null;
	
	public FileDictionary(){
		try {
			file = new File(DICTIONARY_FILE);
		} catch (Exception e) {
			System.out.println(this.getClass().getName()+": Exception while opening file name/path ");
			e.printStackTrace();
		}
		
	}
	
	
	public String readDictionary(String word){
		String line=null;
		try {
			reader = new BufferedReader(new FileReader(file));
			while( (line=reader.readLine())!=null){
				System.out.println(line);
				Pattern pattern = Pattern.compile(word, Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(line);
				while(matcher.find()){
					return line;
				}
			}
			reader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println(this.getClass().getName()+": Exception Invalid file name/path ");
			e.printStackTrace();
		}
		catch (IOException e) {
			System.out.println(this.getClass().getName() + ": Exception while reading data from the dictionary file ");
			e.printStackTrace();
		}
		System.out.println(" Word not found in the dictionary ");
		return line;
	}
	
	
	public HashSet<String> getSynonyms(String word){
		HashSet<String> synonyms =new HashSet<String>();
		String words= readDictionary(word);
		if(words!=null){
			String words_array[] =words.split(",");
			if(words_array!=null && words_array.length>0){
				Collections.addAll(synonyms, words_array);
			}
		}
		return synonyms;
	}
	
	public static void main(String args[]){
		FileDictionary dict = new FileDictionary();
		HashSet<String> words =dict.getSynonyms("liquidate");
		System.out.println(words.toString());
	}
}
