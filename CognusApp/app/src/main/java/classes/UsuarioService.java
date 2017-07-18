package classes;

import android.content.res.Resources;

import com.example.victoria.cognusapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by victoria on 28/06/17.
 */

public interface UsuarioService {
    public static final String URL_BASE= Resources.getSystem().getString(R.string.ip_requisicao);

    @Headers("Content-type: application/json")
    @POST("usuarios/cadastrarUsuario")
    Call<Usuario> cadastrarUsuario(@Body Usuario user);
    @POST("usuarios/consultarUsuario")
    Call<Usuario> buscarUsuario(@Body String id);
    @POST("usuarios/consultarUsuarioEmail")
    Call<Usuario> buscarUsuarioEmail(@Body String email);

    @Headers("Content-type: application/json")
    @POST("usuarios/autenticarUsuario")
    Call<Usuario> autenticarUsuario(@Body Usuario user);
}
