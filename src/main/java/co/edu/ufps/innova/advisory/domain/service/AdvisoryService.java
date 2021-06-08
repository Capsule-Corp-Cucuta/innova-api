package co.edu.ufps.innova.advisory.domain.service;

import java.util.List;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Optional;
import java.time.ZonedDateTime;
import java.time.LocalDateTime;
import java.time.format.FormatStyle;
import lombok.RequiredArgsConstructor;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;
import co.edu.ufps.innova.user.domain.dto.User;
import co.edu.ufps.innova.email.domain.dto.Email;
import org.springframework.mail.MailSendException;
import co.edu.ufps.innova.advisory.domain.dto.Advisory;
import co.edu.ufps.innova.user.domain.service.UserService;
import co.edu.ufps.innova.advisory.domain.dto.AdvisoryArea;
import co.edu.ufps.innova.advisory.domain.dto.AdvisoryType;
import co.edu.ufps.innova.advisory.domain.dto.AdvisoryState;
import co.edu.ufps.innova.email.domain.service.IEmailService;
import co.edu.ufps.innova.advisory.domain.repository.IAdvisoryRepository;

@Service
@RequiredArgsConstructor
public class AdvisoryService {

    private final UserService userService;
    private final IEmailService emailService;
    private final IAdvisoryRepository repository;

    public Advisory save(Advisory advisory) {
        Advisory myAdvisory = repository.save(advisory);

        try {
            User client = userService.findById(myAdvisory.getClientId()).get();
            User consultant = userService.findById(myAdvisory.getConsultantId()).get();
            String advisoryDate = ZonedDateTime.of(myAdvisory.getDate(), ZoneId.of("America/Bogota"))
                    .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                            .withLocale(new Locale("es", "CO")));
            String advisoryHour = advisory.getDate().format(DateTimeFormatter.ofPattern("KK:mm a", Locale.US));
            Email email = new Email();
            email.setTo(client.getEmail());
            email.setSubject("Innova - Asesoría agendada");
            email.setContent(String.format("Hola %s, fuiste agendado(a) para una asesoría con %s %s para el día %s " +
                            "a la(s) %s. Revisala desde la aplicación; en caso de cualquier inquietud puedes " +
                            "contactarte con tu asesor en el siguiente correo:  %s.",
                    client.getName(),
                    consultant.getName(),
                    consultant.getLastname(),
                    advisoryDate,
                    advisoryHour,
                    consultant.getEmail()));
            emailService.sendEmail(email);
        } catch (MailSendException e) {
            e.printStackTrace();
        }

        return myAdvisory;
    }

    public boolean update(long id, Advisory advisory) {
        return findById(id).map(item -> {
            advisory.setId(item.getId());
            repository.save(advisory);
            try {
                User client = userService.findById(advisory.getClientId()).get();
                User consultant = userService.findById(advisory.getConsultantId()).get();
                String advisoryDate = ZonedDateTime.of(item.getDate(), ZoneId.of("America/Bogota"))
                        .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                                .withLocale(new Locale("es", "CO")));
                Email email = new Email();
                email.setTo(client.getEmail());
                email.setSubject("Innova - Asesoría modificada");
                email.setContent(String.format("La asesoría en la que estás agendado(a) para el día %s fue modificada, " +
                                "puedes validarla en la aplicación y en caso de cualquier inquietud puedes contactarte " +
                                "con tu asesor en el siguiente correo: %s.",
                        advisoryDate,
                        consultant.getEmail()));
                emailService.sendEmail(email);
            } catch (MailSendException e) {
                e.printStackTrace();
            }
            return true;
        }).orElse(false);
    }

    public List<Advisory> findAll() {
        return repository.findAll();
    }

    public Optional<Advisory> findById(long id) {
        return repository.findById(id);
    }

    public Optional<List<Advisory>> findByConsultant(String consultantId) {
        return repository.findByConsultant(consultantId);
    }

    public Optional<List<Advisory>> findByClient(String clientId) {
        return repository.findByClient(clientId);
    }

    public Optional<List<Advisory>> findByType(AdvisoryType type) {
        return repository.findByType(type);
    }

    public Optional<List<Advisory>> findByArea(AdvisoryArea area) {
        return repository.findByArea(area);
    }

    public Optional<List<Advisory>> findByState(AdvisoryState state) {
        return repository.findByState(state);
    }

    public Optional<List<Advisory>> findBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findBetweenDates(startDate, endDate);
    }

    public Optional<List<Advisory>> findByConsultantAndClient(String consultantId, String clientId) {
        return repository.findByConsultantAndClient(consultantId, clientId);
    }

    public Optional<List<Advisory>> findByConsultantAndType(String consultantId, AdvisoryType type) {
        return repository.findByConsultantAndType(consultantId, type);
    }

    public Optional<List<Advisory>> findByConsultantAndArea(String consultantId, AdvisoryArea area) {
        return repository.findByConsultantAndArea(consultantId, area);
    }

    public Optional<List<Advisory>> findByConsultantAndState(String consultantId, AdvisoryState state) {
        return repository.findByConsultantAndState(consultantId, state);
    }

    public Optional<List<Advisory>> findByConsultantAndBetweenDates(String consultantId, LocalDateTime startDate,
                                                                    LocalDateTime endDate) {
        return repository.findByConsultantAndBetweenDates(consultantId, startDate, endDate);
    }

    public Optional<List<Advisory>> findByConsultantAndClientBetweenDates(String consultantId, String clientId,
                                                                          LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findByConsultantAndClientBetweenDates(consultantId, clientId, startDate, endDate);
    }

    public long count() {
        return repository.count();
    }

    public long countByConsultant(String consultantId) {
        return repository.countByConsultant(consultantId);
    }

    public long countByConsultantBetweenDates(String consultantId, LocalDateTime startDate, LocalDateTime endDate) {
        return repository.countByConsultantBetweenDates(consultantId, startDate, endDate);
    }

    public long countHoursByConsultantWithPreparation(String consultantId) {
        return findByConsultant(consultantId)
                .map(advisories -> advisories.stream()
                        .mapToLong(advisory -> advisory.getDurationInHours()
                                + advisory.getPreparationTypeInHours()).sum()
                ).orElse(0L);
    }

    public long countHoursByConsultantWithoutPreparation(String consultantId) {
        return findByConsultant(consultantId)
                .map(advisories -> advisories.stream()
                        .mapToLong(Advisory::getDurationInHours).sum()
                ).orElse(0L);
    }

    public long countHoursByConsultantWithoutPreparationBetweenDates(String consultantId, LocalDateTime startDate,
                                                                     LocalDateTime endDate) {
        return findByConsultantAndBetweenDates(consultantId, startDate, endDate)
                .map(advisories -> advisories.stream()
                        .mapToLong(Advisory::getDurationInHours).sum()
                ).orElse(0L);
    }

    public boolean delete(long id) {
        return findById(id).map(advisory -> {
            repository.delete(advisory);
            return true;
        }).orElse(false);
    }

}
