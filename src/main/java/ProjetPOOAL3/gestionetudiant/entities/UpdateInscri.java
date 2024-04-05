package ProjetPOOAL3.gestionetudiant.entities;

import lombok.Data;

@Data
public class UpdateInscri {
    private long nomEtudiant;

    public Long getNomEtudiant(){
        return nomEtudiant;
    }

}
