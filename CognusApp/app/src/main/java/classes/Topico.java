package classes;

/**
 * Created by victoria on 19/06/17.
 */

public class Topico {
    private int id;
    private String descricao;
    private int num_seguidores;

    public Topico(String descricao) {
        this.descricao = descricao;
    }

    public Topico(String descricao, int num_seguidores) {
        this.descricao = descricao;
        this.num_seguidores = num_seguidores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getNum_seguidores() {
        return num_seguidores;
    }

    public void setNum_seguidores(int num_seguidores) {
        this.num_seguidores = num_seguidores;
    }
}
