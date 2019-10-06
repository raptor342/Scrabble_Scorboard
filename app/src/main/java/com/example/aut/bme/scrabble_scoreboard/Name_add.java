package com.example.aut.bme.scrabble_scoreboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Name_add extends AppCompatActivity {
    private int player_max_count; //Only for Scrabble rules
    private Button add_name;
    private Button next;
    private EditText name;
    private LinearLayout namesLayout;
    private ArrayList<String> names;
    private LayoutInflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_add);
        inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        add_name = findViewById(R.id.add_player);
        name = findViewById(R.id.player_name);
        namesLayout = findViewById(R.id.List_of_names);
        names = new ArrayList<>();
        next = findViewById(R.id.next);
        next.setEnabled(false);
        add_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked_NameButton(view);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked_NextButton();
            }
        });
    }
    private void clicked_NameButton(View view)
    {
        if(name.getText().toString().isEmpty())
        {
            Snackbar.make(view,"Hiba: üres mező",Snackbar.LENGTH_LONG).show();
            return;
        }
        else if(player_max_count >= 4) //Scrabble
        {
            Snackbar.make(view,"Hiba: Túl sok játékos lenne",Snackbar.LENGTH_LONG).show();
        }
        else {
            player_max_count++; //for Scrabble rules only
            names.add(name.getText().toString());
            View rowItem = inflater.inflate(R.layout.list_of_names, null);
            TextView rowItemText = rowItem.findViewById(R.id.id_Player_name);
            TextView rowItemNumber = rowItem.findViewById(R.id.id_Player_id);
            rowItemNumber.setText(player_max_count+".   ");
            rowItemText.setText(name.getText().toString());
            namesLayout.addView(rowItem);
            name.setText(null);
            next.setEnabled(true);
        }
    }
    private void clicked_NextButton()
    {
        Intent gameActivity = new Intent(this,Scrabble_Gameboard.class);
        gameActivity.putExtra("player_names",names);
        startActivity(gameActivity);
    }
}
