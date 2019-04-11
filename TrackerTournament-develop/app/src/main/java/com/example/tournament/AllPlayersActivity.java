package com.example.tournament;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AllPlayersActivity extends AppCompatActivity {


    private static final String TAG = "AllPlayersActivity";
    ArrayList<String> playerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_players);


       playerList  =(ArrayList<String>) getIntent().getSerializableExtra("competlist");
       loadPlayers();
    }
    public void getIncomingIntent(){
        if(getIntent().hasExtra("del_player_name")){
            Log.d(TAG, "getIncomingIntent: %Deleted player found!");
            String delPlayer = getIntent().getStringExtra("del_player_name");
            deletePlayer(delPlayer);
        }
        else{
            Log.d(TAG, "getIncomingIntent: %Nothing found");}

    }
    public void deletePlayer(String delPlayer){
        SharedPreferences sharedPreflist = getSharedPreferences("all_names",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreflist.edit();
        Gson gson = new Gson();
        String json = gson.toJson(playerList);//converts list to json format
        editor.remove(delPlayer);
        editor.apply();
        PlayerAdapter();}


    public void PlayerAdapter(){
        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        PlayersAdapter adapter = new PlayersAdapter(this,playerList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();
//
    }
    public void loadPlayers(){
        if(playerList == null){
            playerList = new ArrayList<>();
        }
        SharedPreferences sharefPreflist = getSharedPreferences("all_names",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharefPreflist.getString("player_list",null);
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        playerList = gson.fromJson(json,type);
        getIncomingIntent();
        PlayerAdapter();


    }
}
