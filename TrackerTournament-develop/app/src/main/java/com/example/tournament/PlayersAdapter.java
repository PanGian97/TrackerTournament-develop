package com.example.tournament;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;



public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder> {//specify that is a view holder type<...>
    private static final String TAG="RecyclerViewAdapter";//for debugging
    private ArrayList<String>list_playerNames = new ArrayList<>();

    private Context list_context;

    public PlayersAdapter(Context list_context,ArrayList<String> list_playerNames) {//default constructor to construct the pieces
        this.list_playerNames = list_playerNames;

        this.list_context = list_context;
    }

    @NonNull
    @Override
    public PlayersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {  //responsible for inflating the view

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item,parent,false); // almost standard,recycling the view holders and place them in the right position
        PlayersViewHolder holder = new PlayersViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlayersViewHolder holder, final int position) {//what you want your layouts look like
        Log.d(TAG,"onBindViewHolder:called.");//knowing which item failed
        //gets called every time a new item is added(6 brands = print 6 times)

        holder.bind(list_playerNames.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playerPos= list_playerNames.get(position);

                Toast.makeText(list_context,"Clicked : " + playerPos,Toast.LENGTH_SHORT).show();
            }
        });
        holder.deletePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playerPos= list_playerNames.get(position);

                // Remove the item on remove/button click

                Toast.makeText(list_context,"Removed : " + playerPos,Toast.LENGTH_SHORT).show();
                Intent intent  = new Intent(list_context,PlayersActivity.class);
                intent.putExtra("player_pos_name",list_playerNames.get(position));
                list_playerNames.remove(position);
               // notifyDataSetChanged();

            }
        });



    }



    @Override
    public int getItemCount() {
        return list_playerNames.size();//tells adaptor how many items is in your list
    }

    public class PlayersViewHolder extends RecyclerView.ViewHolder{//holds widgets in memory of each individual entry

        TextView playerName;
        RelativeLayout parentLayout;
        Button deletePlayer;

        public PlayersViewHolder(@NonNull View itemView) {
            super(itemView);

            playerName = itemView.findViewById(R.id.player_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            deletePlayer=itemView.findViewById(R.id.delete_player);
        }

        public void bind(String player) {
            playerName.setText(player);
        }
    }
}