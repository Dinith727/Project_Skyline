package com.example.task_it;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class EmployeeRating extends AppCompatActivity {
    RatingBar mRatingBar;
    TextView mRatingScale;
    EditText mFeedback;
    Button mSendFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_rating);

        mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
        mRatingScale = (TextView) findViewById(R.id.tvRatingScale);
        mFeedback = (EditText) findViewById(R.id.etFeedback);
        mSendFeedback = (Button) findViewById(R.id.btnSubmit);

        job jb = new job();

        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingScale.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        mRatingScale.setText("Very bad");
                        jb.setRating(1);
                        break;
                    case 2:
                        mRatingScale.setText("Need some improvement");
                        jb.setRating(2);
                        break;
                    case 3:
                        mRatingScale.setText("Good");
                        jb.setRating(3);
                        break;
                    case 4:
                        mRatingScale.setText("Great");
                        jb.setRating(4);
                        break;
                    case 5:
                        mRatingScale.setText("Awesome. I love it");
                        jb.setRating(5);
                        break;
                    default:
                        mRatingScale.setText(null);
                }
            }
        });

        mSendFeedback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (mFeedback.getText().toString().isEmpty()) {
                    Toast.makeText(EmployeeRating.this, "Please fill in feedback text box", Toast.LENGTH_LONG).show();
                }
                else if(mRatingScale.getText().toString().isEmpty()){

                    Toast.makeText(EmployeeRating.this, "please rate our service", Toast.LENGTH_SHORT).show();
                }
                else {

                    //mFeedback.setText("");
                   String f =  mFeedback.getText().toString();
                    jb.setFeedback(" ");

                    //mRatingBar.setRating(0);
                    DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Job");

                    dbref.child("job1").child("rating").setValue(jb.getRating());
                    dbref.child("job1").child("feedback").setValue(f);
                    Toast.makeText(EmployeeRating.this, "Thank you for sharing your feedback", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}