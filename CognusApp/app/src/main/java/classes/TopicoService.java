package classes;

import android.content.res.Resources;

import com.example.victoria.cognusapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by victoria on 17/07/17.
 */

public interface TopicoService {
    public static final String URL_BASE= Resources.getSystem().getString(R.string.ip_requisicao);

    @Headers("Content-type: application/json")
    @GET("topicos/listarTopicos")
    Call<TopicoList> listarTopicos();

    @POST("topicos/listarTopicoPergunta")
    Call<TopicoList> listarTopicoPergunta(@Body long id);
    @POST("topicos/listarPerguntasTopico")
    Call<PerguntaList> listarPerguntasTopico(@Body long id);
    @POST("topicos/listarTopicosUsuario")
    Call<TopicoList> listarTopicosUsuario(@Body long id);
}
