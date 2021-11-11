package com.JacobKnuth.flap;

import android.os.Bundle;
import android.view.View;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.JacobKnuth.flap.databinding.FileBinding;

public class score extends AppCompatActivity {

    FileBinding b;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = FileBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.buttond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("CLIKCED");
            }
        });
    }
}
