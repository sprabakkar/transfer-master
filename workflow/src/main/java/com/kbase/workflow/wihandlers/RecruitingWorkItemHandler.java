package com.kbase.workflow.wihandlers;

import java.util.HashMap;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;

public class RecruitingWorkItemHandler
implements WorkItemHandler {
    @Override
	public void abortWorkItem(WorkItem workItem, WorkItemManager workItemManager) {
        workItemManager.abortWorkItem(workItem.getId());
    }

    @Override
	public void executeWorkItem(WorkItem workItem, WorkItemManager workItemManager) {
        System.out.println("Candidate selected for interview is Prabakar  ::: ");
        HashMap<String, Object> results = new HashMap<String, Object>();
        results.put("Result", "Prabakar");
        workItemManager.completeWorkItem(workItem.getId(), results);
    }
}