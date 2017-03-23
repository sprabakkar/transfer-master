package com.kbase.workflow.wihandlers;

import java.util.HashMap;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;

public class CreditLimitWorkItemHandler
implements WorkItemHandler {
    @Override
	public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        Integer ssdLimit = (Integer)workItem.getParameter("ssdLimit");
        int card_limit = ssdLimit * 60 / 100 * 2;
        System.out.println("card_limit :::" + card_limit);
        HashMap<String, Object> results = new HashMap<String, Object>();
        results.put("Result", card_limit);
        manager.completeWorkItem(workItem.getId(), results);
    }

    @Override
	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
    }
}