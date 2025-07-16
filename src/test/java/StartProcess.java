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
    @Test
    public void startProcess() {
        log.info("开始启动流程");
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Free");
        System.err.println(processInstance);

        ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
        ProcessInstanceQuery processInstanceQuery1 = processInstanceQuery.processInstanceId("15001");
        System.err.println(processInstanceQuery1);
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
}
