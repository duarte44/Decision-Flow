package br.com.decisionflow;

import br.com.decisionflow.dominio.SolicitacaoCredito;
import br.com.decisionflow.servico.MotorDeDecisao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args){

        List<SolicitacaoCredito> listaCredito = new ArrayList<>();
        MotorDeDecisao motorDeDecisao = new MotorDeDecisao();



        listaCredito.add(new SolicitacaoCredito("1", new BigDecimal("3000"), new BigDecimal("6000"), 12));
        listaCredito.add(new SolicitacaoCredito("2", new BigDecimal("3000"), new BigDecimal("15000"), 24));
        listaCredito.add(new SolicitacaoCredito("3", new BigDecimal("3000"), new BigDecimal("21000"), 24));
        listaCredito.add(new SolicitacaoCredito("4", new BigDecimal("3000"), new BigDecimal("6000"), 5));
        listaCredito.add(new SolicitacaoCredito("5", new BigDecimal("3000"), new BigDecimal("12000"), 14));
        listaCredito.add(new SolicitacaoCredito("6", new BigDecimal("3000"), new BigDecimal("18000"), 24));
        listaCredito.add(new SolicitacaoCredito("7", new BigDecimal("3000"), new BigDecimal("6000"), 4));

        for (SolicitacaoCredito solicitacaoCredito : listaCredito){
            System.out.println(motorDeDecisao.analise(solicitacaoCredito));
        }


    }
}
