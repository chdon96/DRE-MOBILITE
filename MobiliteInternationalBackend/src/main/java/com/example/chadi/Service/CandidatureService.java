package com.example.chadi.Service;

import com.example.chadi.Email.EmailService;
import com.example.chadi.Entity.Candidature;
import com.example.chadi.Entity.Offre;
import com.example.chadi.Entity.User;
import com.example.chadi.Interface.ICandidatureService;
import com.example.chadi.Repository.CandidatureRepository;
import com.example.chadi.Repository.OffreRepository;
import com.example.chadi.Repository.UserRep;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class CandidatureService implements ICandidatureService {
    @Autowired
    CandidatureRepository candidatureRepository;
    @Autowired

    UserRep userRepository;
    @Autowired

    OffreRepository offreRepository;
    private final static Logger LOGGER = LoggerFactory
            .getLogger(EmailService.class);

    private final JavaMailSender mailSender;

    @Override
    public Candidature AddAndAssignCandidatureToOffre(Candidature f, Integer idOffre) {
        Optional<Offre> O = offreRepository.findById(idOffre);
        if (O.isPresent()) {
            Offre U = O.get();

            f.setOffre(U);
            double s = calculateScore(f.getMoy1(), f.getMoy2(), f.getMoy3());
            f.setScore(s);

            return candidatureRepository.save(f);
        }
        else {
            log.info("not found");
            return null;
        }
    }

    @Override
    public Candidature updateCandidature(Candidature f) {
        return candidatureRepository.save(f);    }


    @Override
    public List<Candidature> retrieveAllCandidatures() {
        return candidatureRepository.findAll();
    }

    @Override
    public List<Candidature> ListCandidatures() {
        return candidatureRepository.findAll();
    }

    public List<Candidature> TopCandidature(Integer idOffre) {
        return candidatureRepository.getCandidaturesLimitedByAvailablePlaces(idOffre);
    }

    public  List<String> getEmailsFromCandidaturesLimitedByAvailablePlaces(Integer idOffre) {
        List<Candidature> candidatures = candidatureRepository.getCandidaturesLimitedByAvailablePlaces(idOffre);

        // Use Java Stream API to extract the emails from the list of candidatures
        return candidatures.stream()
                .map(Candidature::getEmail)
                .collect(Collectors.toList());
    }
    public void sendEmailsTopCandidatures(String to) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText("Congrats");
            helper.setTo(to);
            helper.setSubject("Accepté en Mobilité Internationale");
            helper.setFrom("hrizi.mohamedali@esprit.tn");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }

    }
    public  void sendEmailsCondida(Integer idOffre) {
        List<String> emails =getEmailsFromCandidaturesLimitedByAvailablePlaces(idOffre);
        try {
            for (String email : emails) {
                sendEmailsTopCandidatures(email);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Candidature addCandidature(Candidature f) {
        double s = calculateScore(f.getMoy1(), f.getMoy2(), f.getMoy3());
        f.setScore(s)   ;
        return candidatureRepository.save(f);
    }


    @Override
    public void removeCandidature(Integer IdCandidature) {
         candidatureRepository.deleteById(IdCandidature);

    }

    @Override
    public void assignCandidatureToUser(Integer IdCandidature, Integer IdUser) {
        Candidature T=candidatureRepository.findById(IdCandidature).orElse(null);
        User U=userRepository.findById(IdUser).orElse(null);
        T.setUserCandidature(U);
        candidatureRepository.save(T);
    }

    @Override
    public double calculateScore(double M1, double M2,double M3) {
        double S= M1+M2+M3;
        return S;
    }

    @Override
    public Candidature retreviveCandidature(Integer IdCandidature) {
        return candidatureRepository.findById(IdCandidature).get();
    }

    @Override
    public Candidature retreviveCandidature2(Integer IdCandidature, HttpServletResponse response) {
        return candidatureRepository.findById(IdCandidature).get();
    }
}
