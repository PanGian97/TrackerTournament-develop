package com.example.tournament;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

public class PlayersActivity extends AppCompatActivity implements View.OnClickListener {
    Button viewPlayerButton;
    private static final String TAG = "PlayersActivity";
   SharedPreferences savedPlayers;
    EditText playerEdit;
    Button subPlayerButton;
    Button startButton;
    TextView playerView;
    ArrayList<String> playerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        playerEdit = (EditText) findViewById(R.id.playerEdit);

        subPlayerButton = (Button) findViewById(R.id.subPlayerButton);
        viewPlayerButton = (Button) findViewById(R.id.viewPlayersButton);
        startButton = (Button)findViewById(R.id.startButton);
        viewPlayerButton.setOnClickListener(this);
        subPlayerButton.setOnClickListener(this);
        startButton.setOnClickListener(this);


    }

//    public void initPlayers(){
//
//        for(int i=1; i<11; i++){
//            playerList.add("Player"+i);
//        }
//    }
public void initPlayers(){

      String playerUsername = playerEdit.getText().toString();
      if(playerUsername.matches(""))
      {playerList.add("Unknown");}
      else{
        playerList.add(playerUsername);}
        savePlayers();

}

//    public void getPlayerName(){
//        savedPlayers = getSharedPreferences("playerId", Context.MODE_PRIVATE);
//
//        String username = savedPlayers.getString("first","Default");
//
//        playerView.setText("Last added:"+username);
//    }
    public void savePlayers() {
        SharedPreferences sharedPreflist = getSharedPreferences("all_names", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreflist.edit();
        Gson gson = new Gson();
        String json = gson.toJson(playerList);//converts list to json format
        editor.putString("player_list", json);
        editor.apply();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.subPlayerButton:initPlayers();break;

            case R.id.viewPlayersButton:

                Intent intent = new Intent(this,AllPlayersActivity.class);
                intent.putExtra("competList", playerList);
                startActivity(intent);
                break;
            case R.id.startButton:

                Intent intentt = new Intent(this,TournamentActivity.class);
                intentt.putExtra("competList", playerList);
                startActivity(intentt);break;


        }

    }

//



}
