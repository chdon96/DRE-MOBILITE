package com.example.chadi.Entity;

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
public class Offre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idOffre;
    private String titre;
    private String description;
    private String lieu;
    private LocalDate deadline;
    private Integer nombreplaces;
    private String link;
    @Lob
    private String image;
}