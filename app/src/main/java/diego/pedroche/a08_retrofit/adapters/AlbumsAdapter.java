package diego.pedroche.a08_retrofit.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import diego.pedroche.a08_retrofit.Modelos.Album;
import diego.pedroche.a08_retrofit.PhotosActivity;
import diego.pedroche.a08_retrofit.R;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumVH> {
    private Context context;
    private List<Album> objects;
    private int resource;

    public AlbumsAdapter(List<Album> objects, Context context, int resource) {
        this.context = context;
        this.objects = objects;
        this.resource = resource;
    }

    @NonNull
    @Override
    public AlbumVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View albumView = LayoutInflater.from(context).inflate(resource,null);
        albumView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new AlbumVH(albumView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumVH holder, int position) {
        Album a = objects.get(position);
        holder.lbTitulo.setText(a.getTitulo());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PhotosActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("ID_ALBUM", String.valueOf(a.getId()));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class AlbumVH extends RecyclerView.ViewHolder{
        TextView lbTitulo;
        public AlbumVH(@NonNull View itemView){
            super(itemView);
            lbTitulo = itemView.findViewById(R.id.lbTituloAlbumVH);
        }
    }
}
