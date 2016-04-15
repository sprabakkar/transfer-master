package gov.loc.cts.workflow.wihandlers;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;

public class AddBagWorkItemHandler implements WorkItemHandler {

	@Override
	public void abortWorkItem(WorkItem workItem, WorkItemManager workItemManager) {
		workItemManager.abortWorkItem(workItem.getId());

	}

	@Override
	public void executeWorkItem(WorkItem workItem, WorkItemManager workItemManager) {
		String projectId = (String)workItem.getParameter("projectId");
		String bagId = (String)workItem.getParameter("bagId");
		String version = (String)workItem.getParameter("version");
		System.out.println("ProjectId is ::: "+projectId);
		System.out.println("BagId is ::: "+bagId);
		System.out.println("Version is ::: "+version);

        System.out.println("New Bag information added successfully!!!");
        workItemManager.completeWorkItem(workItem.getId(), null);
	}
}
