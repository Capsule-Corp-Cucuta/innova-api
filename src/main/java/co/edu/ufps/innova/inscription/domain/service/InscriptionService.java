package co.edu.ufps.innova.inscription.domain.service;

import java.util.List;
import java.util.Locale;
import java.time.ZoneId;
import java.util.Optional;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.FormatStyle;
import lombok.RequiredArgsConstructor;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;
import co.edu.ufps.innova.user.domain.dto.User;
import org.springframework.mail.MailSendException;
import co.edu.ufps.innova.event.domain.dto.EventState;
import co.edu.ufps.innova.user.domain.service.IUserService;
import co.edu.ufps.innova.event.domain.service.IEventService;
import co.edu.ufps.innova.email.domain.service.IEmailService;
import co.edu.ufps.innova.inscription.domain.dto.Inscription;
import co.edu.ufps.innova.inscription.domain.repository.IInscriptionRepository;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
@Service
@RequiredArgsConstructor
public class InscriptionService implements IInscriptionService {

    private final IUserService userService;
    private final IEventService eventService;
    private final IEmailService emailService;
    private final IInscriptionRepository repository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Inscription save(Inscription inscription) {
        LocalDate now = LocalDate.now();
        return eventService.findById(inscription.getEventId()).map(event -> {
            if ((now.isBefore(event.getRegistrationDeadlineDate()) || now.isEqual(event.getRegistrationDeadlineDate())) &&
                    (event.getState() == EventState.ABIERTO || event.getState() == EventState.POSPUESTO)
            ) {
                inscription.setInscriptionDate(now);
                Inscription newInscription = repository.save(inscription);
                try {
                    User client = userService.findById(inscription.getUserId()).get();
                    String eventDate = ZonedDateTime.of(event.getStartDate(), ZoneId.of("America/Bogota"))
                            .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                                    .withLocale(new Locale("es", "CO")));
                    String eventHour = event.getStartDate().format(DateTimeFormatter.ofPattern("KK:mm a", Locale.US));
                    emailService.sendScheduledEventEmail(client.getName(),
                            client.getEmail(),
                            event.getTitle(),
                            eventDate,
                            eventHour,
                            event.getEmail());
                } catch (MailSendException e) {
                    e.printStackTrace();
                }
                return newInscription;
            }
            return null;
        }).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveAll(List<Inscription> inscriptions) {
        repository.update(inscriptions);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Inscription> findById(long eventId, String userId) {
        return repository.findById(eventId, userId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(long eventId, String userId) {
        return findById(eventId, userId)
                .map(item -> {
                    repository.delete(item);
                    return true;
                })
                .orElse(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll(List<Inscription> inscriptions) {
        repository.deleteAll(inscriptions);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<List<Inscription>> findByEventId(long eventId) {
        return repository.findByEventId(eventId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<List<Inscription>> findByUserId(String userId) {
        return repository.findByUserId(userId);
    }

}
