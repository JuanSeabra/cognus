package classes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by victoria on 28/06/17.
 */

public interface UsuarioService {
    public static final String URL_BASE="http://192.168.1.132/ws/";

    @Headers("Content-type: application/json")
    @POST("usuarios/cadastrarUsuario")
    Call<Usuario> cadastrarUsuario(@Body Usuario user);
}
