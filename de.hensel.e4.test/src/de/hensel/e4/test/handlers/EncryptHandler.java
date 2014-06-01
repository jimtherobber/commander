 
package de.hensel.e4.test.handlers;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.widgets.Shell;

import de.hensel.e4.test.encrypt.Encryption;
import de.hensel.e4.test.keymap.service.IKeyMapService;
import de.hensel.e4.test.parts.TestPart;

public class EncryptHandler {
	
	@Inject 
	EPartService partService;
	
	@Execute
	public void execute(@Named("de.hensel.e4.test.keymap.service.IKeyMapService")IKeyMapService keyMapService) {
		MPart activePart = partService.getActivePart();
		if(activePart.getObject() instanceof TestPart){
			TestPart part = (TestPart) activePart.getObject();
			String toEncrypt = part.getTextField().getText();
			String encryptedText = keyMapService.encrypt(toEncrypt);
			part.setText(encryptedText);
		}
	}
	
	
	@CanExecute
	public boolean canExecute() {
		//TODO Your code goes here
		return true;
	}
		
}