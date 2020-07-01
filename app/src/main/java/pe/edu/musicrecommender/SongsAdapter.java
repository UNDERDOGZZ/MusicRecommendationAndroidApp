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

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.SongAdapterVH> {


    private List<SongResponse> songResponseList;
    private Context context;
    private ClickedItem clickedItem;


    public SongsAdapter(ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }



    public void setData(List<SongResponse> songResponseList) {
        this.songResponseList = songResponseList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public SongAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new SongsAdapter.SongAdapterVH(LayoutInflater.from(context).inflate(R.layout.row_songs,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SongAdapterVH holder, int position) {

        SongResponse songResponse = songResponseList.get(position);

        String songname = songResponse.getTitle();



        holder.songname.setText(songname);
        holder.imagePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedItem.ClickedSong(songResponse);
            }
        });

    }



    public interface ClickedItem{
        public void ClickedSong(SongResponse songResponse);
    }

    @Override
    public int getItemCount() {
        return songResponseList.size();
    }

    public class SongAdapterVH extends RecyclerView.ViewHolder {

        TextView songname;
        ImageView imagePlay;

        public SongAdapterVH(@NonNull View itemView) {
            super(itemView);
            songname = itemView.findViewById(R.id.songname);
            imagePlay = itemView.findViewById(R.id.imageMore);


        }
    }


}
