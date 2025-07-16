import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.junit.Test;


@Slf4j
public class DeployDefinition {
    @Test
    public void deploy() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        DeploymentBuilder deployment = repositoryService.createDeployment();
        deployment.addClasspathResource("bpmn/test-01/Test01.bpmn20.xml")
                .addClasspathResource("bpmn/test-01/Test01.bpmn20.png")
                .name("name-001")
                .key("key-001")
                .deploy();
    }

}
