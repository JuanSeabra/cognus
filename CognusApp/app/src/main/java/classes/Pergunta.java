package classes;

import java.io.Serializable;

/**
 * Created by victoria on 19/06/17.
 */

public class Pergunta implements Serializable{
    private int perg_id;
    private String texto_perg;
    private String descricao;
    private int user_id;

    public Pergunta(String texto_perg, String descricao, int user_id) {
        this.perg_id = 0;
        this.texto_perg = texto_perg;
        this.descricao = descricao;
        this.user_id = user_id;
    }

    public Pergunta(String texto_perg, String descricao) {
        this.perg_id = 0;
        this.texto_perg = texto_perg;
        this.descricao = descricao;
    }

    public Pergunta(String texto_perg, String descricao, int perg_id, int user_id) {
        this.perg_id = perg_id;
        this.texto_perg = texto_perg;
        this.descricao = descricao;
    }

    public int getperg_id() {
        return perg_id;
    }

    public void setperg_id(int perg_id) {
        this.perg_id = perg_id;
    }

    public String gettexto_perg() {
        return texto_perg;
    }

    public void settexto_perg(String texto_perg) {
        this.texto_perg = texto_perg;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getuser_id() {
        return user_id;
    }

    public void setuser_id(int user_id) {
        this.user_id = user_id;
    }
}
