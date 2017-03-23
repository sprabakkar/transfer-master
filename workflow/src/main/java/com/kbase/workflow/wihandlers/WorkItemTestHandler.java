package com.kbase.workflow.wihandlers;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;

public class WorkItemTestHandler implements WorkItemHandler {

/*	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}*/

	@Override
	public void abortWorkItem(WorkItem arg0, WorkItemManager arg1) {
		System.out.println("Oh no, my item aborted..");
		
	}

	@Override
	public void executeWorkItem(WorkItem workitem, WorkItemManager wmanager) {
		System.out.println("Hello World!");
		wmanager.completeWorkItem(workitem.getId(), null);
		
	}

}
