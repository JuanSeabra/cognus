package classes;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by victoria on 28/06/17.
 */

public interface UsuarioService {
    public static final String URL_BASE="http://192.168.43.67/ws/";

    @Headers("Content-type: application/json")
    @POST("usuarios/cadastrarUsuario")
    Call<Usuario> cadastrarUsuario(@Body Usuario user);
    @POST("usuarios/consultarUsuario")
    Call<Usuario> buscarUsuario(@Body String id);
    @POST("usuarios/consultarUsuarioEmail")
    Call<Usuario> buscarUsuarioEmail(@Body String email);
    @POST("usuarios/autenticarUsuario")
    Call<Usuario> autenticarUsuario(@Body HashMap<String, String> email_senha);
}
