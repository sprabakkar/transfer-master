package gov.loc.cts.workflow.wihandlers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;



public class InterviewWorkItemHandler
implements WorkItemHandler {
    @Override
	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
        manager.abortWorkItem(workItem.getId());
    }

    @Override
	public void executeWorkItem(WorkItem workItem, WorkItemManager workItemManager) {
        System.out.println("Candidate selected for interview is Prabakar  ::: ");
        InterviewService interviewService = new InterviewService();
        List interviewerList = interviewService.getInterviewerList();
        System.out.println("interviewerList size is ::: " + interviewerList.size());
        Map<String, Object> results = new HashMap<String, Object>();
        results.put("Result", interviewerList);
        workItemManager.completeWorkItem(workItem.getId(), results);
    }
}
