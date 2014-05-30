package de.hensel.e4.test.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

public class Encryption {
	
	List<Integer> activeKey = new ArrayList<Integer>();
	Map<Integer, List<Integer>> keyMap = new HashMap<>();
	Map<Integer, String> encryptedMap = new HashMap<>();
	
	/**
	 * Gibt eine Basisverschlüsselung für den EIngabeText zurück
	 * @param text
	 * @return
	 */
	public String encryptText(String text){
		StringBuilder encryptedText = new StringBuilder();
		if(text != null && !text.isEmpty()){
			encryptedText.append(encrypt(text.length(), text));
		}
		return encryptedText.toString();
	}
	
	private String encrypt(int length,String text) {
		List<Integer> key = new ArrayList<>();
		Integer hashCode = text.hashCode();
		StringBuilder encryptedText = new StringBuilder();
		Random r = new Random(text.hashCode());
		for(int i = 0; i<length; i++){
			Integer eInt = r.nextInt();
			key.add(eInt);
			encryptedText.append(Integer.toString(eInt^text.charAt(i)));
		}
		keyMap.put(hashCode, key);
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
