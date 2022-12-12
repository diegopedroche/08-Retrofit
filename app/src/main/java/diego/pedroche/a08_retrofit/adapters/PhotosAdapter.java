package diego.pedroche.a08_retrofit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import diego.pedroche.a08_retrofit.Modelos.Photo;
import diego.pedroche.a08_retrofit.R;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotoVH> {

    private List<Photo> objects;
    private Context context;
    private int resources;

    public PhotosAdapter(List<Photo> objects, Context context, int resources) {
        this.objects = objects;
        this.context = context;
        this.resources = resources;
    }

    @NonNull
    @Override
    public PhotoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhotoVH(LayoutInflater.from(context).inflate(resources,null));
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoVH holder, int position) {
        Photo p = objects.get(position);
        holder.lbTitulo.setText(p.getTitle());
        Picasso.get().load(p.getThumbnailUrl()/*URL de la imagen*/).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground).into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class PhotoVH extends RecyclerView.ViewHolder {

        ImageView imgPhoto;
        TextView lbTitulo;

        public PhotoVH(@NonNull View itemView){
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.imgImagenPhoto);
            lbTitulo = itemView.findViewById(R.id.lbTituloPhoto);
        }
    }
}
