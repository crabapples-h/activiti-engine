import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

@Slf4j
public class Test1 {
    /**
     * 安装Activiti
     */
    @Test
    public void testInit() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.err.println(processEngine);
    }

    /**
     * 部署流程
     */
    @Test
    public void testDeploy() {
        // 启动流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 部署流程文件
        RepositoryService repository = processEngine.getRepositoryService();
        Deployment deploy = repository.createDeployment()
                .addClasspathResource("bpmn/Free.bpmn20.xml")
                .addClasspathResource("bpmn/Free.png")
                .name("请假流程").deploy();
        System.err.println(deploy.getId());
        System.err.println(deploy.getName());
    }

    /**
     * 启动流程
     */
    @Test
    public void testStart() {
        // 启动流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 启动流程
        RuntimeService service = processEngine.getRuntimeService();
        ProcessInstance instance = service.startProcessInstanceByKey("Free");

    }

    /**
     * 查询待办任务
     */
    @Test
    public void testTask() {
        // 启动流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 启动流程
        TaskService service = processEngine.getTaskService();
        final List<Task> list = service.createTaskQuery()
                .processDefinitionName("Free")
                .taskAssignee("张三")
                .list();
        for (Task task : list) {
            System.err.println(task);
        }

    }
}
