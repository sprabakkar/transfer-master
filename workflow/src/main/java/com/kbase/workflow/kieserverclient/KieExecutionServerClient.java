package com.kbase.workflow.kieserverclient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.KieContainerResource;
import org.kie.server.api.model.KieContainerResourceList;
import org.kie.server.api.model.ReleaseId;
import org.kie.server.api.model.definition.ProcessDefinition;
import org.kie.server.api.model.instance.NodeInstance;
import org.kie.server.api.model.instance.ProcessInstance;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.ProcessServicesClient;
import org.kie.server.client.QueryServicesClient;

public class KieExecutionServerClient {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        String serverUrl = "http://140.147.210.134:8080/kie-server/services/rest/server";
        String user = "kieserver";
        String password = "kieserver1!";

        String containerId = "U1Repo1P1Container";
        String processId = "U1Repo1P1.u1r1p1process1";//fourproject.fourprocess, oneproject.oneprocess, threeproject.threeProcess, twoproject.twoprocess

        KieServicesConfiguration configuration = KieServicesFactory.newRestConfiguration(serverUrl, user, password);

        configuration.setMarshallingFormat(MarshallingFormat.JAXB);
//        configuration.addJaxbClasses(extraClasses);
//        KieServicesClient kieServicesClient =  KieServicesFactory.newKieServicesClient(configuration, kieContainer.getClassLoader());
        KieServicesClient kieServicesClient =  KieServicesFactory.newKieServicesClient(configuration);
        
        boolean deployContainer = true;
        KieContainerResourceList containers = kieServicesClient.listContainers().getResult();
        // check if the container is not yet deployed, if not deploy it
        if (containers != null) {
            for (KieContainerResource kieContainerResource : containers.getContainers()) {
                if (kieContainerResource.getContainerId().equals(containerId)) {
                    System.out.println("\t######### Found container " + containerId + " skipping deployment...");
                    deployContainer = false;
                    break;
                }
            }
        }
        // deploy container if not there yet        
        if (deployContainer) {
            System.out.println("\t######### Deploying container " + containerId);
            KieContainerResource resource = new KieContainerResource(containerId, new ReleaseId("org.OrgUnit1.U1Repo1P1", "U1Repo1P1", "1.1"));//ReleaseId(String groupId, String artifactId, String version)
            kieServicesClient.createContainer(containerId, resource);
        }
        // query for all available process definitions
        QueryServicesClient queryClient = kieServicesClient.getServicesClient(QueryServicesClient.class);
        List<ProcessDefinition> processes = queryClient.findProcesses(0, 10);
        System.out.println("\t######### Available processes" + processes);

        ProcessServicesClient processClient = kieServicesClient.getServicesClient(ProcessServicesClient.class);
        // get details of process definition
        ProcessDefinition definition = processClient.getProcessDefinition(containerId, processId);
        System.out.println("\t######### Definition details: " + definition);

        // start process instance
        Map<String, Object> params = new HashMap<String, Object>();
        //params.put("name", "john");
        //params.put("age", 25);
        //Long processInstanceId = processClient.startProcess(containerId, processId, params);
        Long processInstanceId = processClient.startProcess(containerId, processId);
        System.out.println("\t######### Process instance id: " + processInstanceId);
        
        List<NodeInstance> completedNodes = queryClient.findCompletedNodeInstances(processInstanceId, 0, 10);
        System.out.println("\t######### Completed nodes: " + completedNodes);
/**
        UserTaskServicesClient taskClient = kieServicesClient.getServicesClient(UserTaskServicesClient.class);
        // find available tasks
        List<TaskSummary> tasks = taskClient.findTasksAssignedAsPotentialOwner(user, 0, 10);
        System.out.println("\t######### Tasks: " +tasks);

        // complete task
        Long taskId = tasks.get(0).getId();

        taskClient.startTask(containerId, taskId, user);
        taskClient.completeTask(containerId, taskId, user, null);

        // work with rules
        List<GenericCommand<?>> commands = new ArrayList<GenericCommand<?>>();
        BatchExecutionCommandImpl executionCommand = new BatchExecutionCommandImpl(commands);
        executionCommand.setLookup("defaultKieSession");

        InsertObjectCommand insertObjectCommand = new InsertObjectCommand();
        insertObjectCommand.setOutIdentifier("person");
        insertObjectCommand.setObject("john");

        FireAllRulesCommand fireAllRulesCommand = new FireAllRulesCommand();

        commands.add(insertObjectCommand);
        commands.add(fireAllRulesCommand);

        RuleServicesClient ruleClient = kieServicesClient.getServicesClient(RuleServicesClient.class);
        ruleClient.executeCommands(containerId, executionCommand);
        System.out.println("\t######### Rules executed");
        
        completedNodes = queryClient.findCompletedNodeInstances(processInstanceId, 0, 10);
        System.out.println("\t######### Completed nodes: " + completedNodes);
        
        List<ProcessInstance> instances = queryClient.findProcessInstances(0, 10);
        System.out.println("\t######### Active process instances: " + instances);
*/
        // at the end abort process instance
        //processClient.abortProcessInstance(containerId, processInstanceId);

        ProcessInstance processInstance = queryClient.findProcessInstanceById(processInstanceId);
        System.out.println("\t######### ProcessInstance: " + processInstance);
 
        System.out.println("Execution completed in " + (System.currentTimeMillis() - start));

    }
}
