package de.hensel.e4.test.keymap.service;

import java.util.HashMap;
import java.util.Map;

import de.hensel.e4.test.encrypt.Decryption;
import de.hensel.e4.test.encrypt.Encryption;
import de.hensel.e4.test.keymap.KeyMap;

public class KeyMapService implements IKeyMapService {

	Encryption encryption = new Encryption();
	Decryption decryption = new Decryption();
	
	private Map<Integer,String> encryptedTextMap = new HashMap<>();
	
	/* (non-Javadoc)
	 * @see de.hensel.e4.test.keymap.service.IKeyMapService#getActualKey()
	 */
	@Override
	public Integer getActualKey(){
		return KeyMap.getInstance().getActualKey();
	}
	
	/* (non-Javadoc)
	 * @see de.hensel.e4.test.keymap.service.IKeyMapService#getKeyMap()
	 */
	@Override
	public KeyMap getKeyMap(){
		return KeyMap.getInstance();
	}

	@Override
	public void setActualKey(Integer key) {
		KeyMap.getInstance().setActualKey(key);
	}

	@Override
	public void setEncryption(Encryption mode) {
		encryption = mode;
		
	}

	/* (non-Javadoc)
	 * @see de.hensel.e4.test.keymap.service.IEncryption#getEncryption()
	 */
	@Override
	public Encryption getEncryption() {
		return encryption;
	}

	@Override
	public void setDecryption(Decryption mode) {
		decryption = mode;
	}

	/* (non-Javadoc)
	 * @see de.hensel.e4.test.keymap.service.IEncryption#getEncryptedText()
	 */
	public Map<Integer,String> getEncryptedText() {
		return encryptedTextMap;
	}

	@Override
	public String encrypt(String text) {
		String encryptedText = encryption.encryptText(text);
		getKeyMap().insert(encryption.getHashCode(), encryption.getKey());
		setActualKey(encryption.getHashCode());
		encryptedTextMap.put(encryption.getHashCode(), text);
		return encryptedText;
	}

	@Override
	public String decrypt(String text) {
		decryption.setKey(getKeyMap().getKeyListFromMap(getActualKey()));
		return decryption.decrypt(text);
	}
}
