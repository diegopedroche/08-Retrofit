package diego.pedroche.a08_retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import diego.pedroche.a08_retrofit.Conexiones.ApiConexiones;
import diego.pedroche.a08_retrofit.Conexiones.RetrofitObject;
import diego.pedroche.a08_retrofit.Modelos.Photo;
import diego.pedroche.a08_retrofit.adapters.PhotosAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PhotosActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private PhotosAdapter adapter;
    private RecyclerView.LayoutManager la;
    private List<Photo> photos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        photos = new ArrayList<>();
        recycler = findViewById(R.id.contenedorPhotos);
        adapter = new PhotosAdapter(photos,this, R.layout.photo_view_holder);
        la = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(la);

        if(getIntent().getExtras().getString("ID_ALBUM") != null){
            doGetPhotos(getIntent().getExtras().getString("ID_ALBUM"));
        }
    }

    private void doGetPhotos(String idAlbum) {
        Retrofit retrofit = RetrofitObject.getConnection();
        ApiConexiones api = retrofit.create(ApiConexiones.class);
        Call<ArrayList<Photo>> getPhotos = api.getPhotosAlbum(idAlbum);

        getPhotos.enqueue(new Callback<ArrayList<Photo>>() {
            @Override
            public void onResponse(Call<ArrayList<Photo>> call, Response<ArrayList<Photo>> response) {
                if (response.code() == HttpURLConnection.HTTP_OK){
                    photos.addAll(response.body());
                    adapter.notifyItemRangeInserted(0, photos.size());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Photo>> call, Throwable t) {
                Toast.makeText(PhotosActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}