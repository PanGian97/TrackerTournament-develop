package com.example.tournament;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class CompetitorsAdapter extends RecyclerView.Adapter<CompetitorsAdapter.CompetitorsViewHolder> {//specify that is a view holder type<...>
    private static final String TAG="RecyclerViewAdapter";//for debugging
    private ArrayList<String> list_first_contender = new ArrayList<>();
    private ArrayList<String> list_second_contender = new ArrayList<>();
    private ArrayList<String> winner_contender = new ArrayList<>();
    private ArrayList<String> extraPlayers = new ArrayList<>();
    private Boolean buttonState;
    private Context list_context;
    private String toNextRound;

    public CompetitorsAdapter(Context list_context, ArrayList<String> list_first_contender, ArrayList<String> list_second_contender) {//default constructor to construct the pieces
        this.list_first_contender = list_first_contender;
        this.list_second_contender = list_second_contender;
        this.list_context = list_context;
    }
    public CompetitorsAdapter(Context list_context, ArrayList<String> list_first_contender, ArrayList<String> list_second_contender,String toNextRound) {//default constructor to construct the pieces
        this.list_first_contender = list_first_contender;
        this.list_second_contender = list_second_contender;
        this.list_context = list_context;
        this.toNextRound = toNextRound;
        winner_contender.add(toNextRound);
        extraPlayers.add(toNextRound);
    }

    @NonNull
    @Override
    public CompetitorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {  //responsible for inflating the view

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_competitors,parent,false); // almost standard,recycling the view holders and place them in the right position
        CompetitorsViewHolder holder = new CompetitorsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CompetitorsViewHolder holder, final int position) {//what you want your layouts look like
        Log.d(TAG,"onBindViewHolder:called.");//knowing which item failed
        //gets called every time a new item is added(6 brands = print 6 times)

        holder.bindFirstCont(list_first_contender.get(position));
        holder.bindSecCont(list_second_contender.get(position));
        buttonState = false;
        holder.first_comp_name.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String fContenderPos= list_first_contender.get(position);

             winner_contender.add(fContenderPos);
             //if(buttonState==false){
                Log.d(TAG, "onClick: %IF STATEMENT IS TRUE");
                Toast.makeText(list_context,"Clicked : " + fContenderPos,Toast.LENGTH_SHORT).show();
                buttonState = true;
                holder.first_comp_name.setTextColor(Color.BLUE);
                if((winner_contender.size()-extraPlayers.size()) == list_first_contender.size())
                {

                    Intent intent = new Intent(list_context,Tournament2Activity.class);
                    intent.putExtra("winnerList", winner_contender);
                    list_context.startActivity(intent);

                //}
             }
            }
        });
        holder.sec_comp_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sContenderPos= list_second_contender.get(position);
                winner_contender.add(sContenderPos);
               // if(buttonState==false) {
                    Toast.makeText(list_context, "Clicked : " + sContenderPos, Toast.LENGTH_SHORT).show();
                    buttonState = true;
                    holder.sec_comp_name.setTextColor(Color.BLUE);
                    if ((winner_contender.size()-extraPlayers.size()) == list_first_contender.size()) //- the extra player
                    {

                        Intent intent = new Intent(list_context, Tournament2Activity.class);
                        intent.putExtra("winnerList", winner_contender);
                        list_context.startActivity(intent);

                   // }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
         return list_first_contender.size();
    }


    public class CompetitorsViewHolder extends RecyclerView.ViewHolder{//holds widgets in memory of each individual entry

        TextView versus;
        RelativeLayout parentLayout;
        TextView first_comp_name;
        TextView sec_comp_name;


        public CompetitorsViewHolder(@NonNull View itemView) {
            super(itemView);

            first_comp_name= itemView.findViewById(R.id.first_comp_name);
            sec_comp_name= itemView.findViewById(R.id.sec_comp_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }

        public void bindFirstCont(String contender) {
            first_comp_name.setText(contender);
        }
        public void bindSecCont(String contender) {
            sec_comp_name.setText(contender);
        }

    }

}