package classes;

import android.content.res.Resources;

import com.example.victoria.cognusapp.R;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by d-zero on 19/07/17.
 */

public interface RespostaService {
    public static final String URL_BASE= Resources.getSystem().getString(R.string.ip_requisicao);

    @Headers("Content-type: application/json")
    @GET("respostas/listarRespostas")
    Call<Resposta> listarRespostas();
    @POST("respostas/listarRespostaPergunta")
    Call<RespostaList> listarRespostasPergunta(@Body long id);
}
