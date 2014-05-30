package de.hensel.e4.test.keymap.service;

import de.hensel.e4.test.keymap.KeyMap;

public class KeyMapService implements IKeyMapService {

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
}
