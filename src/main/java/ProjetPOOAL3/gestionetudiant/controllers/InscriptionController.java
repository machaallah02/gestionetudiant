package ProjetPOOAL3.gestionetudiant.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ProjetPOOAL3.gestionetudiant.dao.CoursRepository;
import ProjetPOOAL3.gestionetudiant.dao.EtudiantRepository;
import ProjetPOOAL3.gestionetudiant.dao.InscriptionRepository;
import ProjetPOOAL3.gestionetudiant.entities.Inscription;

@Controller
public class InscriptionController {
    private InscriptionRepository inscriptionRepository;
    private EtudiantRepository etudiantRepository;
    private CoursRepository coursRepository;

    public InscriptionController(InscriptionRepository inscriptionRepository){
        this.coursRepository=coursRepository;
        this.etudiantRepository=etudiantRepository;
        this.inscriptionRepository=inscriptionRepository;
    }

    @GetMapping("/inscriptionList")
    public String getAllInscription(Model model){
        List<Inscription> inscriptions=inscriptionRepository.findAll();
        return "inscription";
    }

}
