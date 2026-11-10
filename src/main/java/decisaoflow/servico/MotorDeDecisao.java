package main.java.decisaoflow.servico;

import main.java.decisaoflow.dominio.ResultadoAnalise;
import main.java.decisaoflow.dominio.SolicitacaoCredito;
import main.java.decisaoflow.dominio.StatusDecisao;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Decide se um pedido de crédito será aprovado, reprovado ou enviado para análise manual,
 * comparando o valor solicitado e a parcela mensal com a renda do cliente.
 */
public class MotorDeDecisao {

    /**
     * Aplica as regras nesta ordem: reprovação, análise manual e aprovação.
     * O primeiro resultado encontrado encerra a análise.
     *
     * @param solicitacaoCredito pedido a avaliar: renda mensal do cliente, valor do empréstimo,
     *                           prazo de pagamento em meses e identificador da solicitação;
     *                           deve ser não nulo e conter os dados validados pelo seu construtor
     * @return decisão com status, justificativa e identificador do pedido analisado
     */
    public ResultadoAnalise analise(SolicitacaoCredito solicitacaoCredito) {

        // Valor que o cliente pagaria por mês: valor solicitado dividido pelo número de meses, sem juros.
        // A divisão usa duas casas decimais; HALF_UP arredonda para o centavo mais próximo
        // e, em caso de empate (meio centavo), para cima. As regras usam essa parcela arredondada.
        BigDecimal valorParcelaMensal = solicitacaoCredito.getValorSolicitado()
                .divide(BigDecimal.valueOf(solicitacaoCredito.getPrazoMeses()), 2, RoundingMode.HALF_UP);

        // Teto do valor solicitado para evitar reprovação: 6 vezes a renda mensal.
        // Exemplo: renda de R$ 2.000 permite um pedido de até R$ 12.000 nesta regra.
        BigDecimal limiteCreditoReprovacao = solicitacaoCredito.getRendaMensal().multiply(BigDecimal.valueOf(6));

        // Teto da parcela para evitar reprovação: 50% da renda mensal (renda multiplicada por 0,50).
        // Exemplo: com renda de R$ 2.000, uma parcela acima de R$ 1.000 reprova o pedido.
        BigDecimal limiteParcelaReprovacao = solicitacaoCredito.getRendaMensal().multiply(new BigDecimal("0.50"));


        // Reprova se QUALQUER um dos tetos for ultrapassado: crédito > 6 vezes a renda OU parcela > 50%.
        // compareTo(...) > 0 significa "maior que"; um valor igual ao teto não reprova por esta regra.
        if(solicitacaoCredito.getValorSolicitado().compareTo(limiteCreditoReprovacao) > 0
                || valorParcelaMensal.compareTo(limiteParcelaReprovacao) > 0){

            // Registra a reprovação com a sua justificativa e o ID que vincula o resultado ao pedido.
            ResultadoAnalise resultadoAnaliseNegada = new ResultadoAnalise(
                    StatusDecisao.REPROVADA,
                    "Solicitação Negada",
                    solicitacaoCredito.getId());

            return resultadoAnaliseNegada;

        }

        // Teto do valor solicitado para aprovação automática: 4 vezes a renda mensal.
        // Exemplo: com renda de R$ 2.000, o pedido deve ser de até R$ 8.000 para atender a este critério.
        BigDecimal limiteCreditoAprovacao = solicitacaoCredito.getRendaMensal().multiply(BigDecimal.valueOf(4));

        // Teto da parcela para aprovação automática: 30% da renda mensal (renda multiplicada por 0,30).
        // Exemplo: com renda de R$ 2.000, a parcela deve ser de até R$ 600 para atender a este critério.
        BigDecimal limiteParcelaAprovacao = solicitacaoCredito.getRendaMensal().multiply(new BigDecimal("0.30"));

        // Como o pedido já passou pelos tetos de reprovação, ultrapassar QUALQUER teto de aprovação
        // exige análise manual: crédito > 4 vezes a renda OU parcela > 30% da renda.
        if(solicitacaoCredito.getValorSolicitado().compareTo(limiteCreditoAprovacao) > 0
                || valorParcelaMensal.compareTo(limiteParcelaAprovacao) > 0){

            // Registra que o pedido precisa de avaliação manual, mantendo a justificativa e o ID do pedido.
            ResultadoAnalise resultadoAnaliseManual = new ResultadoAnalise(StatusDecisao.ANALISE_MANUAL, "Analise Manual", solicitacaoCredito.getId());
            return resultadoAnaliseManual;

        }



        // Chegar aqui significa que os DOIS critérios de aprovação foram atendidos:
        // crédito <= 4 vezes a renda E parcela <= 30% da renda. Os valores iguais aos limites são aceitos.
        // Registra a aprovação automática, a sua justificativa e o ID do pedido aprovado.
        ResultadoAnalise resultadoAnaliseAprovada = new ResultadoAnalise(
                StatusDecisao.APROVADA,
                "Solicitação aprovada",
                solicitacaoCredito.getId());
        return resultadoAnaliseAprovada;
    }

}
