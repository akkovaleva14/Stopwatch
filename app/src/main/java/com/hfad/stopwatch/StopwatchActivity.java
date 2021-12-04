package com.hfad.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import java.util.Locale;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

public class StopwatchActivity extends Activity {
    //Number of seconds displayed on the stopwatch.
    private int seconds = 0;
    //Is the stopwatch running?
    private boolean running;
    /* int seconds AND boolean running: Use the seconds and running variables to record the number of
    seconds passed and whether the stopwatch is running. */
    private boolean wasRunning;
    TextView cycleFIRST;
    TextView cycleSECOND;
    TextView cycleTHIRD;
    TextView cycleFOURTH;
    TextView cycleFIFTH;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        cycleFIRST = findViewById(R.id.time_view_cycle1);
        cycleSECOND = findViewById(R.id.time_view_cycle2);
        cycleTHIRD = findViewById(R.id.time_view_cycle3);
        cycleFOURTH = findViewById(R.id.time_view_cycle4);
        cycleFIFTH = findViewById(R.id.time_view_cycle5);
        runTimer();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

    // If the activity’s paused, stop the stopwatch.
    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    // If the activity’s resumed, start the stopwatch again if it was running previously.
    @Override
    protected void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    //Start the stopwatch running when the Start button is clicked.
    public void onClickStart(View view) { // This gets called when the Start button is clicked.
        running = true; // Start the stopwatch.
    }

    //Stop the stopwatch running when the Stop button is clicked.
    public void onClickStop(View view) { // This gets called when the Stop button is clicked.
        running = false; // Stop the stopwatch.
    }

    //Reset the stopwatch when the Reset button is clicked.
    public void onClickReset(View view) { // This gets called when the Reset button is clicked
        // Stop the stopwatch and set the seconds to 0:
        counter++;
        setEveryCycle(seconds - 1, counter);
        running = false;
        seconds = 0;
    }

    public void setEveryCycle(int secundi, int counterAR) {
        int hours = secundi / 3600;
        int minutes = (secundi % 3600) / 60;
        int secs = secundi % 60;
        String cycle = getResources().getString(R.string.cycle);
        String format = String.format(cycle, counterAR, hours, minutes, secs);
        switch (counterAR) {
            case (1):
                cycleFIRST.setText(format);
                break;
            case (2):
                cycleSECOND.setText(format);
                break;
            case (3):
                cycleTHIRD.setText(format);
                break;
            case (4):
                cycleFOURTH.setText(format);
                break;
            case (5):
                cycleFIFTH.setText(format);
                break;
            default:
                Toast.makeText(this, "Happy end", Toast.LENGTH_LONG).show();
                break;
        }
    }

/* The runTimer() method will run code every second to check whether the stopwatch
is running, and, if it is, increment the number of seconds and display the number
of seconds in the text view.
To help us with this, we’ll use two private variables to record the state
of the stopwatch. We’ll use an int called seconds to track how many seconds
have passed since the stopwatch started running, and a boolean called running
to record whether the stopwatch is currently running. */

    //Sets the number of seconds on the timer.
    private void runTimer() {
        final TextView timeView = (TextView) findViewById(R.id.time_view); // Get the text view
        final Handler handler = new Handler();
        handler.post(new Runnable() { // Use a Handler to post code.
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, secs); //Format the seconds into hours, minutes, and seconds
                timeView.setText(time); // Set the text view text.

                if (running) {
                    seconds++; // If running is true, increment the seconds variable
                }
                handler.postDelayed(this, 1000); // Post the code again with a delay of 1 second.
            }
        });
    }
}




