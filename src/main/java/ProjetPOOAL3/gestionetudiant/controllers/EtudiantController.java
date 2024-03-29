package ProjetPOOAL3.gestionetudiant.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ProjetPOOAL3.gestionetudiant.dao.EtudiantRepository;
import ProjetPOOAL3.gestionetudiant.entities.Etudiant;

@Controller
public class EtudiantController {
    private EtudiantRepository etudiantRepository;

    public EtudiantController(EtudiantRepository etudiantRepository){
        this.etudiantRepository=etudiantRepository;
    }

    //ajout d'un etudiant
    @GetMapping("/etudiantList")
    public String getAllEtudiant(org.springframework.ui.Model model){
        List<Etudiant> etudiantList=etudiantRepository.findAll();
        model.addAttribute("listEtudiant", etudiantList);
        return "etudiant";
    }

    @GetMapping("/insertEtudiant")
    public String insertEtudiant(String nom, String prenom, RedirectAttributes redirectAttributes) {
        Etudiant etudiant =new Etudiant();
        etudiant.setNom(nom);
        etudiant.setPrenom(prenom);
        etudiantRepository.save(etudiant);
        redirectAttributes.addFlashAttribute("save_success", "Saisie validée avec succes");
        return "redirect:/etudiantList";
    }

    //edition de l'etudiant
    @GetMapping("/updateEtudiant")
    public String updateEtudiant(String nom, String prenom, Long id, RedirectAttributes redirectAttributes){
        Optional<Etudiant> etudiant=etudiantRepository.findById(id);
        if (etudiant.isPresent()) {
            etudiant.get().setNom(nom);
            etudiant.get().setPrenom(prenom);
            etudiantRepository.save(etudiant.get());
            redirectAttributes.addFlashAttribute("success_update", "Mise à jour effectuée avec succes");
            }else{
                redirectAttributes.addFlashAttribute("update_error", "Echec lors de la mise à jour de l'etudiant");
        }
        return "redirect:/etudiantList";
    }

    //suppression de l'etudiant
    @GetMapping("/deleteEtudiant")
    public String supprimer(Long id, RedirectAttributes redirectAttributes){
        try {
            etudiantRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("delete_success", "suppression de l'etudiant effectue avec succes");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/etudiantList";
    }
}
