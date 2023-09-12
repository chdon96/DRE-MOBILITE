package com.example.chadi.Repository;

import com.example.chadi.Entity.Candidature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, Integer> {
    @Query("SELECT c FROM Candidature c WHERE c.offre.idOffre = :idOffre ORDER BY c.score DESC")
    List<Candidature> findCandidaturesWithAvailablePlacesLimited(@Param("idOffre") Integer idOffre);

    default List<Candidature> getCandidaturesLimitedByAvailablePlaces(Integer idOffre) {
        List<Candidature> candidatures = findCandidaturesWithAvailablePlacesLimited(idOffre);

        if (candidatures.isEmpty()) {
            return Collections.emptyList(); // No candidatures found for the given offer id, return an empty list
        }

        int availablePlaces = candidatures.get(0).getOffre().getNombreplaces();

        if (availablePlaces <= 0) {
            return Collections.emptyList(); // No available places, return an empty list
        }

        return candidatures.stream().limit(availablePlaces).collect(Collectors.toList());
    }


}

