package com.example.chadi.Controller;

import com.example.chadi.Entity.Offre;
import com.example.chadi.Repository.OffreRepository;
import com.example.chadi.Service.OffreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/Offre")
@CrossOrigin("*")
@Slf4j
public class OffreController {
    @Autowired
    OffreService offreService;
    @Autowired
    OffreRepository offreRepository;
    @PostMapping("/AddOffre")
    public Offre addOffre(@RequestBody Offre O) {
        Offre offre = offreService.addOffre(O);
        log.info(offre.toString());
        return offre;
    }
    @GetMapping("/retrieve-all-Offre")
    public List<Offre> getOffres() {
        List<Offre> LO = offreService.showOffre();
        return LO;
    }
    @DeleteMapping( "/remove-Offre/{id}")
    public void removeOffre(@PathVariable("id") Integer id) {

        offreService.removeOffre(id);
    }
    @PutMapping("/update-Offre")
    public Offre updateOffre(@RequestBody Offre c) {
        Offre offre = offreService.updateOffre(c);
        return offre;
    }


    /*public ResponseEntity<Offre> updateOffer(@PathVariable Integer id,@RequestParam MultipartFile file,
                                             @RequestParam String titre,
                                             @RequestParam String description,
                                             @RequestParam String lieu,
                                             @RequestParam("deadline") String dateString,
                                             @RequestParam Integer nombrePlaces,
                                             @RequestParam String link) {
        Optional<Offre> existingOffer = offreRepository.findById(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate deadline = LocalDate.parse(dateString, formatter);
        if (existingOffer.isPresent()) {
            Offre offerToUpdate = existingOffer.get();
            offerToUpdate.setTitre(titre);
            offerToUpdate.setDescription(description);
            offerToUpdate.setLieu(lieu);
            offerToUpdate.setDeadline(deadline);
            offerToUpdate.setNombreplaces(nombrePlaces);
            offerToUpdate.setLink(link);


            // Update more properties as needed

            // Save the updated offer to the database
            offreRepository.save(offerToUpdate);


            return ResponseEntity.ok(offerToUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
*/
    @GetMapping("/get-Offre/{id}")
    public Offre getOffre(@PathVariable("id") Integer id) {
        Offre offre = offreService.getOffrebyId(id);
        return offre;
    }

    /*@PostMapping(path = "/add-offre",consumes = {MULTIPART_FORM_DATA_VALUE})
    public Offre addPublication(@RequestParam("titre") String titre,
                                @RequestParam("description") String description,
                                @RequestParam("lieu") String lieu,
                                @RequestParam("deadline") String deadline,
                                @RequestParam("nombrePlaces") Integer nombrePlaces,
                                @RequestParam("link") String link,
                                @RequestParam("image") MultipartFile image) throws IOException {
        LocalDate deadlineDate = LocalDate.parse(deadline);

        Offre oo = offreService.addOffreandimage(titre, description, lieu, deadlineDate, nombrePlaces, link, image);
        return oo;
    }
*/

    /*  @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
      public void addOffre(
              @RequestPart Offre offre,
              @RequestPart MultipartFile imageFile) {
          offreService.AddOffreAndImage(offre, imageFile);
      }*/
    /*
  @PostMapping(value = "/addoffImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<Offre> addOffre(
          @RequestPart Offre offre,
          @RequestPart MultipartFile imageFile) {
      Offre addedOffre = offreService.AddOffreImage(offre, imageFile);
      return ResponseEntity.ok(addedOffre);
  }*/
    @PostMapping(value = "/AddF", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Offre saveOffre(@RequestParam MultipartFile file,
                           @RequestParam String titre,
                           @RequestParam String description,
                           @RequestParam String lieu,
                           @RequestParam("deadline") String dateString,
                           @RequestParam Integer nombrePlaces,
                           @RequestParam String link)
    {        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate deadline = LocalDate.parse(dateString, formatter);
        Offre offre = offreService.AddOffrewithImage(file, titre, description, lieu,deadline,nombrePlaces,link);
        return offre;

    }

}
