package main.java.decisaoflow;

import main.java.decisaoflow.dominio.SolicitacaoCredito;
import main.java.decisaoflow.servico.MotorDeDecisao;

import java.math.BigDecimal;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args){



        SolicitacaoCredito solicitacaoCredito = new SolicitacaoCredito(
                "1", new BigDecimal("3000"), new BigDecimal("6000"), 12);

        SolicitacaoCredito solicitacaoCredito2 = new SolicitacaoCredito(
                "2", new BigDecimal("3000"), new BigDecimal("15000"), 24);

        SolicitacaoCredito solicitacaoCredito3 = new SolicitacaoCredito(
                "3", new BigDecimal("3000"), new BigDecimal("21000"), 24);

        SolicitacaoCredito solicitacaoCredito4 = new SolicitacaoCredito(
                "4", new BigDecimal("3000"), new BigDecimal("6000"), 5);

        MotorDeDecisao motorDeDecisao = new MotorDeDecisao();
        System.out.println(motorDeDecisao.analise(solicitacaoCredito));
        System.out.println(motorDeDecisao.analise(solicitacaoCredito2));
        System.out.println(motorDeDecisao.analise(solicitacaoCredito3));
        System.out.println(motorDeDecisao.analise(solicitacaoCredito4));




    }
}
