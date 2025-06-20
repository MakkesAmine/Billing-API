package tn.esprit.facturation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;


import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    @NotBlank(message="nom obligatoire")
    String nom;
    @Email(message="email invalide")
    @NotBlank(message="email obligatoire")
    String email;

    @NotBlank(message=" numéro SIRET est obligatoire  ")
    @Pattern(regexp="\\d{14}" ,message=" SIRET doit contenir 14 chiffres  .")
    String siret;

    LocalDate dateCreation;
    @OneToMany(mappedBy="client", cascade=CascadeType.ALL)
    @JsonIgnore
    List<Facture> factures;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Facture> getFactures() {
        return factures;
    }

    public void setFactures(List<Facture> factures) {
        this.factures = factures;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}

