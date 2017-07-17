package classes;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by victoria on 17/07/17.
 */

public class PerguntaList {
    @SerializedName("pergunta")
    private List<Pergunta> listaPerguntas;

    public List<Pergunta> getListaPerguntas() {
        return listaPerguntas;
    }

    public void setListaPerguntas(List<Pergunta> listaPerguntas) {
        this.listaPerguntas = listaPerguntas;
    }
}
