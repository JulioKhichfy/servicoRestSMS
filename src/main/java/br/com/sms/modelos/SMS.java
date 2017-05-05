package br.com.sms.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class SMS implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String celularOrigem;
    private String mensagem;
    private String celularDestino;
    private String datePicker;
	private String status;
	private String statusCode;

    private ObjectMapper mapper;
    
    public SMS() {
        this.mapper = new ObjectMapper();
    }

    public SMS(String celularOrigem, String mensagem, String celularDestino,
            String datePicker, String status, String statusCode) {
        this.celularOrigem = celularOrigem;
        this.mensagem = mensagem;
        this.celularDestino = celularDestino;
        this.datePicker = datePicker;
        this.status = status;
        this.statusCode = statusCode;
    }

    /* Copy constructor */
    public SMS(SMS sms) {
        this.celularOrigem = sms.celularOrigem;
        this.mensagem = sms.mensagem;
        this.celularDestino = sms.celularDestino;
        this.datePicker = sms.datePicker;
        this.status = sms.status;
        this.statusCode = sms.statusCode;
    }

    public String toString() {
        try {
            return this.mapper.writeValueAsString(this);
        } catch(JsonProcessingException e)
        {
            e.printStackTrace();
            return "{}";
        }
    }
 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCelularOrigem() {
        return celularOrigem;
    }

    public void setCelularOrigem(String celularOrigem) {
        this.celularOrigem = celularOrigem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getCelularDestino() {
        return celularDestino;
    }

    public void setCelularDestino(String celularDestino) {
        this.celularDestino = celularDestino;
    }

    public String getDatePicker() {
        return datePicker;
    }

    public void setDatePicker(String datePicker) {
        this.datePicker = datePicker;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = String.valueOf(statusCode.value());
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String string) {
        this.statusCode = string;
    }

    public List<String> dados()
    {
        List<String> l = new ArrayList<String>();
        l.add(this.celularOrigem);
        l.add(this.mensagem);
        l.add(this.celularDestino);
        return l;
    }
}
