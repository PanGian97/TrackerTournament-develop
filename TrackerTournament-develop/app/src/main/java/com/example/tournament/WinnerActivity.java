package com.example.tournament;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class WinnerActivity extends AppCompatActivity {
    private static final String TAG = "WinnerActivity";
    ArrayList<String> finalWinnerList = new ArrayList<>();
    TextView winnerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        winnerName = findViewById(R.id.winner_text);
        getIncomingIntent();

    }
    public void getIncomingIntent() {
        if(getIntent().hasExtra("finalWinnerList"))
        {
            Log.d(TAG, "getIncomingIntent: found the extras");
            finalWinnerList = (ArrayList<String>) getIntent().getSerializableExtra("finalWinnerList");
            winnerName.setText(finalWinnerList.get(0));
        }
    }
}
