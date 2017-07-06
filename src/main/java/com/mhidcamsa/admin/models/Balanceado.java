package com.mhidcamsa.admin.models;

import java.math.BigDecimal;
import java.util.UUID;

public class Balanceado extends Producto{

    private int perc_prot;

    public Balanceado(UUID id, String marca, String tipo, BigDecimal precio, double volumen, boolean liquido, int perc_prot) {
        super(id, marca, tipo, precio, volumen, liquido);
        this.perc_prot = perc_prot;
    }

    public int getPerc_prot() {
        return perc_prot;
    }

    public void setPerc_prot(int perc_prot) {
        this.perc_prot = perc_prot;
    }

    @Override
    public String toString() {
        return "Balanceado{" +
                "Porcentaje de proteina: " + perc_prot +
                '}';
    }
}