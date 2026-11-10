# DecisionFlow

## 1. Sobre o projeto

O DecisionFlow é uma aplicação de console que simula a análise de solicitações de crédito. A partir da renda mensal, do valor solicitado e do prazo de pagamento, aplica critérios padronizados para aprovar, reprovar ou encaminhar o pedido para análise manual, retornando a decisão, uma justificativa e o identificador da solicitação.
Este projeto tem finalidade de estudo. Os critérios utilizados são fictícios e não representam políticas reais de concessão de crédito.

## 2. Tecnologias

- **Java 21**, com classes, construtores e encapsulamento para representar os dados e as regras.
- **BigDecimal e RoundingMode.HALF_UP** para cálculos monetários e arredondamento.
- **Enum** para representar os possíveis status da decisão.
- **List, ArrayList e for-each** para armazenar e percorrer as solicitações de exemplo.
- **Condicionais, operadores lógicos e IllegalArgumentException** para aplicar as regras e rejeitar dados inválidos.

## 3. Regras de negócio

A parcela mensal é calculada dividindo o valor solicitado pelo prazo em meses, sem juros, com duas casas decimais e arredondamento `HALF_UP`.

| Decisão | Condição |
| --- | --- |
| **Reprovação** | Valor solicitado **maior que 6 vezes a renda** OU parcela **maior que 50% da renda**. |
| **Análise manual** | Sem atender às condições de reprovação, valor solicitado **maior que 4 vezes a renda** OU parcela **maior que 30% da renda**. |
| **Aprovação** | Valor solicitado **menor ou igual a 4 vezes a renda** E parcela **menor ou igual a 30% da renda**. |

O motor verifica primeiro a reprovação, depois a análise manual e, por fim, a aprovação. Valores iguais aos limites não os ultrapassam. O identificador deve ser preenchido, a renda e o valor solicitado devem ser positivos e o prazo deve ser maior que zero; dados inválidos geram uma exceção na criação da solicitação.

## 4. Estrutura

As classes ficam em `src/br/com/decisionflow`, organizadas nos pacotes `dominio` e `servico`, além da classe principal.

| Classe | Responsabilidade |
| --- | --- |
| `Main` | Cria solicitações de exemplo, chama o motor e exibe os resultados no console. |
| `dominio.SolicitacaoCredito` | Armazena e valida o identificador, a renda mensal, o valor solicitado e o prazo. |
| `dominio.ResultadoAnalise` | Armazena e valida o status, a justificativa e o identificador do pedido analisado. |
| `dominio.StatusDecisao` | Define os status `APROVADA`, `REPROVADA` e `ANALISE_MANUAL`. |
| `servico.MotorDeDecisao` | Calcula a parcela e os limites de renda, aplica as regras e retorna o resultado da análise. |

## 5. Como executar

1. Abra a pasta do projeto no IntelliJ IDEA usando **File > Open**.
2. Em **File > Project Structure > Project**, selecione o **JDK 21** como SDK do projeto. Caso necessário, adicione ou baixe esse JDK.
3. Confira se a pasta `src` está marcada como **Sources Root**. Se necessário, clique nela com o botão direito e escolha **Mark Directory as > Sources Root**.
4. Abra `src/br/com/decisionflow/Main.java` e clique no ícone de execução ao lado do método `main`, selecionando **Run 'Main.main()'**.
5. Veja os resultados na janela **Run**. Para experimentar outros cenários, altere as solicitações criadas em `Main` e execute novamente.

## 6. Testes manuais

Foram validados os sete cenários definidos em `Main`, comparando os resultados no console com as regras de negócio. Os testes abrangeram aprovação, reprovação, análise manual e valores nos limites de 4 e 6 vezes a renda mensal e de parcela igual a 50% da renda. Todos apresentaram os resultados esperados: duas aprovações, uma reprovação e quatro encaminhamentos para análise manual.
