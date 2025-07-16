import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.junit.Test;

import java.util.List;

@Slf4j
public class StartProcess {

    //          [2025-07-16 16:35:55] INFO [main] : 查询流程定义
//              [2025-07-16 16:35:59] INFO [main] : 流程定义ID:[id-001:1:4]
//            [2025-07-16 16:35:59] INFO [main] : 流程定义名称:[测试流程]
//            [2025-07-16 16:35:59] INFO [main] : 流程定义Key:[id-001]
//            [2025-07-16 16:35:59] INFO [main] : 流程定义版本:[1]
//            [2025-07-16 16:35:59] INFO [main] : 流程定义部署ID:[1]
//            [2025-07-16 16:35:59] INFO [main] : -----------------------------
//            [2025-07-16 16:35:59] INFO [main] : 查询流程部署
//              [2025-07-16 16:35:59] INFO [main] : 流程部署ID:[1]
//            [2025-07-16 16:35:59] INFO [main] : 流程部署名称:[name-001]
//            [2025-07-16 16:35:59] INFO [main] : 流程部署Key:[key-001]
//            [2025-07-16 16:35:59] INFO [main] : 流程部署时间:[Wed Jul 16 16:18:51 CST 2025]
//            [2025-07-16 16:35:59] INFO [main] : 流程部署tenantId:[]
//            [2025-07-16 16:35:59] INFO [main] : 流程部署category:[null]
//            [2025-07-16 16:35:59] INFO [main] : -----------------------------
    @Test
    public void startProcessByKey() {
        log.info("根据[{}]启动流程实例", "流程定义key");// bpmn中的id
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("id-001");
        printDetail(processInstance);
    }

    @Test
    public void startProcessById() {
        log.info("根据[{}]启动流程实例", "流程定义id");// 自动生成的id
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceById("id-001:1:4");
        printDetail(processInstance);
    }

    @Test
    public void startProcessByMessage() {
        log.info("根据[{}]启动流程实例", "流程定义message");// bpmn中的message
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByMessage("test-message");
        System.err.println(processInstance);
    }

    @Test
    public void pauseProcess() {
        log.info("暂停流程");
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
        List<ProcessInstance> list = processInstanceQuery.list();
        for (ProcessInstance instance : list) {
            log.info("流程实例ID:{}", instance.getId());
            log.info("流程定义ID:{}", instance.getProcessDefinitionId());
            log.info("流程实例名称:{}", instance.getName());
            log.info("流程实例状态:{}", instance.isSuspended());
            log.info("-----------------------------");
        }
//        processInstanceQuery.processInstanceName()

    }

    public void printDetail(ProcessInstance processInstance) {
        log.info("流程实例ID:[{}]", processInstance.getId());
        log.info("流程实例名称:[{}]", processInstance.getName());
        log.info("流程实例定义ID:[{}]", processInstance.getProcessDefinitionId());
        log.info("流程实例activityId:[{}]", processInstance.getActivityId());
        log.info("流程实例定义名称:[{}]", processInstance.getProcessDefinitionName());
        log.info("流程实例定义key:[{}]", processInstance.getProcessDefinitionKey());
        log.info("流程实例定义版本:[{}]", processInstance.getProcessDefinitionVersion());
        log.info("流程实例定义deploymentId:[{}]", processInstance.getDeploymentId());
        log.info("流程实例定义rootProcessInstanceId:[{}]", processInstance.getRootProcessInstanceId());
        log.info("流程实例定义parentId:[{}]", processInstance.getParentId());
    }
}
