package main.java.decisaoflow;

import main.java.decisaoflow.dominio.SolicitacaoCredito;
import main.java.decisaoflow.sevico.MotorDeDecisao;

import java.math.BigDecimal;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args){

     
        try {
            SolicitacaoCredito solicitacaoCredito = new SolicitacaoCredito(
                    "12345", new BigDecimal("3.000"), new BigDecimal("6.000"), 12);

            MotorDeDecisao motorDeDecisao = new MotorDeDecisao();
            motorDeDecisao.analise(solicitacaoCredito);
            System.out.println(motorDeDecisao.analise(solicitacaoCredito));

        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());

        }


    }
}
