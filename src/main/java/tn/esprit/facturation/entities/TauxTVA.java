package tn.esprit.facturation.entities;

public enum TauxTVA {
    ZERO(0.0),
    CINQ_CINQ(5.5),
    DIX(10.0),
    VINGT(20.0);

    private double value;

    TauxTVA(double value) {
        this.value = value;
    }
    public double getValue() {
        return value;
    }
}

