package de.hensel.e4.test.keymap.service;

import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;

public class KeyMapContextFunction extends ContextFunction {
	
	@Override
	public Object compute(IEclipseContext context, String contextKey) {
		System.out.println("Creating a new service");
		IKeyMapService todoService = ContextInjectionFactory.make(KeyMapService.class, context);
		// add the new object to the application context
		MApplication application = context.get(MApplication.class);
		IEclipseContext ctx = application.getContext();
		ctx.set(IKeyMapService.class, todoService);
		return todoService;
	}
}
