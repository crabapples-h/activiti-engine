import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * 功能描述: 测试流程
 */
@Slf4j
public class TestProcess {
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


    @Test
    public void testQuery() {
        System.err.println(repositoryService.getProcessDefinition("Free"));
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
//                .processDefinitionName("请假流程")
                .processDefinitionKey("Free")
                .taskAssignee("zhangsan")
                .list();
        for (Task task : list) {
            log.info("任务id:[{}]", task.getId());
            log.info("任务name:[{}]", task.getName());
            log.info("任务assignee:[{}]", task.getAssignee());
            log.info("任务createTime:[{}]", task.getCreateTime());
            log.info("任务processDefinitionId:[{}]", task.getProcessDefinitionId());
            log.info("任务processInstanceId:[{}]", task.getProcessInstanceId());
            log.info("任务executionId:[{}]", task.getExecutionId());
            log.info("任务taskDefinitionKey:[{}]", task.getTaskDefinitionKey());
            log.info("任务description:[{}]", task.getDescription());
            log.info("任务category:[{}]", task.getCategory());
            log.info("任务priority:[{}]", task.getPriority());
            log.info("任务tenantId:[{}]", task.getTenantId());
            log.info("任务formKey:[{}]", task.getFormKey());
            log.info("任务parentTaskId:[{}]", task.getParentTaskId());
            log.info("任务localVariables:[{}]", task.getTaskLocalVariables());
            log.info("任务processVariables:[{}]", task.getProcessVariables());
            log.info("任务claimTime:[{}]", task.getClaimTime());
            log.info("任务suspended:[{}]", task.isSuspended());
            log.info("任务appVersion:[{}]", task.getAppVersion());
            log.info("任务businessKey:[{}]", task.getBusinessKey());
        }

    }
}
