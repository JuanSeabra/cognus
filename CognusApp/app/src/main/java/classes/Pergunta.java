package classes;

import java.io.Serializable;

/**
 * Created by victoria on 19/06/17.
 */

public class Pergunta implements Serializable{
    private int id;
    private String texto;
    private String descricao;
    private int usuario;

    public Pergunta(String texto, String descricao, int usuario) {
        this.id = 0;
        this.texto = texto;
        this.descricao = descricao;
        this.usuario = usuario;
    }

    public Pergunta(String texto, String descricao) {
        this.id = 0;
        this.texto = texto;
        this.descricao = descricao;
    }

    public Pergunta(String texto, String descricao, int id, int usuario) {
        this.id = id;
        this.texto = texto;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }
}
