package com.example.chadi.Service;

import com.example.chadi.Entity.Offre;
import com.example.chadi.Interface.IOffreService;
import com.example.chadi.Repository.OffreRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class OffreService implements IOffreService {
    OffreRepository offreRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Offre addOffre(Offre o) {
        return offreRepository.save(o);
    }

    @Override
    public void removeOffre(Integer id) {
        offreRepository.deleteById(id);
    }

    @Override
    public List<Offre> showOffre() {
        return offreRepository.findAll();
    }

    @Override
    public Offre updateOffre(Offre f) {
        return offreRepository.save(f);
    }

    /*public Offre addOffreandimage (String titre, String description, String lieu, LocalDate deadline, Integer nombrePlaces, String link, MultipartFile image) throws IOException {
        Offre offre = objectMapper.readValue(titre, Offre.class);

        offre.setTitre(titre);
        offre.setDescription(description);
        offre.setLieu(lieu);
        offre.setDeadline(deadline);
        offre.setNombreplaces(nombrePlaces);
        offre.setLink(link);
        String filename = StringUtils.cleanPath(image.getOriginalFilename());
        if(filename.contains("..")){
            System.out.println("!!! Not a valid File");
        }
        offre.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
        return offreRepository.save(offre);
    }*/
   /* public Offre addOffreWithImage(String titre, String description, String lieu, LocalDate deadline, Integer nombrePlaces, String link, MultipartFile imageFile) {
        try {
            Offre offre = new Offre();
            offre.setTitre(titre);
            offre.setDescription(description);
            offre.setLieu(lieu);
            offre.setDeadline(deadline);
            offre.setNombreplaces(nombrePlaces);
            offre.setLink(link);

            String filename = StringUtils.cleanPath(Objects.requireNonNull(imageFile.getOriginalFilename()));
            if (filename.contains("..")) {
                throw new IllegalArgumentException("Invalid file path detected.");
            }

            byte[] imageBytes = imageFile.getBytes();
            offre.setImage(Base64.getEncoder().encodeToString(imageBytes));

            // Save the offre object with the image to the database
            return offreRepository.save(offre);
        } catch (IOException e) {
            // Handle the exception appropriately
            e.printStackTrace();
        }

        return null; // Return null if an exception occurs
    }
*/
   /* public void AddOffreAndImage(Offre offre, MultipartFile imageFile) {
        try {
            // Convert the image file to a byte array
            byte[] imageData = imageFile.getBytes();

            // Encode the byte array as a Base64 string
            String encodedImage = Base64.getEncoder().encodeToString(imageData);

            // Set the encoded image string in the Offre object
            offre.setImage(encodedImage);

            // Save the Offre object in the repository
            offreRepository.save(offre);
        } catch (IOException e) {
            // Handle any exception that occurred during file processing
            throw new RuntimeException("Failed to process image file.", e);
        }
    }*/
    /*
    public Offre AddOffreImage(Offre offre, MultipartFile imageFile) {
        String imageData = saveImage(imageFile);
        offre.setImage(imageData);
        return offreRepository.save(offre);
    }

    private String saveImage(MultipartFile imageFile) {
        try {
            byte[] imageData = imageFile.getBytes();
            return Base64.getEncoder().encodeToString(imageData);
        } catch (IOException e) {
            throw new RuntimeException("Failed to process image file.", e);
        }
    }
*/

   public Offre  AddOffrewithImage(MultipartFile file,String titre, String description, String lieu, LocalDate deadline, Integer nombrePlaces, String link)
    {
        Offre p = new Offre();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        p.setTitre(titre);
        p.setDescription(description);
        p.setLieu(lieu);
        p.setDeadline(deadline);
        p.setNombreplaces(nombrePlaces);
        p.setLink(link);

       return offreRepository.save(p);
    }
   /*public Offre AddOffrewithImage(MultipartFile file, String titre, String description, String lieu, LocalDate deadline, Integer nombrePlaces, String link) {
        Offre p = new Offre();
        p.setTitre(titre);
        p.setDescription(description);
        p.setLieu(lieu);
        p.setDeadline(deadline);
        p.setNombreplaces(nombrePlaces);
        p.setLink(link);

        // Get the original filename
        String originalFilename = file.getOriginalFilename();

        // Create the image path based on the original filename
        String imagePath = "src/assets/img" + originalFilename;

        // Set the image path in the entity
        p.setImage(imagePath);

        return offreRepository.save(p);
    }
*/
    //   SAVE NAME FILE IN DATABASE
 /*  public Offre AddOffrewithImage(MultipartFile file, String titre, String description, String lieu, LocalDate deadline, Integer nombrePlaces, String link) {
        Offre p = new Offre();
        p.setTitre(titre);
        p.setDescription(description);
        p.setLieu(lieu);
        p.setDeadline(deadline);
        p.setNombreplaces(nombrePlaces);
        p.setLink(link);

        // Get the original path of the file
        String originalPath = file.getOriginalFilename();

        // Set the image path in the entity
        p.setImage(originalPath);

        return offreRepository.save(p);
    }
*/
   public Offre getOffrebyId(Integer id){
       Offre f = offreRepository.findById(id).get();
       return f;
   }
}