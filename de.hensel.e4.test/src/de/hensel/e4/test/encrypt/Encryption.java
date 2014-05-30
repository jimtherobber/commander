package de.hensel.e4.test.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

import de.hensel.e4.test.keymap.KeyMap;

public class Encryption {
	
	List<Integer> activeKey = new ArrayList<Integer>();
	KeyMap keyMap = null;
	Map<Integer, String> encryptedMap = new HashMap<>();
	
	/**
	 * Gibt eine Basisverschlüsselung für den EIngabeText zurück
	 * @param text
	 * @return
	 */
	public String encryptText(String text){
		StringBuilder encryptedText = new StringBuilder();
		if(text != null && !text.isEmpty()){
			encryptedText.append(encryptToIntegerOutput(text.length(), text));
		}
		return encryptedText.toString();
	}
	
	private String encryptToIntegerOutput(int length,String text) {
		keyMap = KeyMap.getInstance();
		List<Integer> key = new ArrayList<>();
		Integer hashCode = text.hashCode();
		StringBuilder encryptedText = new StringBuilder();
		Random r = new Random(text.hashCode());
		for(int i = 0; i<length; i++){
			Integer eInt = r.nextInt(1000);
			key.add(eInt);
			encryptedText.append(Character.toChars(eInt^Integer.valueOf(text.charAt(i))));
		}
		keyMap.insert(hashCode, key);
		encryptedMap.put(hashCode, encryptedText.toString());
		return encryptedText.toString();
	}
	
	private String encryptToCharacter(int length, String text){
		List<Integer> key = new ArrayList<>();
		Integer hashCode = text.hashCode();
		StringBuilder encryptedText = new StringBuilder();
		Random r = new Random(text.hashCode());
		for(int i = 0; i<length; i++){
			Integer eInt = r.nextInt();
			key.add(eInt);
			encryptedText.append(Integer.toString(eInt^text.charAt(i)));
		}
		keyMap.insert(hashCode, key);
		encryptedMap.put(hashCode, encryptedText.toString());
		return encryptedText.toString();
	}

	public String generateHashKey(String text) throws NoSuchAlgorithmException{
		
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(text.getBytes());
		String encryptedString = new String(messageDigest.digest());
		return encryptedString;
	}
}
