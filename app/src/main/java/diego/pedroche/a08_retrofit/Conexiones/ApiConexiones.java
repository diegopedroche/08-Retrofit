package diego.pedroche.a08_retrofit.Conexiones;

import java.util.ArrayList;

import diego.pedroche.a08_retrofit.Modelos.Album;
import diego.pedroche.a08_retrofit.Modelos.Photo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiConexiones {

    //Todos los endpoints y retornos de la API
    @GET("/albums")
    Call<ArrayList<Album>> getAlbums();

    @GET("/albums/{idAlbum}")
    Call<Album> getAlbum(@Path("idAlbum") String idAlbum);


    // /photos?albumId=2
    @GET("/photos?")
    Call<ArrayList<Photo>> getPhotosAlbum(@Query("albumId") String idAlbum);
}
