package classes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by d-zero on 12/07/17.
 */

public interface PerguntaService {
    public static final String URL_BASE="http://192.168.1.132/ws/";

    @Headers("Content-type: application/json")
    @POST("usuarios/cadastrarUsuario")
    Call<Pergunta> cadastrarPergunta(@Body Pergunta pergunta);
}
