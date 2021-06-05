package co.edu.ufps.innova.email.domain.dto;

import lombok.Data;

@Data
public class Email {

    private String to;
    private String subject;
    private String content;

}
