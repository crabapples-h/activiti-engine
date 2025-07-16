import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

@Slf4j
public class ProcessTest {
    @Test
    public void startProcessByKey() {
        log.info("根据[{}]启动流程实例", "流程定义key");// bpmn中的id
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("bpmn_file_id");
        printDetail(processInstance);
    }

    @Test
    public void startProcessById() {
        log.info("根据[{}]启动流程实例", "流程定义id");// 自动生成的id
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceById("bpmn_file_id:1:27504");
        printDetail(processInstance);

    }

    @Test
    public void startProcessByMessage() {
        log.info("根据[{}]启动流程实例", "流程定义message");// bpmn中的message
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByMessage("testMessage");
        System.err.println(processInstance);
    }

    @Test
    public void pauseProcess() {
        log.info("暂停流程");
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        runtimeService.suspendProcessInstanceById("45005");
    }

    public void printDetail(ProcessInstance processInstance) {
        log.info("流程实例ID:[{}]", processInstance.getId());
        log.info("流程实例名称:[{}]", processInstance.getName());
        log.info("流程定义ID:[{}]", processInstance.getProcessDefinitionId());
        log.info("流程实例activityId:[{}]", processInstance.getActivityId());
        log.info("流程定义名称:[{}]", processInstance.getProcessDefinitionName());
        log.info("流程定义key:[{}]", processInstance.getProcessDefinitionKey());
        log.info("流程定义版本:[{}]", processInstance.getProcessDefinitionVersion());
        log.info("流程实例定义deploymentId:[{}]", processInstance.getDeploymentId());
        log.info("流程实例定义rootProcessInstanceId:[{}]", processInstance.getRootProcessInstanceId());
        log.info("流程实例定义parentId:[{}]", processInstance.getParentId());
    }
}
