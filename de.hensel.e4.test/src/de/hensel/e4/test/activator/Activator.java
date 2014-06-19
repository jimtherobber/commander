package de.hensel.e4.test.activator;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.prefs.PreferencesService;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import de.hensel.e4.test.encrypt.Decryption;
import de.hensel.e4.test.encrypt.Encryption;
import de.hensel.e4.test.keymap.KeyMap;
import de.hensel.e4.test.keymap.service.IKeyMapService;
import de.hensel.e4.test.keymap.service.KeyMapService;

public class Activator implements BundleActivator {

        // ServiceTracker for PreferencesServices  
        private ServiceTracker serviceTracker;  
        // BundleContext  
        private BundleContext bc;  
        // Registration of Echo service  
        private ServiceRegistration registration;  

        // activation  
        public void start(BundleContext context) throws Exception {  
            bc = context;  
            // init and start ServiceTracker to track PreferencesService  
            serviceTracker = new ServiceTracker(context, PreferencesService.class.getName(), new Customizer());  
            serviceTracker.open();  
        }  

        // deactivation  
        public void stop(BundleContext context) throws Exception {  
            // stop ServiceTracker to track PreferencesService  
            serviceTracker.close();  
            serviceTracker = null;  
        }  
        // customizer that handles tracked service registration/modification/unregistration events  
        private class Customizer implements ServiceTrackerCustomizer {  
            public Object addingService(ServiceReference reference) {  
                System.out.println("PreferencesService is linked");  
                // register Echo service  
                Dictionary<String, String> props = new Hashtable<String, String>();  
                props.put(IKeyMapService.class.getName(), "KeyMapService");  
                registration = bc.registerService(IKeyMapService.class.getName(), new KeyMapService(), props);  

                return bc.getService(reference);  
            }  

            public void modifiedService(ServiceReference reference, Object service) {  
            }  

            public void removedService(ServiceReference reference, Object service) {  
                // unregister Echo service  
                registration.unregister();  
                System.out.println("PreferencesService is unlinked");  
            }  
        }
}
