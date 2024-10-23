package com.example.baitapmrhuy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button button;
    CheckBox cb;
    EditText user_edit;
    TextView age_num;
    SeekBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        component_map();

        pb.setMax(100);
        SharedPreferences sp = getSharedPreferences(getString(R.string.my_shared_pref), MODE_PRIVATE);

        user_edit.setText(sp.getString(getString(R.string.saved_user_name), ""));
        age_num.setText(sp.getString(getString(R.string.saved_user_age), "0"));
        cb.setChecked(sp.getBoolean(getString(R.string.saved_check), false));
        pb.setProgress(Integer.parseInt(age_num.getText().toString()));
        pb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                age_num.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                String username = user_edit.getText().toString();
                String age = age_num.getText().toString();
                if(cb.isChecked()){
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString(getString(R.string.saved_user_name), username);
                    editor.putString(getString(R.string.saved_user_age), age);
                    editor.putBoolean(getString(R.string.saved_check), true);
                    editor.apply();
                }else{
                    SharedPreferences.Editor editor = sp.edit();
                    editor.remove(getString(R.string.saved_user_name));
                    editor.remove(getString(R.string.saved_user_age));
                    editor.remove(getString(R.string.saved_check));
                    editor.apply();
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void component_map() {
        button = findViewById(R.id.button);
        cb = findViewById(R.id.cB_save);
        user_edit = findViewById(R.id.edittext);
        age_num = findViewById(R.id.textview2);
        pb = findViewById(R.id.progressBar);
    }
}