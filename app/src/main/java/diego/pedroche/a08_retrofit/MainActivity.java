package diego.pedroche.a08_retrofit;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Toast;


import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import diego.pedroche.a08_retrofit.Conexiones.ApiConexiones;
import diego.pedroche.a08_retrofit.Conexiones.RetrofitObject;
import diego.pedroche.a08_retrofit.Modelos.Album;
import diego.pedroche.a08_retrofit.adapters.AlbumsAdapter;
import diego.pedroche.a08_retrofit.databinding.ActivityMainBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private List<Album> albums;
    private AlbumsAdapter adapter;
    private RecyclerView.LayoutManager la;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        albums = new ArrayList<>();
        adapter = new AlbumsAdapter(albums,this, R.layout.album_view_holder);
        la = new LinearLayoutManager(this);
        binding.contentMain.contenedorAlbums.setAdapter(adapter);
        binding.contentMain.contenedorAlbums.setLayoutManager(la);

        //RETROFIT
        doGetAlbums();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void doGetAlbums() {
        Retrofit retrofit = RetrofitObject.getConnection();
        ApiConexiones api = retrofit.create(ApiConexiones.class);

        Call<ArrayList<Album>> getAlbums = api.getAlbums();
        getAlbums.enqueue(new Callback<ArrayList<Album>>() {
            @Override
            public void onResponse(Call<ArrayList<Album>> call, Response<ArrayList<Album>> response) {
                if (response.code() == HttpURLConnection.HTTP_OK){
                    ArrayList<Album> temp = response.body();
                    albums.addAll(temp);
                    adapter.notifyItemRangeInserted(0,temp.size());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Album>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                Log.e("Failure",t.getLocalizedMessage());
            }
        });
    }
}