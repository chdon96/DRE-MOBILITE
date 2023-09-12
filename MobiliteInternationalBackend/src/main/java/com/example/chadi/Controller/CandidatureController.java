package com.example.chadi.Controller;

import com.example.chadi.Email.EmailService;
import com.example.chadi.Entity.Candidature;
import com.example.chadi.Service.CandidatureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Candidature")
@Slf4j
@CrossOrigin("*")
public class CandidatureController {
    @Autowired
    CandidatureService candidatureService;
    @Autowired
    EmailService emailService;

    @PostMapping("/AddCandidature/{idOffre}")

    public Candidature AddandAssignCandidature(@RequestBody Candidature candidature, @PathVariable("idOffre") Integer idOffre) {
        Candidature C = candidatureService.AddAndAssignCandidatureToOffre(candidature, idOffre);
        return C;
    }
    @PostMapping("/AddCandidature/")

    public Candidature AddCandidature(@RequestBody Candidature candidature) {
        Candidature C = candidatureService.addCandidature(candidature);
        return C;
    }
    @PutMapping("/update-Candidature")
    public Candidature updateCandidature(@RequestBody Candidature c) {
        Candidature O = candidatureService.updateCandidature(c);
        return O;
    }

    @DeleteMapping("/remove-Candidature/{idCandidature}")
    public void removeCandidature(@PathVariable("idCandidature") Integer idCandidature) {

        candidatureService.removeCandidature(idCandidature);
    }

    @PutMapping("/assign-idCandidature-to-user/{idCandidature}/{IdUser}")
    public void assignCandidatureToUser(@PathVariable("idCandidature") Integer idCandidature,
                                            @PathVariable("IdUser") Integer IdUser) {
        candidatureService.assignCandidatureToUser(idCandidature, IdUser);

    }
    @GetMapping("/get-candidature/{idCandidature}")
    public Candidature getTicket(@PathVariable("idCandidature") Integer idCandidature) {

        return candidatureService.retreviveCandidature(idCandidature);
    }
    @GetMapping("retrevive-candiddatures")
    public List<Candidature> retrevive(){
        List<Candidature> l=candidatureService.retrieveAllCandidatures();
        return l;
    }
    @GetMapping("Show-All")
    public List<Candidature>showAll(){
        return candidatureService.ListCandidatures();
    }

    @GetMapping("Show-Top/{idOffre}")
    public List<Candidature>TopCandidature(@PathVariable("idOffre") Integer idOffre){
        return candidatureService.TopCandidature(idOffre);
    }
    @GetMapping("Show-TopEmails/{idOffre}")
    public List<String>TopCandidatureemails(@PathVariable("idOffre") Integer idOffre){
        return candidatureService.getEmailsFromCandidaturesLimitedByAvailablePlaces(idOffre);
    }
    @PostMapping("/send/{idOffre}")
    public void sendEmailsToCandidatures(@PathVariable Integer idOffre) {
        try {
            candidatureService.sendEmailsCondida(idOffre);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


