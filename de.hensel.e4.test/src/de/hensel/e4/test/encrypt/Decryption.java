package de.hensel.e4.test.encrypt;

import java.util.ArrayList;
import java.util.List;


public class Decryption {
	
	private List<Character> key = new ArrayList<>();
	private Integer hashCode = null;

	public String decrypt(String text) {
		StringBuffer decryptedText = new StringBuffer();
		for(int i = 0; i<getKey().size(); i++){
			decryptedText.append(Character.toChars(text.charAt(i)^key.get(i))[0]);
		}
		return decryptedText.toString();
	}

	public List<Character> getKey() {
		return key;
	}

	public void setKey(List<Character> key) {
		this.key = key;
	}

	public Integer getHashCode() {
		return hashCode;
	}

	public void setHashCode(Integer hashCode) {
		this.hashCode = hashCode;
	}

}
