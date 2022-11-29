package diego.pedroche.a08_retrofit.Conexiones;

import java.util.ArrayList;

import diego.pedroche.a08_retrofit.Modelos.Album;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiConexiones {

    //Todos los endpoints y retornos de la API
    @GET("/albums")
    Call<ArrayList<Album>> getAlbums();
    @GET("/albums/{idAlbum}")
    Call<Album> getAlbum(@Path("idAlbum") String idAlbum);
}
