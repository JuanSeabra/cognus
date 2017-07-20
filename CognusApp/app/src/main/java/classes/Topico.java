package classes;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victoria on 19/06/17.
 */

public class Topico implements Parcelable {
    @SerializedName("topico_id")
    private int topico_id;
    @SerializedName("descricao_topico")
    private String descricao_topico;
    @SerializedName("num_seguidores")
    private int num_seguidores;
    @SerializedName("listPerguntas")
    private List<Pergunta> listPerguntas = new ArrayList<Pergunta>();

    protected Topico(Parcel in) {
        topico_id = in.readInt();
        descricao_topico = in.readString();
        num_seguidores = in.readInt();
        listPerguntas = in.createTypedArrayList(Pergunta.CREATOR);
    }

    public static final Creator<Topico> CREATOR = new Creator<Topico>() {
        @Override
        public Topico createFromParcel(Parcel in) {
            return new Topico(in);
        }

        @Override
        public Topico[] newArray(int size) {
            return new Topico[size];
        }
    };

    public List<Pergunta> getListPerguntas() {
        return listPerguntas;
    }

    public void setListPerguntas(List<Pergunta> listPerguntas) {
        this.listPerguntas = listPerguntas;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(topico_id);
        dest.writeString(descricao_topico);
        dest.writeInt(num_seguidores);
        dest.writeTypedList(listPerguntas);
    }
}
