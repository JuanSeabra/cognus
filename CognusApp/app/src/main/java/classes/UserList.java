package classes;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Set;

/**
 * Created by d-zero on 19/07/17.
 */

public class UserList {
    @SerializedName("user")
    private List<Usuario> listUsuario;

    public List<Usuario> getListUsuario() {
        return listUsuario;
    }

    public void setListUsuario(List<Usuario> listUsuario) {
        this.listUsuario = listUsuario;
    }
}
