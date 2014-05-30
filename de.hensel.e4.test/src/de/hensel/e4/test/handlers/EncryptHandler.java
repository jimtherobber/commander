 
package de.hensel.e4.test.handlers;

import javax.inject.Inject;

import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.widgets.Shell;

import de.hensel.e4.test.encrypt.Encryption;
import de.hensel.e4.test.parts.TestPart;

public class EncryptHandler {
	
	@Inject 
	EPartService partService;
	
	@Inject
	IEclipseContext eclipseContext;
	
	
	
	Encryption encryption;
	
	@Execute
	public void execute() {
		MPart activePart = partService.getActivePart();
		eclipseContext.containsKey("de.hensel.e4.test.keymap.service.IKeyMapService");
		eclipseContext.get("de.hensel.e4.test.keymap.service.IKeyMapService");
		encryption = new Encryption();
		if(activePart.getObject() instanceof TestPart){
			TestPart part = (TestPart) activePart.getObject();
			String toEncrypt = part.getTextField().getText();
			String encryptedText = encryption.encryptText(toEncrypt);
			part.setText(encryptedText);
		}
	}
	
	
	@CanExecute
	public boolean canExecute() {
		//TODO Your code goes here
		return true;
	}
		
}