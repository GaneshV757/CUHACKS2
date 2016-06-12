package com.example.amit.cuhacks3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private TextView introduction;
    private EditText destination_location;
    private String destinationLocationString;
    private String distanceEnteredString;
    private Button submitButton;
    private EditText distanceEntered;
    private String transportMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        spinner = (Spinner)findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener(){

                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (view.toString()){
                            case "Car":
                                transportMode = "Car";
                                break;
                            case "Bike":
                                transportMode = "Bike";
                                break;
                            case "Walk":
                                transportMode = "Walk";
                                break;
                            default:
                                transportMode = null;
                                break;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {}
                });
        final Bundle bundle = new Bundle();

        introduction = (TextView)findViewById(R.id.introduction);
        //Typeface font = new Typeface(getAssets(), "@res/");
        //introduction.setTypeface(font);

        destination_location = (EditText)findViewById(R.id.destination);
        destinationLocationString = destination_location.getText().toString();

        distanceEntered = (EditText)findViewById(R.id.distance);
        distanceEnteredString = distanceEntered.getText().toString();

        submitButton = (Button)findViewById(R.id.button);
        submitButton.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(destinationLocationString != null ||
                                distanceEnteredString != null) {
                            System.out.println("Hi");
                            if(destinationLocationString == null) {
                                Intent i = new Intent();
                                bundle.putString("Transport", distanceEnteredString);
                                i.putExtras(bundle);
                                startActivity(i);
                            } else if(distanceEnteredString == null) {
                                Intent i = new Intent();
                                bundle.putString("Destination", destinationLocationString);
                                i.putExtras(bundle);
                                startActivity(i);
                            } else {
                                new AlertDialog.Builder(getApplicationContext())
                                        .setTitle("WARNING!")
                                        .setMessage("You cannot type in both text fields!!")
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {}
                                        })
                                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener(){

                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {}
                                        })
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();

                            }
                        }
                    }
                }
        );
    }



}
