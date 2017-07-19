package classes;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Set;

/**
 * Created by d-zero on 19/07/17.
 */

public class RespostaList {
    @SerializedName("resposta")
    private List<Resposta> listRespostas;

    public List<Resposta> getListRespostas() {
        return listRespostas;
    }

    public void setListRespostas(List<Resposta> listRespostas) {
        this.listRespostas = listRespostas;
    }
}
