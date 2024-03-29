package ProjetPOOAL3.gestionetudiant.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ProjetPOOAL3.gestionetudiant.entities.Inscription;

public interface InscriptionRepository extends JpaRepository <Inscription, Long> {

}
