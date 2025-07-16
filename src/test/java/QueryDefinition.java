import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentQuery;
import org.junit.Test;


@Slf4j
public class QueryDefinition {
    @Test
    public void query() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();
        // 查询流程部署
        deploymentQuery.list().forEach(deployment -> {
            log.info("流程定义ID:{}", deployment.getId());
            log.info("流程定义名称:{}", deployment.getName());
            log.info("流程定义Key:{}", deployment.getKey());
            log.info("流程定义版本:{}", deployment.getVersion());
            log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        });
        // 查询流程定义
        repositoryService.createProcessDefinitionQuery()
                .list()
                .forEach(processDefinition -> {
                    log.info("流程定义ID:{}", processDefinition.getId());
                    log.info("流程定义名称:{}", processDefinition.getName());
                    log.info("流程定义Key:{}", processDefinition.getKey());
                    log.info("流程定义版本:{}", processDefinition.getVersion());
                    log.info("流程定义部署ID:{}", processDefinition.getDeploymentId());
                    log.info("-----------------------------");
                });
    }
}
