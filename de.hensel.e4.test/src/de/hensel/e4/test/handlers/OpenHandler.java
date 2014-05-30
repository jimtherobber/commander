/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Lars Vogel <lars.Vogel@gmail.com> - Bug 419770
 *******************************************************************************/
package de.hensel.e4.test.handlers;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.model.application.ui.basic.MWindowElement;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import de.hensel.e4.test.file.FileReaderIO;
import de.hensel.e4.test.parts.TestPart;
public class OpenHandler {
	
	@Inject 
	EPartService partService;
	
	FileReaderIO reader;

	@Execute
	public void execute(Shell shell){
		
		File file = reader.openFileDialog(shell);
		MPart part = partService.findPart(TestPart.ID);
		
		if(part.getObject() instanceof TestPart){
			reader = new FileReaderIO();
			((TestPart)part.getObject()).setText(reader.readFile(file));
			partService.bringToTop(part);
			part.setVisible(true);
		}
	}

}
