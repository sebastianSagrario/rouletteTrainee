package com.example.demo.Enums;

public enum ValoresFicha {
    CINCUENTA(50.0), DOSCIENTOS(200.0), QUINIENTOS(500.0), MIL(1000.0), DOSMIL(2000.0);
    private Double valorNominal;

    private ValoresFicha(Double valorNominal) {
        this.valorNominal = valorNominal;
    }

    public Double getValorNominal() {
        return valorNominal;
    }

}
