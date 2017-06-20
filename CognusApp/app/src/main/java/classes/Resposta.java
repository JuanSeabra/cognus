package classes;

/**
 * Created by waldomiro on 20/06/17.
 */

public class Resposta {
    private String texto;
    private int positivacoes;
    private int negativacoes;
    private int id;
    private int usuario;

    public Resposta(String texto, int positivacoes, int negativacoes, int id, int usuario) {
        this.texto = texto;
        this.positivacoes = positivacoes;
        this.negativacoes = negativacoes;
        this.id = id;
        this.usuario = usuario;
    }

    public Resposta(String texto, int positivacoes, int negativacoes, int usuario) {
        this.texto = texto;
        this.positivacoes = positivacoes;
        this.negativacoes = negativacoes;
        this.usuario = usuario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getPositivacoes() {
        return positivacoes;
    }

    public void setPositivacoes(int positivacoes) {
        this.positivacoes = positivacoes;
    }

    public int getNegativacoes() {
        return negativacoes;
    }

    public void setNegativacoes(int negativacoes) {
        this.negativacoes = negativacoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
