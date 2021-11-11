package com.JacobKnuth.flap;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.JacobKnuth.flap.databinding.SettBinding;

public class Settings extends AppCompatActivity {
    SettBinding binder;
    @Override

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binder = SettBinding.inflate(getLayoutInflater());
        setContentView(binder.getRoot());


    }
}
