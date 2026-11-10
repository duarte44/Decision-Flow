package main.java.decisaoflow.dominio;


import java.math.BigDecimal;

public class SolicitacaoCredito {

    private String id;
    private BigDecimal rendaMensal;
    private BigDecimal valorSolicitado;
    private int prazoMeses;

    public SolicitacaoCredito(String id, BigDecimal rendaMensal, BigDecimal valorSolicitado, int prazoMeses){

        if((id == null || id.isBlank()) || (rendaMensal == null || rendaMensal.compareTo(BigDecimal.ZERO) <= 0)
                || (valorSolicitado == null || valorSolicitado.compareTo(BigDecimal.ZERO) <= 0)
                || prazoMeses <= 0){

            throw new IllegalArgumentException("Campos vieram com valores errados");

        }

        this.id = id;
        this.rendaMensal = rendaMensal;
        this.valorSolicitado = valorSolicitado;
        this.prazoMeses = prazoMeses;

    }

    public String getId() {
        return id;
    }

    public BigDecimal getRendaMensal() {
        return rendaMensal;
    }

    public BigDecimal getValorSolicitado() {
        return valorSolicitado;
    }

    public int getPrazoMeses() {
        return prazoMeses;
    }


    @Override
    public String toString() {
        return "SolicitacaoCredito{" +
                "id = '" + id + '\'' +
                ", rendaMensal = " + rendaMensal +
                ", valorSolicitado = " + valorSolicitado +
                ", prazoMeses = " + prazoMeses +
                '}';
    }
}
