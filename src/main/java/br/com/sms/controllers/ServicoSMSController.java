package br.com.sms.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.ws.client.ClientTransportException;

import br.com.sms.modelos.SMS;
import br.com.sms.repositorios.SMSRepositorio;

@RestController
public class ServicoSMSController {

	private final String url = "http://localhost:8888/api/v1/sms";

	@Autowired
	private SMSRepositorio smsRepositorio;

	@RequestMapping(value = "enviados", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Collection<SMS> enviados() {

		Iterable<SMS> iter = smsRepositorio.findAll();
		Collection<SMS> list = new ArrayList<SMS>();
		for (SMS item : iter) {
			list.add(item);
		}
		return list;
	}

	@RequestMapping(value = "enviados/{id}", method = RequestMethod.GET, produces = "application/json")
	public SMS enviados(@PathVariable("id") Long id) {
		return smsRepositorio.findOne(id);
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	public void removerSMS(@PathVariable Long id) {
		smsRepositorio.delete(id);
	}

	/*
	 * TODO AppWebConfiguration CORS produces = {"application/xml",
	 * "application/json"} consumes ={"application/xml", "application/json"}
	 */
	@RequestMapping(value = "enviarsms", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<SMS> enviados(@RequestBody SMS smsData) {
		SMS sms = new SMS(smsData);
		try {
			
			/**
			 * TODO
			 * Tratar a questao da data no sentido de agendamento.
			 * Olhar @Scheduled(fixedRate = 5000) por exemplo para definir periodicidade.
			 * Por exemplo 5s.
			 * Consumir de uma Lista de SMS pendentes de envio e comparar a data corrente com a data de agendamento.
			 * Por enquanto estou apenas travando o cenario de agendamento não permitindo uma data futura.
			 */
			
			if (!"".equals(sms.getDatePicker())) {
				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				Date d = df.parse(sms.getDatePicker());
				if (d.after(new Date())) {
					sms.setStatus("Date expired");
					return new ResponseEntity<SMS>(sms, HttpStatus.BAD_REQUEST);
				}
			}

			ObjectMapper mapper = new ObjectMapper();
			RestTemplate rest = new RestTemplate();
			String blob = mapper.writeValueAsString(sms.dados());
			ResponseEntity<SMS> entity = rest.postForEntity(url, blob, SMS.class);
			sms.setStatus(entity.getBody().getStatus());
			sms.setStatusCode(entity.getStatusCode());
			return new ResponseEntity<SMS>(sms, HttpStatus.CREATED);

		} catch (HttpClientErrorException e) {
			sms.setStatusCode(e.getStatusCode());
			sms.setStatus(e.getResponseBodyAsString());
			return new ResponseEntity<SMS>(sms, e.getStatusCode());

		} catch (ClientTransportException e) {
			e.printStackTrace();
			sms.setStatus("Erro de transporte");
			return new ResponseEntity<SMS>(sms, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (ResourceAccessException e) {
			sms.setStatus("Operadora Movel Desligada");
			return new ResponseEntity<SMS>(sms, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (Exception e) {
			e.printStackTrace();
			sms.setStatus("Erro Inesperado");
			return new ResponseEntity<SMS>(sms, HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			smsRepositorio.save(sms);
		}
	}
}