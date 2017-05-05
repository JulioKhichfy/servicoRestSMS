package br.com.sms.repositorios;

import org.springframework.data.repository.CrudRepository;

import br.com.sms.modelos.SMS;

public interface SMSRepositorio extends CrudRepository<SMS, Long>{

}
