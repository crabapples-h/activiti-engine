import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 功能描述: 测试流程
 */
//@Slf4j
public class TestProcess {
    Logger log = LoggerFactory.getLogger(TestProcess.class);
    ProcessEngine processEngine = null;
    RepositoryService repositoryService = null;
    RuntimeService runtimeService = null;
    TaskService taskService = null;
    HistoryService historyService = null;
    ManagementService managementService = null;
    DynamicBpmnService dynamicBpmnService = null;


    @Before
    public void initService() {
        processEngine = ProcessEngines.getDefaultProcessEngine();
        // 资源相关服务
        repositoryService = processEngine.getRepositoryService();
        // 运行时相关u五
        runtimeService = processEngine.getRuntimeService();
        // 任务相关服务
        taskService = processEngine.getTaskService();
        // 历史相关服务
        historyService = processEngine.getHistoryService();
        // 引擎相关服务
        managementService = processEngine.getManagementService();
        //
        dynamicBpmnService = processEngine.getDynamicBpmnService();
    }


    /**
     * 查询流程定义
     */
    @Test
    public void testQuery() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        for (ProcessDefinition processDefinition : list) {
            System.err.println(processDefinition);
        }
    }

    /**
     * 部署流程
     */
    @Test
    public void testDeployProcess() {
//        1. 创建ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        2. 获取RepositoryService
        RepositoryService repository = processEngine.getRepositoryService();
//        3. 使用service进行流程部署, 定义一个流程名字,把bpmn和png部署到数据库中
        Deployment deploy = repository.createDeployment()
                .name("请假流程")
                .addClasspathResource("bpmn/Free.bpmn20.xml")
                .addClasspathResource("bpmn/Free.bpmn20.png")
                .deploy();
//        4. 输出部署信息
        log.info("流程部署id:[{}]", deploy.getId());
        log.info("流程部署name:[{}]", deploy.getName());
        log.info("流程部署time:[{}]", deploy.getDeploymentTime());
        log.info("流程部署key:[{}]", deploy.getKey());
        log.info("流程部署tenantId:[{}]", deploy.getTenantId());
        log.info("流程部署category:[{}]", deploy.getCategory());
    }

    /**
     * 启动流程实例
     */
    @Test
    public void testStartProcess() {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("Free");
        log.info("流程实例id:[{}]", instance.getId());
        log.info("流程实例name:[{}]", instance.getName());
        log.info("流程实例processDefinitionId:[{}]", instance.getProcessDefinitionId());
        log.info("流程实例activityId:[{}]", instance.getActivityId());
    }

    /**
     * 查询待办任务
     */
    @Test
    public void testQueryTask() {
        final List<Task> list = taskService.createTaskQuery()
                .processDefinitionName("请假流程")
                .processDefinitionKey("Free")
//                .taskAssignee("zhangsan")
                .list();
        for (Task task : list) {
            log.info("\n任务id:[{}]\n任务当前节点名称:[{}]\n任务当前负责人:[{}]\n创建时间:[{}]\n流程定义ID:[{}]\n" +
                            "流程实例ID:[{}]\n任务executionId:[{}]\n任务taskDefinitionKey:[{}]\n任务description:[{}]\n" +
                            "任务category:[{}]\n任务priority:[{}]\n任务tenantId:[{}]\n任务formKey:[{}]\n任务parentTaskId:[{}]\n" +
                            "任务localVariables:[{}]\n任务processVariables:[{}]\n任务claimTime:[{}]\n任务suspended:[{}]\n" +
                            "任务appVersion:[{}]\n任务businessKey:[{}]\n",
                    task.getId(), task.getName(), task.getAssignee(), task.getCreateTime(), task.getProcessDefinitionId(),
                    task.getProcessInstanceId(), task.getExecutionId(), task.getTaskDefinitionKey(), task.getDescription(),
                    task.getCategory(), task.getPriority(), task.getTenantId(), task.getFormKey(), task.getParentTaskId(),
                    task.getTaskLocalVariables(), task.getProcessVariables(), task.getClaimTime(), task.isSuspended(),
                    task.getAppVersion(), task.getBusinessKey()
            );
        }

    }
}
