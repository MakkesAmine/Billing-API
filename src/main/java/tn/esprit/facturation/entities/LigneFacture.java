package tn.esprit.facturation.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class LigneFacture {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    @NotBlank(message="description obligatoire")
    String description;
    int qte;
    double prixUnitHT;
    @Enumerated(EnumType.STRING)
            @NonNull
    TauxTVA tauxTVA;

    @ManyToOne
    Facture facture;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public double getPrixUnitHT() {
        return prixUnitHT;
    }

    public void setPrixUnitHT(double prixUnitHT) {
        this.prixUnitHT = prixUnitHT;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public @NonNull TauxTVA getTauxTVA() {
        return tauxTVA;
    }

    public void setTauxTVA(@NonNull TauxTVA tauxTVA) {
        this.tauxTVA = tauxTVA;
    }
}
