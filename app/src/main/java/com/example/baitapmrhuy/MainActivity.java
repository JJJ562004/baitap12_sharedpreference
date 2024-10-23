package com.example.baitapmrhuy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            Button button = findViewById(R.id.button);
            CheckBox cb = findViewById(R.id.cB_save);
            EditText user_edit = findViewById(R.id.edittext);
            ProgressBar pb = findViewById(R.id.progressBar);

            pb.setMax(100);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Use getSharedPreferences with a custom name for your preferences
                    SharedPreferences sharedPref = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();

                    // Assuming the user input from the EditText is the new high score
                    int newHighScore = Integer.parseInt(user_edit.getText().toString());

                    // Save the high score
                    editor.putInt(getString(R.string.saved_high_score_key), newHighScore);
                    editor.apply();
                }
            });

            return insets;
        });
    }
}
