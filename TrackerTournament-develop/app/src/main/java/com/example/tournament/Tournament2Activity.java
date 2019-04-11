package com.example.tournament;

import android.content.Intent;
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

public class Tournament2Activity extends AppCompatActivity {


    private static final String TAG = "TournamentActivity";

    ArrayList<String> first_cont_list = new ArrayList<>();
    ArrayList<String> sec_cont_list = new ArrayList<>();
    ArrayList<String> winnerList = new ArrayList<>();
    ArrayList<String> finalWinnerList = new ArrayList<>();
    String toNextRound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament2);

        getIncomingIntent();
        Collections.shuffle(winnerList);
        ListSplitter();





    }

    public void getIncomingIntent() {
        if(getIntent().hasExtra("winnerList"))
        {
            Log.d(TAG, "getIncomingIntent: found the extras");
            winnerList = (ArrayList<String>) getIntent().getSerializableExtra("winnerList");
    }
    }
    public void ListSplitter() {
        if(winnerList.size()%2==0){

            int middle = winnerList.size() / 2;
            first_cont_list = new ArrayList<>(winnerList.subList(0, middle));
            sec_cont_list = new ArrayList<>(winnerList.subList(middle, winnerList.size()));
            CompetitorsAdapter();
        }else if(winnerList.size()==1){
            finalWinnerList.add(winnerList.get(0));
            Winner();

        } else{
            Log.d(TAG, "ListSplitter: %Cant be divided!");
            toNextRound = winnerList.get(winnerList.size()-1);//
            int middle = winnerList.size() / 2;
            first_cont_list = new ArrayList<>(winnerList.subList(0, middle));
            sec_cont_list = new ArrayList<>(winnerList.subList(middle, winnerList.size()));
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
    public void Winner(){
        Intent intent = new Intent(this,WinnerActivity.class);
        intent.putExtra("finalWinnerList", finalWinnerList);
        startActivity(intent);
    }
}


