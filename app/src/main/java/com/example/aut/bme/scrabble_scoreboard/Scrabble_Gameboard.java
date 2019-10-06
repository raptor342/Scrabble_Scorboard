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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Scrabble_Gameboard extends AppCompatActivity {

    private ArrayList<String> player_names;
    private Button point_button;
    private TextView round_number_text;
    private TextView player_name_text;
    private int counter;
    private int player_count;
    private EditText point;
    private TextView player1_name;
    private TextView player2_name;
    private TextView player3_name;
    private TextView player4_name;
    private TextView player1_point;
    private TextView player2_point;
    private TextView player3_point;
    private TextView player4_point;
    private Integer round_number;
    private Integer turn_number;
    private Integer player1_point_number;
    private Integer player2_point_number;
    private Integer player3_point_number;
    private Integer player4_point_number;
    private Integer actual_player1;
    private Integer actual_player2;
    private Integer actual_player3;
    private Integer actual_player4;
    private LayoutInflater inflater;
    private LinearLayout PlayerPointDinamically;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrabble__gameboard);
        initialize();
    }
    private void initialize()
    {
        PlayerPointDinamically = findViewById(R.id.player_points_dinamically);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        round_number_text = findViewById(R.id.round_number_text);
        player_name_text = findViewById(R.id.player_name_text);
        Intent this_intent = getIntent();
        player_names = this_intent.getStringArrayListExtra("player_names");
        player_count = player_names.size();
        player1_name = findViewById(R.id.player1_name);
        player2_name = findViewById(R.id.player2_name);
        player3_name = findViewById(R.id.player3_name);
        player4_name = findViewById(R.id.player4_name);
        player1_point = findViewById(R.id.player1_point);
        player2_point = findViewById(R.id.player2_point);
        player3_point = findViewById(R.id.player3_point);
        player4_point = findViewById(R.id.player4_point);
        player1_name.setText(player_names.get(0));
        if(player_names.size()>1)
        {
            player2_name.setText(player_names.get(1));
            if(player_names.size()>2)
            {
                player3_name.setText(player_names.get(2));
                if(player_names.size()>3)
                    player4_name.setText(player_names.get(3));
            }
        }
        turn_number = 1;
        round_number = 1;
        player1_point_number=0;
        player2_point_number=0;
        player3_point_number=0;
        player4_point_number=0;
        point = findViewById(R.id.actual_point);
        point_button = findViewById(R.id.add_point);
        point_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(point.getText().toString().isEmpty())
                {
                    Snackbar.make(view,"Hibás érték",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(turn_number == 1)
                {
                    actual_player1 = Integer.parseInt(point.getText().toString());
                    player1_point_number+=Integer.parseInt(point.getText().toString());
                    player1_point.setText(player1_point_number.toString());
                }
                if(turn_number == 2)
                {
                    actual_player2 = Integer.parseInt(point.getText().toString());
                    player2_point_number+=Integer.parseInt(point.getText().toString());
                    player2_point.setText(player2_point_number.toString());
                }
                if(turn_number == 3)
                {
                    actual_player3 = Integer.parseInt(point.getText().toString());
                    player3_point_number+=Integer.parseInt(point.getText().toString());
                    player3_point.setText(player3_point_number.toString());
                }
                if(turn_number == 4)
                {
                    actual_player4 = Integer.parseInt(point.getText().toString());
                    player4_point_number+=Integer.parseInt(point.getText().toString());
                    player4_point.setText(player4_point_number.toString());
                }
                if(turn_number>=player_names.size())
                {

                    View rowItem = inflater.inflate(R.layout.points,null);
                    TextView player1_point = rowItem.findViewById(R.id.first_player_points);
                    TextView rounder = rowItem.findViewById(R.id.rounder);
                    player1_point.setText(actual_player1.toString());
                    TextView player2_point = rowItem.findViewById(R.id.second_player_points);
                    TextView player3_point = rowItem.findViewById(R.id.third_player_points);
                    TextView player4_point = rowItem.findViewById(R.id.fourth_player_points);
                    player2_point.setText("");
                    player3_point.setText("");
                    player4_point.setText("");
                    if(player_count > 1)
                    {
                        player2_point.setText(actual_player2.toString());
                    }
                    if(player_count > 2)
                    {
                        player3_point.setText(actual_player3.toString());
                    }
                    if(player_count > 3)
                    {
                        player4_point.setText(actual_player4.toString());
                    }
                    Integer help = round_number;
                    rounder.setText(round_number.toString()+".");
                    PlayerPointDinamically.addView(rowItem);
                    turn_number = 1;
                    round_number++;
                }
                else
                {
                    turn_number++;
                }
                point.setText(null);
                turnTimerInit();

            }
        });
        turnTimerInit();
    }
    private void turnTimerInit()
    {
        round_number_text.setText(round_number.toString()+" .kör");
        player_name_text.setText(turn_number.toString()+"."+"   "+player_names.get(turn_number-1));
    }
}
