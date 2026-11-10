# Decision-Flow

decisionflow/
├── pom.xml
├── README.md
├── .gitignore
└── src/
├── main/java/br/com/decisionflow/
│   ├── Main.java
│   ├── dominio/
│   │   ├── SolicitacaoCredito.java
│   │   ├── ResultadoAnalise.java
│   │   └── StatusDecisao.java
│   └── servico/
│       └── MotorDeDecisao.java
└── test/java/br/com/decisionflow/

DecisionFlow — Regras de negócio
Objetivo: desenvolver um motor que recebe uma solicitação fictícia de crédito e determina se ela será aprovada, reprovada ou encaminhada para análise manual.
1. Dados de entrada
   Cada solicitação contém:
   Campo	Descrição	Exemplo
   id	Identificador da solicitação	SOL-001
   rendaMensal	Renda mensal do solicitante	R$ 3.000
   valorSolicitado	Valor do crédito solicitado	R$ 6.000
   prazoMeses	Prazo de pagamento	12 meses

Todos os campos são obrigatórios. Identificador vazio, valores monetários nulos ou menores ou iguais a zero e prazo menor ou igual a zero tornam a solicitação inválida.
2. Cálculos necessários
   Antes de tomar qualquer decisão, o motor deverá calcular três informações.
   Simulador das regras

Altere os valores para testar diferentes situações antes de implementar a lógica no Java.
Renda (R$)

Crédito (R$)

Prazo (meses)


Parcela mensal (sem juros)
R$ 500,00


30% da renda
R$ 900,00


50% da renda
R$ 1.500,00


4× e 6× a renda

R$ 12.000,00 / R$ 18.000,00




Decisão calculada
APROVADA

As duas condições de aprovação foram atendidas.

3. Regras de decisão

RN01 — Reprovação

A solicitação será REPROVADA quando pelo menos uma das condições ocorrer:
- O valor solicitado for maior que 6 vezes a renda mensal.
- A parcela mensal for maior que 50% da renda mensal.
  Basta uma condição ser verdadeira. Portanto, utilize a lógica OU (||).

Exemplo: renda de R$ 3.000 e crédito de R$ 21.000. Como ultrapassa R$ 18.000 (6× a renda), a solicitação é reprovada.




RN02 — Análise manual

A solicitação será encaminhada para ANALISE_MANUAL quando não for reprovada e pelo menos uma destas condições ocorrer:
- O valor solicitado for maior que 4 vezes a renda mensal.
- A parcela mensal for maior que 30% da renda mensal.
  Isso abrange valores até 6 vezes a renda e parcelas até 50%, porque os casos superiores já foram reprovados na RN01.

Exemplo: renda de R$ 3.000, crédito de R$ 15.000 e prazo de 24 meses. A parcela é R$ 625, mas o valor excede 4 vezes a renda (R$ 12.000). Resultado: análise manual.




RN03 — Aprovação

A solicitação será APROVADA quando ambas as condições forem verdadeiras:
- O valor solicitado for menor ou igual a 4 vezes a renda mensal.
- A parcela mensal for menor ou igual a 30% da renda mensal.
  As duas condições devem ser verdadeiras. Portanto, utilize E (&&).

Exemplo: renda de R$ 3.000, crédito de R$ 6.000 e prazo de 12 meses. Parcela de R$ 500, abaixo de R$ 900, e crédito abaixo de R$ 12.000. Resultado: aprovada.



4. Ordem de execução
   A ordem é importante para evitar decisões conflitantes.
   Receber solicitação válida


Verificar RN01 — Reprovação


Verificar RN02 — Análise manual


Caso contrário — Aprovação


Como você já começou implementando a condição de aprovação, pode mantê-la, mas deverá garantir que todos os caminhos retornem exatamente uma decisão.
5. Cenários para validar seu código
   Renda	Crédito	Prazo	Resultado esperado
   R$ 3.000	R$ 6.000	12	Aprovada
   R$ 3.000	R$ 15.000	24	Análise manual
   R$ 3.000	R$ 21.000	24	Reprovada
   R$ 3.000	R$ 6.000	3	Reprovada
   R$ 3.000	R$ 12.000	12	Análise manual
   R$ 3.000	R$ 12.000	14	Aprovada

Esses cenários incluem situações em que apenas uma condição determina o resultado, além de valores próximos aos limites.