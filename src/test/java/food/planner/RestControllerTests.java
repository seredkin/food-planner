package food.planner;

import food.planner.rest.RetailOffer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestControllerTests {

	private WebClient webClient;

	@LocalServerPort
	private int port;

	@Before
	public void setup() {
		this.webClient = WebClient.create("http://localhost:" + this.port);
	}

	@Autowired
	ApplicationContext ctx;

	@Test
	public void contextLoads() {
		assertNotNull(ctx);
	}

	@Test
	public void getCurrentOffers() {
		List<RetailOffer> customer = this.webClient.get().uri("/offers/")

				.accept(MediaType.APPLICATION_JSON)
				.exchange().block().toEntityList(RetailOffer.class).block().getBody();

		assertThat(customer.size(), is(5));
	}

}
