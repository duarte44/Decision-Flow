package main.java.decisaoflow;

import main.java.decisaoflow.dominio.SolicitacaoCredito;

import java.math.BigDecimal;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args){

        try {
            SolicitacaoCredito solicitacaoCredito1 = new SolicitacaoCredito(
                    null, new BigDecimal("222.05"), new BigDecimal("895.6"), 2);

            System.out.println(solicitacaoCredito1);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        try {
            SolicitacaoCredito solicitacaoCredito = new SolicitacaoCredito(
                    "12345", new BigDecimal("222.05"), new BigDecimal("895.6"), 2);
            System.out.println(solicitacaoCredito);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());

        }

        try {
            SolicitacaoCredito solicitacaoCredito = new SolicitacaoCredito(
                    " ", new BigDecimal("222.05"), new BigDecimal("895.6"), 2);
            System.out.println(solicitacaoCredito);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());

        }


    }
}
