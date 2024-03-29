package ProjetPOOAL3.gestionetudiant.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ProjetPOOAL3.gestionetudiant.dao.CoursRepository;
import ProjetPOOAL3.gestionetudiant.entities.Cours;

@Controller
public class CoursController {
    private CoursRepository coursRepository;

    public CoursController(CoursRepository coursRepository){
        this.coursRepository=coursRepository;
    }

    //ajout d'un cours
    @GetMapping("/coursList")
    public String getAllCours(Model model){
        List<Cours> coursList=coursRepository.findAll();
        model.addAttribute("listCours", coursList);
        return "cours";
    }

    @GetMapping("/insertCours")
    public String insertCours(String nomC, String description, RedirectAttributes redirectAttributes){
        Cours cours= new Cours();
        cours.setNomC(nomC);
        cours.setDescription(description);
        coursRepository.save(cours);
        redirectAttributes.addFlashAttribute("save_success"," Saisie avec succes");
        return "redirect:/coursList";
    }

    //edition du cours
    @GetMapping("/updateCours")
    public String updateCours(String nomC, String description, Long id, RedirectAttributes redirectAttributes){
        Optional<Cours> cours=coursRepository.findById(id);
        if (cours.isPresent()) {
            cours.get().setNomC(nomC);
            cours.get().setDescription(description);
            coursRepository.save(cours.get());
            redirectAttributes.addFlashAttribute("success_update", "Mise à jour effectuée avec succes");
            }else{
                redirectAttributes.addFlashAttribute("update_error", "Echec lors de la mise à jour du cours");
        }
        return "redirect:/coursList";
    }

        //suppression du cours
        @GetMapping("/deleteCours")
        public String supprimer(Long id, RedirectAttributes redirectAttributes){
            try {
                coursRepository.deleteById(id);
                redirectAttributes.addFlashAttribute("delete_success", "suppression du cours effectue avec succes");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "redirect:/coursList";
        }

}
