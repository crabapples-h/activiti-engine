import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;

import java.util.List;

@Slf4j
public class TaskTest {
    @Test
    public void queryTask() {
        log.info("查询任务");
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = engine.getTaskService();
        TaskQuery taskQuery = taskService.createTaskQuery();
        List<Task> list = taskQuery.list();
        for (Task task : list) {
            log.info("任务id:[{}]", task.getId());
            log.info("任务名称:[{}]", task.getName());
            log.info("任务创建时间:[{}]", task.getCreateTime());
            log.info("任务负责人:[{}]", task.getAssignee());
            log.info("任务所属流程定义ID:[{}]", task.getProcessDefinitionId());
            log.info("任务所属流程实例ID:[{}]", task.getProcessInstanceId());
            log.info("任务所属流程实例执行对象ID:[{}]", task.getExecutionId());
            log.info("任务所属流程定义的KEY:[{}]", task.getTaskDefinitionKey());
            log.info("任务描述:[{}]", task.getDescription());
            log.info("任务分类:[{}]", task.getCategory());
            log.info("任务优先级:[{}]", task.getPriority());
            log.info("任务租户ID:[{}]", task.getTenantId());
            log.info("任务表单KEY:[{}]", task.getFormKey());
            log.info("任务父任务ID:[{}]", task.getParentTaskId());
            log.info("任务本地变量:[{}]", task.getTaskLocalVariables());
            log.info("任务流程变量:[{}]", task.getProcessVariables());
            log.info("任务是否被挂起:[{}]", task.isSuspended());
            log.info("任务所属流程定义的版本:[{}]", task.getAppVersion());
            log.info("任务所属流程实例的业务键:[{}]", task.getBusinessKey());
            log.info("-----------------------------");
        }
    }

    @Test
    public void completeTask() {
        log.info("完成任务");
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        taskService.complete("45005");
    }

    @Test
    public void testCompleteTask() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        taskService.complete("15005");
    }
}
