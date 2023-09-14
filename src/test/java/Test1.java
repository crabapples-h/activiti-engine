import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

@Slf4j
public class Test1 {
    @Test
    public void testInit() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.err.println(processEngine);
    }

    @Test
    public void testDeploy() {
        // 启动流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 部署流程文件
        RepositoryService repository = processEngine.getRepositoryService();
        Deployment deploy = repository.createDeployment()
                .addClasspathResource("bpmn/Free.bpmn")
                .addClasspathResource("bpmn/Free.png")
                .name("请假流程").deploy();
        System.err.println(deploy.getId());
        System.err.println(deploy.getName());
    }
}
