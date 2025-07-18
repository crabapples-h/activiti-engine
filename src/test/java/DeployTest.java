import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;


@Slf4j
public class DeployTest {
    @Test
    public void deploy() {
        log.info("开始部署流程定义");
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        DeploymentBuilder deployment = repositoryService.createDeployment();
        // 使用service进行流程部署, 定义一个流程名字,把bpmn和png部署到数据库中
        deployment.addClasspathResource("bpmn/test-01/Test01.bpmn")
                .addClasspathResource("bpmn/test-01/Test01.png")
                .name("deploy_name_01")
                .key("deploy_key_01")
                .deploy();
    }

    @Test
    public void deleteDeploy() {
        log.info("开始删除流程定义");
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        for (Deployment definition : repositoryService.createDeploymentQuery().list()) {
            repositoryService.deleteDeployment(definition.getId(), true);
        }
    }

    @Test
    public void deployZip() {
        log.info("开始使用ZIP部署流程定义");
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        DeploymentBuilder deployment = repositoryService.createDeployment();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("bpmn/test-02.zip");
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        deployment.addZipInputStream(zipInputStream)
                .deploy();
    }
}
