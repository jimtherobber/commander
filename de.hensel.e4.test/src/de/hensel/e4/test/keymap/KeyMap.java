package de.hensel.e4.test.keymap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyMap {
	Map<Integer, List<Integer>> keyMap = new HashMap<>();
	private Integer actualKey;
	
	private KeyMap(){}
	
	private static KeyMap instance = null;
	
	public static KeyMap getInstance(){
        if (instance == null) {
            instance = new KeyMap();
        }
        return instance;
	}
	
	public boolean isKeyKnown(Integer key){
		return keyMap.containsKey(key);
	}
	
	public List<Integer> getKeyListFromMap(Integer key){
		return keyMap.get(key);
	}
	
	public void insert(Integer key , List<Integer> values){
		if(key != null && values != null){
			keyMap.put(key, values);
		}
	}

	public Integer getActualKey() {
		return actualKey;
	}

	public void setActualKey(Integer actualKey) {
		this.actualKey = actualKey;
	}
}
