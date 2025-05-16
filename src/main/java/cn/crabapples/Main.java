package cn.crabapples;

import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;

public class Main {
    public static void main(String[] args) {
//        ProcessEngine publicrocessEngine = ProcessEngines.getDefaultProcessEngine();
        ProcessEngine processEngine = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration()
//                .setJdbcUrl("jdbc:mysql://192.168.10.155:3306/activiti_engine")
                .setJdbcUrl("jdbc:mysql://localhost:3306/activiti_engine")
                .setJdbcUsername("root")
                .setJdbcPassword("root")
                .setJdbcDriver("com.mysql.cj.jdbc.Driver")
//                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
                .buildProcessEngine();
//
//        RepositoryService repositoryService = processEngine.getRepositoryService();
//        repositoryService.createDeployment()
//                .addClasspathResource("bpmn/Free.bpmn20.xml")
//                .deploy();
//
//        RuntimeService runtimeService = processEngine.getRuntimeService();
//        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("start");

        System.err.println(processEngine);

    }
}
