package main.java.decisaoflow.dominio;

public class ResultadoAnalise {

    private StatusDecisao status;
    private String justificativa;
    private String idSolicitacao;


    public ResultadoAnalise(StatusDecisao status, String justificativa, String idSolicitacao) {

        if(status == null
                || (justificativa == null || justificativa.isBlank())
                || (idSolicitacao == null || idSolicitacao.isBlank())
        ){
            throw new IllegalArgumentException("Argumentos invalidos");

        }

        this.status = status;
        this.justificativa = justificativa;
        this.idSolicitacao = idSolicitacao;
    }

    public StatusDecisao getStatus() {
        return status;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public String getIdSolicitacao() {
        return idSolicitacao;
    }

    @Override
    public String toString() {
        return "ResultadoAnalise{" +
                "status = " + status +
                ", justificativa = '" + justificativa + '\'' +
                ", idSolicitacao = '" + idSolicitacao + '\'' +
                '}';
    }
}
