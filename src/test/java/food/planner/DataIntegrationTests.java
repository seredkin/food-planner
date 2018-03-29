package food.planner;

import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest @Slf4j
public class DataIntegrationTests {


	@Test
	public void contextLoads() {
        CloudSolrClient solrClient = new CloudSolrClient.Builder().withSolrUrl("http://localhost:8983/solr").build();
        solrClient.connect();
        solrClient.getClusterStateProvider().liveNodes().forEach(log::info);
    }

}
