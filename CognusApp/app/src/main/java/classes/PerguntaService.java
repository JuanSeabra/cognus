package classes;

import android.content.res.Resources;

import com.example.victoria.cognusapp.R;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by d-zero on 12/07/17.
 */

public interface PerguntaService {
    public static final String URL_BASE= Resources.getSystem().getString(R.string.ip_requisicao);

    @Headers("Content-type: application/json")
    @POST("perguntas/cadastrarPergunta")
    Call<Pergunta> cadastrarPergunta(@Body Pergunta pergunta);

    @GET("perguntas/listarPerguntas")
    Call<PerguntaList> listarPerguntas();
}
