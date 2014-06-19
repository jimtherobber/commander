package de.hensel.e4.test.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;
import javax.inject.Named;

import de.hensel.e4.test.file.FileReaderIO;
import de.hensel.e4.test.keymap.KeyMap;
import de.hensel.e4.test.keymap.service.KeyMapService;

public class Encryption {
	
	private List<Character> key = null;
	private Integer hashCode = null;
	private FileReaderIO fileIO = null;
	
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
		setHashCode(text.hashCode());
		key = new ArrayList<>();
		StringBuilder encryptedText = new StringBuilder();
		Random r = new Random(text.hashCode());
		for(int i = 0; i<length; i++){
			Integer eInt = r.nextInt(255);
			getKey().add(Character.toChars(eInt)[0]);
			encryptedText.append(Character.toChars(eInt^Integer.valueOf(text.charAt(i)))[0]);
		}
		return encryptedText.toString();
	}
	
	private void writeTempFile(String text){
		fileIO = new FileReaderIO();
		fileIO.writeFile(text);
	}

	public String generateHashKey(String text) throws NoSuchAlgorithmException{
		
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(text.getBytes());
		String encryptedString = new String(messageDigest.digest());
		return encryptedString;
	}

	public List<Character> getKey() {
		return key;
	}

	public Integer getHashCode() {
		return hashCode;
	}

	public void setHashCode(Integer hashCode) {
		this.hashCode = hashCode;
	}
}
