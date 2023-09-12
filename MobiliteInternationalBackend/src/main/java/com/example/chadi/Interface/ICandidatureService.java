package com.example.chadi.Interface;

import com.example.chadi.Entity.Candidature;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ICandidatureService {
    public Candidature addCandidature(Candidature f);

    public Candidature AddAndAssignCandidatureToOffre(Candidature f, Integer idOffre);

    public Candidature updateCandidature(Candidature f);

    public List<Candidature> retrieveAllCandidatures();

    public List<Candidature> ListCandidatures();


    public void removeCandidature(Integer IdCandidature);

    public void assignCandidatureToUser(Integer IdCandidature, Integer IdUser);



    public double calculateScore(double M1, double M2,double M3) ;

    public Candidature retreviveCandidature(Integer IdCandidature);

    public Candidature retreviveCandidature2(Integer IdCandidature, HttpServletResponse response);
}
