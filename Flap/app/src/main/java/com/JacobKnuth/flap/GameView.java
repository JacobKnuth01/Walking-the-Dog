package com.JacobKnuth.flap;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.JacobKnuth.flap.databinding.FileBinding;
import com.JacobKnuth.flap.databinding.GameBinding;

public class GameView extends AppCompatActivity {
    gamePlay game;
    GameBinding xml;
    @Override

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        xml = GameBinding.inflate(getLayoutInflater());
        //game = new gamePlay(this);
        game.start();
        setContentView(game);


    }
    public void back(){
        game.end();
        setContentView(xml.getRoot());

        //startActivity(new Intent(this, MainActivity.class));
        //this.finish();




    }
}
