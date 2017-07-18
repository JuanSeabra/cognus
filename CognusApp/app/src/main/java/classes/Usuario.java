package classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Created by victoria on 19/06/17.
 */

public class Usuario implements Parcelable {
    @SerializedName("listTopicos")
    private List<Topico> listTopicos;
    @SerializedName("user_id")
    private int user_id;
    @SerializedName("user_name")
    private String user_name;
    @SerializedName("user_email")
    private String user_email;
    @SerializedName("user_senha")
    private String user_senha;
    @SerializedName("user_numseguidores")
    private int user_numseguidores;


    public Usuario(String user_name, String user_email, String user_senha, int user_id) {

        this.user_name = user_name;

        this.user_email = user_email;
        this.user_senha = user_senha;
        this.user_id = user_id;
    }

    public Usuario(String user_email, String user_senha) {
        this.user_email = user_email;
        this.user_senha = user_senha;
    }

    protected Usuario(Parcel in) {
        user_id = in.readInt();
        user_name = in.readString();
        user_email = in.readString();
        user_senha = in.readString();
        user_numseguidores = in.readInt();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    public List<Topico> getListTopicos() {
        return listTopicos;
    }

    public void setListTopicos(List<Topico> listTopicos) {
        this.listTopicos = listTopicos;
    }

    public Usuario(String user_name, String user_email, String user_senha) {
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_senha = user_senha;
        this.user_numseguidores = 0;
    }

    public Usuario(int user_id, String user_name, String user_email, String user_senha, int user_numseguidores) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_senha = user_senha;
        this.user_numseguidores = user_numseguidores;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_senha() {
        return user_senha;
    }

    public void setUser_senha(String user_senha) {
        this.user_senha = user_senha;
    }

    public int getUser_numseguidores() {
        return user_numseguidores;
    }

    public void setUser_numseguidores(int user_numseguidores) {
        this.user_numseguidores = user_numseguidores;
    }

    public String toString() {
        return  "ID: " + this.user_id + "\n" +
                "Nome: " + this.user_name + "\n" +
                "Email:" + this.user_email + "\n" +
                        "Senha: " + this.user_senha;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(user_id);
        dest.writeString(user_name);
        dest.writeString(user_email);
        dest.writeString(user_senha);
        dest.writeInt(user_numseguidores);
    }


}