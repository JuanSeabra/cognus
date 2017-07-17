package classes;

import com.google.gson.annotations.SerializedName;

/**
 * Created by victoria on 19/06/17.
 */

public class Topico {
    @SerializedName("topico_id")
    private int topico_id;
    @SerializedName("descricao_topico")
    private String descricao_topico;
    @SerializedName("num_seguidores")
    private int num_seguidores;

    public Topico(String descricao_topico) {
        this.descricao_topico = descricao_topico;
    }

    public Topico(String descricao_topico, int num_seguidores) {
        this.descricao_topico = descricao_topico;
        this.num_seguidores = num_seguidores;
    }

    public int gettopico_id() {
        return topico_id;
    }

    public void settopico_id(int topico_id) {
        this.topico_id = topico_id;
    }

    public String getdescricao_topico() {
        return descricao_topico;
    }

    public void setdescricao_topico(String descricao_topico) {
        this.descricao_topico = descricao_topico;
    }

    public int getnum_seguidores() {
        return num_seguidores;
    }

    public void setnum_seguidores(int num_seguidores) {
        this.num_seguidores = num_seguidores;
    }
}
