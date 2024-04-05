package ProjetPOOAL3.gestionetudiant.entities;

import java.util.List;

import lombok.Data;

@Data
public class Inscriremap {
    private Long id;
    private String nomEtudiant;
    private String nomCours;
    private List<String> coursList;
    public void setCoursList(List<String> coursList) {
        // Implémentation de la méthode
        this.coursList=coursList;
    }
    public List<String> getCoursList() {
        return coursList;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setNomEtudiant(String nomEtudiant) {
        this.nomEtudiant = nomEtudiant;
    }

}
