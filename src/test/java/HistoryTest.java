import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.junit.Test;

import java.util.List;

@Slf4j
public class HistoryTest {
    @Test
    public void queryHistory(){
        log.info("查询流程执行历史");
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        HistoryService historyService = processEngine.getHistoryService();
        HistoricTaskInstanceQuery historicTaskInstanceQuery = historyService.createHistoricTaskInstanceQuery();
        List<HistoricTaskInstance> list = historicTaskInstanceQuery.list();
        for (HistoricTaskInstance historicTaskInstance : list) {
            System.err.println(historicTaskInstance);
        }
        log.info("查询流程执行历史结束");
    }
}
