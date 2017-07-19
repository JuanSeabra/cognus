package classes;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Set;

/**
 * Created by victoria on 17/07/17.
 */

public class PerguntaList {
    @SerializedName("pergunta")
    private List<Pergunta> listaPerguntas;

    public PerguntaList() {

    }

    public List<Pergunta> getListaPerguntas() {
        return listaPerguntas;
    }

    public void setListaPerguntas(List<Pergunta> listaPerguntas) {
        this.listaPerguntas = listaPerguntas;
    }
}
