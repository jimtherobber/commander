 
package de.hensel.e4.test.handlers;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import de.hensel.e4.test.encrypt.Encryption;
import de.hensel.e4.test.keymap.service.IKeyMapService;
import de.hensel.e4.test.parts.TestPart;

public class DecryptHandler {
	
	@Inject 
	EPartService partService;
	
	
	@Execute
	public void execute(@Named("de.hensel.e4.test.keymap.service.IKeyMapService")IKeyMapService keyMapService) {
		MPart activePart = partService.getActivePart();
		if(activePart.getObject() instanceof TestPart){
			TestPart part = (TestPart) activePart.getObject();
			String toDencrypt = part.getTextField().getText();
			String decrypt = keyMapService.decrypt(toDencrypt);
			part.setText(decrypt);
		}
	}
		
}