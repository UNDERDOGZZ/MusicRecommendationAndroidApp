package pe.edu.musicrecommender;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SongsGenreAdapter extends RecyclerView.Adapter<SongsGenreAdapter.SongsGenreAdapterVH>{

    private List<SongsGenreResponse> songsGenreResponseList;
    private Context context;
    private SongsGenreAdapter.ClickedItem clickedItem;


    public SongsGenreAdapter(SongsGenreAdapter.ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }



    public void setData(List<SongsGenreResponse> songsGenreResponseList) {
        this.songsGenreResponseList = songsGenreResponseList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public SongsGenreAdapter.SongsGenreAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new SongsGenreAdapter.SongsGenreAdapterVH(LayoutInflater.from(context).inflate(R.layout.row_songs_genre,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SongsGenreAdapter.SongsGenreAdapterVH holder, int position) {

        SongsGenreResponse songsGenreResponse = songsGenreResponseList.get(position);

        String songname = songsGenreResponse.getTitle();



        holder.songname.setText(songname);
        holder.imagePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedItem.ClickedSong(songsGenreResponse);
            }
        });

    }



    public interface ClickedItem{
        public void ClickedSong(SongsGenreResponse songsGenreResponse);
    }

    @Override
    public int getItemCount() {
        return songsGenreResponseList.size();
    }

    public class SongsGenreAdapterVH extends RecyclerView.ViewHolder {

        TextView songname;
        ImageView imagePlay;

        public SongsGenreAdapterVH(@NonNull View itemView) {
            super(itemView);
            songname = itemView.findViewById(R.id.songname_genre);
            imagePlay = itemView.findViewById(R.id.imageMore_genre);


        }
    }


}
