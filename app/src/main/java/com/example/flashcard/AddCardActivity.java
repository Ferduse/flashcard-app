package com.example.flashcard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        findViewById(R.id.cancelBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.saveBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String card_q = ((EditText) findViewById(R.id.questionPlace)).getText().toString();
                String card_a = ((EditText) findViewById(R.id.answerPlace)).getText().toString();


                Intent data = new Intent(AddCardActivity.this, MainActivity.class);
                data.putExtra("question", card_q);
                data.putExtra("answer", card_a);
                setResult(RESULT_OK, data);
                finish();
            }

        });

        String s1 = getIntent().getStringExtra("question");
        String s2 = getIntent().getStringExtra("answer");



    }
}

