package classes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by victoria on 28/06/17.
 */

public interface APIInterface {

    @POST("/api/users")
    Call<Usuario> createUser(@Body Usuario user);
}
