package diego.pedroche.a08_retrofit.Conexiones;

import diego.pedroche.a08_retrofit.Configuraciones.Constantes;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitObject {

    public static Retrofit getConnection(){
        return new Retrofit.Builder().baseUrl(Constantes.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }
}
