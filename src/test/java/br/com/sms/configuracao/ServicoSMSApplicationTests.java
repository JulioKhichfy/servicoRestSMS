package br.com.sms.configuracao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sms.modelos.SMS;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ServicoSMSApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int localServerPort;

	private final String url = "http://localhost:8080/enviarsms";
	
	static ObjectMapper mapper = new ObjectMapper();

	@Test
	public void contextLoads() {
	}

	
	@Test
	public void test_sucesso() throws RestClientException, JsonProcessingException {

		String src = "21896963704";
		String msg = "teste";
		String dst = "21896963705";
		String date = "";
		SMS s = new SMS(src,msg,dst,date,null,null);
		ResponseEntity<SMS> response = this.restTemplate.postForEntity(url, s, SMS.class);
		assertThat(response.getBody().getStatus().contains("SMS sent"));
		assertThat(response.getStatusCodeValue()==201);
	}
	
	@Test
	public void test_celular_nao_encontrado() throws RestClientException, JsonProcessingException {

		String src = "21996963704";
		String msg = "teste";
		String dst = "21996963705";
		String date = "";
		SMS s = new SMS(src,msg,dst,date,null,null);
		
		ResponseEntity<SMS> response = this.restTemplate.postForEntity(url, s, SMS.class);
		assertThat(response.getBody().getStatus().contains("Mobile user not found"));
		assertThat(response.getStatusCodeValue()==412);
	}
	
	
	@Test
	public void test_celular_msg_vazia() throws RestClientException, JsonProcessingException {

		String src = "21896963704";
		String msg = "";
		String dst = "21896963705";
		String date = "";
		SMS s = new SMS(src,msg,dst,date,null,null);
	
		ResponseEntity<SMS> response = this.restTemplate.postForEntity(url, s, SMS.class);
		assertThat(response.getBody().getStatus().contains("Message is empty"));
		assertThat(response.getStatusCodeValue()==412);
	}
	
	@Test
	public void test_celular_src_errado() throws RestClientException, JsonProcessingException {

		String src = "218969637";
		String msg = "teste";
		String dst = "21896963704";
		String date = "";
		SMS s = new SMS(src,msg,dst,date,null,null);
		
		ResponseEntity<SMS> response = this.restTemplate.postForEntity(url, s, SMS.class);
		assertThat(response.getBody().getStatus().contains("Source phone is invalid"));
		assertThat(response.getStatusCodeValue()==412);
	}
	
	@Test
	public void test_celular_dst_errado() throws RestClientException, JsonProcessingException {

		String src = "21896963704";
		String msg = "teste";
		String dst = "2189696370";
		String date = "";
		SMS s = new SMS(src,msg,dst,date,null,null);
	
		ResponseEntity<SMS> response = this.restTemplate.postForEntity(url, s, SMS.class);
		assertThat(response.getBody().getStatus().contains("Destination phone is invalid"));
		assertThat(response.getStatusCodeValue()==412);
	}
}
