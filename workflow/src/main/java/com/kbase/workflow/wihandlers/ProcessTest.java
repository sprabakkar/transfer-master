package com.kbase.workflow.wihandlers;

import java.util.HashMap;
import java.util.Map;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * This is a sample file to launch a process.
 */
public class ProcessTest {

    public static final void main(String[] args) {
        try {
        	
        	
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-process");

            // start a new process instance
           
            
            kSession.getWorkItemManager().registerWorkItemHandler("HelloProcessExtension", 
            		                                       new HelloProcessExtension());
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("variable1", "One Variable Prints now....");
            params.put("variable2", "Two Variable Prints now....");
            params.put("variable3", "Three Variable Prints now....");
            

            kSession.startProcess("gov.loc.cts.receive.hello", params);
            kSession.dispose();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
