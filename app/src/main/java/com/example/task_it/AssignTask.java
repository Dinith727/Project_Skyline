package com.example.task_it;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AssignTask extends AppCompatActivity {

    TextView count1;
    EditText description;
    String desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.assigntask);
        description = findViewById(R.id.desc);
        count1 = findViewById(R.id.count);
        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = (description.length()) ;
                String convert = String.valueOf(length) ;
                count1.setText(convert);

                if(length == 100){
                    description.setEnabled(false);
                    Toast.makeText(getApplicationContext(), "Character limit exceeded", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    public void back(View v) {
        Intent intent = new Intent (AssignTask.this, carpProfile.class);
        startActivity(intent);
    }

    public void assign(View v) {
        desc = description.getText().toString().trim();
        String s = getIntent().getStringExtra("tel");
        Intent intent = new Intent (AssignTask.this, EmpJob.class);
        intent.putExtra("desc", desc);
        intent.putExtra("tel", s);
        startActivity(intent);

    }
}