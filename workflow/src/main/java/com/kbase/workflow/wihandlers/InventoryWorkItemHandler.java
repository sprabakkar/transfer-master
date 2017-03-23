package com.kbase.workflow.wihandlers;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;

public class InventoryWorkItemHandler implements WorkItemHandler {

	@Override
	public void abortWorkItem(WorkItem arg0, WorkItemManager arg1) {
		arg1.abortWorkItem(arg0.getId());

	}

	@Override
	public void executeWorkItem(WorkItem workItem, WorkItemManager workItemManager) {
		String projectId = (String)workItem.getParameter("projectId");
		String bagId = (String)workItem.getParameter("bagId");
		String version = (String)workItem.getParameter("version");
		System.out.println("ProjectId is ::: "+projectId);
		System.out.println("BagId is ::: "+bagId);
		System.out.println("Version is ::: "+version);

        System.out.println("Inventory system invoked !!!");
        workItemManager.completeWorkItem(workItem.getId(), null);

	}

}
