package classes;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Created by victoria on 19/06/17.
 */

public class Usuario implements Serializable {
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
    private List<Topico> listTopicos;

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
}