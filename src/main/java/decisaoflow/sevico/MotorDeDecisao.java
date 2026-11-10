package main.java.decisaoflow.sevico;

import main.java.decisaoflow.dominio.ResultadoAnalise;
import main.java.decisaoflow.dominio.SolicitacaoCredito;
import main.java.decisaoflow.dominio.StatusDecisao;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MotorDeDecisao {

    public ResultadoAnalise analise(SolicitacaoCredito solicitacaoCredito) {

        /*Aqui estamos pegando o valor solicitado, dividindo pelo prazo de meses.
        Posteriormente aplicando a regra de 2 casas decimais e o metodo de aredondamento
        */
        BigDecimal parcelaMensal = solicitacaoCredito.getValorSolicitado()
                .divide(BigDecimal.valueOf(solicitacaoCredito.getPrazoMeses()), 2, RoundingMode.HALF_UP);

        /*Pegamos a renda mensal e multiplicamos por 30, pra pegar 30% da renda mensal*/
        BigDecimal limiteDeParcela = solicitacaoCredito.getRendaMensal().multiply(new BigDecimal("0.30"));

        /*Pegamos a renda e multiplicamos por 4, para calcular o limite de credito*/
        BigDecimal limiteDeCredito = solicitacaoCredito.getRendaMensal().multiply(BigDecimal.valueOf(4));

        if (parcelaMensal.compareTo(limiteDeParcela) <= 0 && solicitacaoCredito.getValorSolicitado().compareTo(limiteDeCredito) <= 0) {
            ResultadoAnalise resultadoAnalise = new ResultadoAnalise(
                    StatusDecisao.APROVADA,
                    "Solicitação aprovada",
                    solicitacaoCredito.getId());
            return resultadoAnalise;
        }

        throw new UnsupportedOperationException("Demais regras ainda não implementadas");
    }
}
