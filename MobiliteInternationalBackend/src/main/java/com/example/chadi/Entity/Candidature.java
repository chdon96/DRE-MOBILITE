package com.example.chadi.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Candidature implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer idCandidature;
    private String NomComplet;
    private String Identifiant;
    private String email;
    @Enumerated(EnumType.STRING)
    private  Genre genre;
    private LocalDate DateNaissance;
    private String Classe;
    private double Moy3;
    private double Moy2;
    private double Moy1;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private double score;
    @Enumerated(EnumType.STRING)
    private  Niveau Francais;
    @Enumerated(EnumType.STRING)
    private  Niveau Anglais;
    @Enumerated(EnumType.STRING)
    private  Option specialite;
    @ManyToOne
    @JsonIgnore
    User userCandidature;
    @ManyToOne
    @JsonIgnore
    Offre offre;



}
