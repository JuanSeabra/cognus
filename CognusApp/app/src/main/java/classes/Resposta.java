package classes;

import com.google.gson.annotations.SerializedName;

/**
 * Created by waldomiro on 20/06/17.
 */

public class Resposta {
    @SerializedName("texto_resp")
    private String texto_resp;
    @SerializedName("num_votos_positivos")
    private int num_votos_positivos;
    @SerializedName("num_votos_negativos")
    private int num_votos_negativos;
    @SerializedName("resp_id")
    private int resp_id;
    @SerializedName("user_id")
    private Usuario user_id;

    public Usuario getuser_id() {
        return user_id;
    }

    public void setuser_id(Usuario user_id) {
        this.user_id = user_id;
    }

    public int getPergunta() {
        return pergunta;
    }

    public void setPergunta(int pergunta) {
        this.pergunta = pergunta;
    }

    private int pergunta;

    public Resposta(String texto_resp, int num_votos_positivos, int num_votos_negativos, int resp_id, Usuario user_id, int pergunta) {
        this.texto_resp = texto_resp;
        this.num_votos_positivos = num_votos_positivos;
        this.num_votos_negativos = num_votos_negativos;
        this.resp_id = resp_id;
        this.user_id = user_id;
        this.pergunta = pergunta;
    }

    public Resposta(String texto_resp, int num_votos_positivos, int num_votos_negativos, Usuario user_id, int pergunta) {
        this.texto_resp = texto_resp;
        this.num_votos_positivos = num_votos_positivos;
        this.num_votos_negativos = num_votos_negativos;
        this.user_id = user_id;
        this.pergunta = pergunta;
    }

    public String gettexto_resp() {
        return texto_resp;
    }

    public void settexto_resp(String texto_resp) {
        this.texto_resp = texto_resp;
    }

    public int getnum_votos_positivos() {
        return num_votos_positivos;
    }

    public void setnum_votos_positivos(int num_votos_positivos) {
        this.num_votos_positivos = num_votos_positivos;
    }

    public int getnum_votos_negativos() {
        return num_votos_negativos;
    }

    public void setnum_votos_negativos(int num_votos_negativos) {
        this.num_votos_negativos = num_votos_negativos;
    }

    public int getresp_id() {
        return resp_id;
    }

    public void setresp_id(int resp_id) {
        this.resp_id = resp_id;
    }
}
