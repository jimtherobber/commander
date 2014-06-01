package de.hensel.e4.test.keymap.service;

import java.util.Map;

import de.hensel.e4.test.encrypt.Decryption;
import de.hensel.e4.test.encrypt.Encryption;
import de.hensel.e4.test.keymap.KeyMap;

public interface IKeyMapService {

	Integer getActualKey();

	KeyMap getKeyMap();
	
	void setActualKey(Integer key);
	
	void setEncryption(Encryption mode);
	
	Encryption getEncryption();
	
	void setDecryption(Decryption decryption);

	Map<Integer, String> getEncryptedText();
	
	String encrypt(String text);
	
	String decrypt(String text);

	
}