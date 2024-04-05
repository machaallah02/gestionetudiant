package ProjetPOOAL3.gestionetudiant.entities;

import java.util.List;

import lombok.Data;
import lombok.Getter;

@Data
public class Insert {
    private long nomEtudiant;
    private long nomCours;
    private List<Long> courList;

    public List<Long> getCourList() {
        return courList;
    }

    public long getNomEtudiant() {
        return nomEtudiant;
    }

    

}
