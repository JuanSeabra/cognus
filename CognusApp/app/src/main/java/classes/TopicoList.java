package classes;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Set;

/**
 * Created by victoria on 17/07/17.
 */

public class TopicoList {
    @SerializedName("topico")
    private List<Topico> listaTopicos;

    public TopicoList() {

    }

    public TopicoList(List<Topico> topicos) {
        this.listaTopicos = topicos;
    }

    public List<Topico> getListaTopicos() {
        return listaTopicos;
    }

    public void setListaTopicos(List<Topico> listaTopicos) {
        this.listaTopicos = listaTopicos;
    }
}
