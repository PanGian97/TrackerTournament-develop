package com.example.tournament;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TournamentActivity extends AppCompatActivity {


    private static final String TAG = "TournamentActivity";
    ArrayList<String> playerList = new ArrayList<>();
    ArrayList<String> first_cont_list = new ArrayList<>();
    ArrayList<String> sec_cont_list = new ArrayList<>();
    ArrayList<String> winnerList = new ArrayList<>();
    String toNextRound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament);

        loadPlayers();
        Collections.shuffle(playerList);
        ListSplitter();





    }
    public void ListSplitter() {
      if(playerList.size()%2==0){

        int middle = playerList.size() / 2;
         first_cont_list = new ArrayList<>(playerList.subList(0, middle));
         sec_cont_list = new ArrayList<>(playerList.subList(middle, playerList.size()));
          CompetitorsAdapter();
    }

    else{
          Log.d(TAG, "ListSplitter: %Cant be divided!");
          toNextRound = playerList.get(playerList.size()-1);//
          int middle = playerList.size() / 2;
          first_cont_list = new ArrayList<>(playerList.subList(0, middle));
          sec_cont_list = new ArrayList<>(playerList.subList(middle, playerList.size()));
          CompetitorsAdapter2();


      }
    }

    public void CompetitorsAdapter(){

        RecyclerView recyclerView = findViewById(R.id.recycle_view_vs);
        CompetitorsAdapter adapter = new CompetitorsAdapter(this,first_cont_list,sec_cont_list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();}
    public void CompetitorsAdapter2(){

        RecyclerView recyclerView = findViewById(R.id.recycle_view_vs);
        CompetitorsAdapter adapter = new CompetitorsAdapter(this,first_cont_list,sec_cont_list,toNextRound);//overload
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();}

    public void loadPlayers(){
        if(playerList == null){
            playerList = new ArrayList<>();
        }
        SharedPreferences sharefPreflist = getSharedPreferences("all_names",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharefPreflist.getString("player_list",null);
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        playerList = gson.fromJson(json,type);
    }

    }


