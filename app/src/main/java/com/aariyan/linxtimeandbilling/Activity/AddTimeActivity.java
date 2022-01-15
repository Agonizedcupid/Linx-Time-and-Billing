package com.aariyan.linxtimeandbilling.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.aariyan.linxtimeandbilling.R;

import java.util.Calendar;

public class AddTimeActivity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private Calendar calendar;
    int day, month, year;
    String date = "";

    int hour, time;
    private static String amPm = "";

    private String startTimeDateStr,startTimeClockStr,endTimeDateStr,endTimeClockStr;
    private ImageView startTimeDate, startTimeClock, endTimeDate, endTimeClock;
    private TextView totalTime, billableTime, startTimeText, endTimeText;
    private String spinnerSelection;
    private EditText descriptionOfWork;
    private Button saveBtn, ignoreBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_time);

        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        if (getIntent() != null) {
            setTitle(String.format("Hello %s", getIntent().getStringExtra("name")));
        }

        initUI();
    }

    private void initUI() {

        startTimeDate = findViewById(R.id.startTimeDate);
        startTimeClock = findViewById(R.id.startTimeClock);
        endTimeDate = findViewById(R.id.endTimeDate);
        endTimeClock = findViewById(R.id.endTimeClock);
        startTimeText = findViewById(R.id.startTimeText);
        endTimeText = findViewById(R.id.endTimeText);
        totalTime = findViewById(R.id.totalTime);
        billableTime = findViewById(R.id.billableTime);
        descriptionOfWork = findViewById(R.id.workDescription);

        startTimeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker("start");
            }
        });


        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.workType, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerSelection = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void showWatch(String clicked) {

        TimePickerDialog dialog = new TimePickerDialog(AddTimeActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                hour = i;
                time = i1;

//                if (hour >= 12) {
//                    amPm = "PM";
//                } else {
//                    amPm = "AM";
//                }

                Calendar calendar = Calendar.getInstance();
                calendar.set(0, 0, 0, hour, time);

                String time = DateFormat.format("hh:mm", calendar).toString();

                //String taskTime = time + " " + amPm;
                String taskTime = time;
                if (clicked.equals("start")) {
                    startTimeClockStr = time;
                } else {
                    endTimeClockStr = time;
                }

                Toast.makeText(AddTimeActivity.this, "You've selected " + taskTime, Toast.LENGTH_SHORT).show();
            }
        }, 24, 0, true);

        dialog.updateTime(hour, time);
        dialog.show();
    }

    private void showDatePicker(String clicked) {
        datePickerDialog = new DatePickerDialog(AddTimeActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                int j = i1 + 1;

                date = i + "-" + j + "-" + i2;
                //2022-1-15
                Log.d("TEST_DATE",date);
                if (clicked.equals("start")) {
                    startTimeDateStr = date;
                } else {
                    endTimeDateStr = date;
                }

                Toast.makeText(AddTimeActivity.this, "You've selected " + date, Toast.LENGTH_LONG).show();
                //Toast.makeText(AddProperty.this, "" + availableStatus, Toast.LENGTH_SHORT).show();

            }
        }, day, month, year);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        datePickerDialog.show();
    }
}