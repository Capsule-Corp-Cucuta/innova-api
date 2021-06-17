package co.edu.ufps.innova.email.domain.dto;

import lombok.Data;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
@Data
public class Email {

    private String to;
    private String subject;
    private String content;

}
