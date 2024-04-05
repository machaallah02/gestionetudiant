package ProjetPOOAL3.gestionetudiant.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ProjetPOOAL3.gestionetudiant.dao.CoursRepository;
import ProjetPOOAL3.gestionetudiant.dao.EtudiantRepository;
import ProjetPOOAL3.gestionetudiant.dao.InscriptionRepository;
import ProjetPOOAL3.gestionetudiant.entities.Cours;
import ProjetPOOAL3.gestionetudiant.entities.Etudiant;
import ProjetPOOAL3.gestionetudiant.entities.Inscription;
import ProjetPOOAL3.gestionetudiant.entities.Inscriremap;
import ProjetPOOAL3.gestionetudiant.entities.Insert;
import ProjetPOOAL3.gestionetudiant.entities.UpdateInscri;
import lombok.Getter;

@Getter
@Controller
public class InscriptionController {
    private CoursRepository coursRepository;
    private EtudiantRepository etudiantRepository;
    private InscriptionRepository inscriptionRepository;
    public InscriptionController(CoursRepository coursRepository, EtudiantRepository etudiantRepository,
            InscriptionRepository inscriptionRepository) {
        this.coursRepository = coursRepository;
        this.etudiantRepository = etudiantRepository;
        this.inscriptionRepository = inscriptionRepository;
    }

    @GetMapping("/listInscription")
    public String getAllInscriptions(Model model) {
        List<Inscription> inscriptions = inscriptionRepository.findAll();
        List<Inscriremap> inscriremaps=new ArrayList<>();

        for(Inscription inscription:inscriptions){
            Inscriremap inscriremap=new Inscriremap();
            inscriremap.setId(inscription.getId());
            inscriremap.setNomEtudiant(inscription.getEtudiant().getNom());
            List<String> coursList=new ArrayList<>();
            for (Cours cours : inscription.getCours()) { // Récupère les cours associés à cette inscription
                coursList.add(cours.getNomC()); // Ajoute le nom du cours à la liste
            }
            inscriremap.setCoursList(coursList);
            inscriremaps.add(inscriremap);
        }
        List<Etudiant> etudiantList=etudiantRepository.findAll();
        List<Cours> coursList= coursRepository.findAll();
        model.addAttribute("listetudiant", etudiantList);
        model.addAttribute("listcours", coursList);
        model.addAttribute("listinscription", inscriremaps);
        return "inscription";
    }
    @PostMapping("/insertInscrip")
    public String Inscrire(@ModelAttribute Insert insert, RedirectAttributes redirectAttributes){
        System.out.println("Liste de cours : " + insert.getCourList());
        List<Cours> cours = new ArrayList<>();
        for (Long id : insert.getCourList()) {
            Optional<Cours> cours1 = coursRepository.findById(id);
            cours1.ifPresent(cours::add);
        }

        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(insert.getNomEtudiant());
        if (optionalEtudiant.isPresent() && !cours.isEmpty()) {
            Etudiant etudiant = optionalEtudiant.get();
            List<Inscription> inscriptions = new ArrayList<>(); // Liste pour stocker les inscriptions

            // Création des inscriptions pour chaque cours
            for (Cours coursItem : cours) {
                Inscription inscription = new Inscription(1);
                inscription.setEtudiant(etudiant);
                inscription.setCours(coursItem);
                //inscription.setDateinscription(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                inscriptions.add(inscription); // Ajout de l'inscription à la liste
            }

            // Enregistrement de toutes les inscriptions en une seule opération
            inscriptionRepository.saveAll(inscriptions);

            redirectAttributes.addFlashAttribute("save_success", "Validé avec succès");
        } else {
            redirectAttributes.addFlashAttribute("save_error", "Erreur lors de la validation");
        }

        return "redirect:/listInscription";

    }

    // @PostMapping("/insertInscrip")
    // public String Inscrire(@ModelAttribute Insert insert, RedirectAttributes redirectAttributes){
    //     System.out.println("Liste de cours : " + insert.getCourList());
    //     List<Cours> cours = new ArrayList<>();
    //     for (Long id : insert.getCourList()) {
    //         Optional<Cours> cours1 = coursRepository.findById(id);
    //         if (cours1.isPresent()) {
    //             cours.add(cours1.get());
    //         }
    //     }
    //     Optional<Etudiant> etudianto = etudiantRepository.findById(insert.getNomEtudiant());
    //     if (etudianto.isPresent() && !cours.isEmpty()) {
    //         Etudiant etudiant = etudianto.get();
    //         List<Inscription> inscriptions = new ArrayList<>(); // Liste pour stocker les inscriptions

    //         // Création des inscriptions pour chaque cours
    //         for (Cours coursItem : cours) {
    //             Inscription inscription = new Inscription();
    //             inscription.setEtudiant(etudiant);
    //             inscription.setCours(coursItem);
    //             //inscription.setDateinscription(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    //             inscriptions.add(inscription); // Ajout de l'inscription à la liste
    //         }

    //         // Enregistrement de toutes les inscriptions en une seule opération
    //         inscriptionRepository.saveAll(inscriptions);

    //         redirectAttributes.addFlashAttribute("save_success", "Validé avec succès");
    //     } else {
    //         redirectAttributes.addFlashAttribute("save_error", "Erreur lors de la validation");
    //     }

    //     return "redirect:/listIncription";
    //     }

        @PostMapping("/updateInscrip")
    public String updateInscription(@ModelAttribute UpdateInscri insert, @RequestParam Long coded, RedirectAttributes redirectAttributes){
        Optional<Inscription> inscription=inscriptionRepository.findById(coded);
        if (inscription.isPresent()){
            Optional<Etudiant> etudiant=etudiantRepository.findById(insert.getNomEtudiant());
            inscription.get().setEtudiant(etudiant.get());
            //inscription.get().setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            inscriptionRepository.save(inscription.get());
            redirectAttributes.addFlashAttribute("update_success","Modification avec succes");
        }else redirectAttributes.addFlashAttribute("update_error","Modification échoué");
        return  "redirect:/listInscription";
    }

    @GetMapping("/deleteInscrip")
    public String deleteinscription(Long id, RedirectAttributes redirectAttributes){
        try {
            inscriptionRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("delete_success","suppression effectue avec succes");

        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/listInscription";
    }

}


