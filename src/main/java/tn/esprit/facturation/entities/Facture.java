package tn.esprit.facturation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class Facture {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    LocalDate date;
    @ManyToOne
    Client client;
    @Valid
    @OneToMany(mappedBy = "facture", cascade = CascadeType.ALL)
    @JsonManagedReference
    List<LigneFacture> lignes;
    Double totalHT;
    Double totalTVA;
    Double totalTTC;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalTVA() {
        return totalTVA;
    }

    public void setTotalTVA(Double totalTVA) {
        this.totalTVA = totalTVA;
    }

    public Double getTotalHT() {
        return totalHT;
    }

    public void setTotalHT(Double totalHT) {
        this.totalHT = totalHT;
    }

    public Double getTotalTTC() {
        return totalTTC;
    }

    public void setTotalTTC(Double totalTTC) {
        this.totalTTC = totalTTC;
    }

    public List<LigneFacture> getLignes() {
        return lignes;
    }

    public void setLignes(List<LigneFacture> lignes) {
        this.lignes = lignes;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
