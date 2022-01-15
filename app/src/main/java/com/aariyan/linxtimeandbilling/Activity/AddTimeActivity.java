package com.aariyan.linxtimeandbilling.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
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

public class AddTimeActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private DatePickerDialog datePickerDialog;
    private Calendar calendar;
    int day, month, year;
    String date = "";

    int hour, minute;
    private static String amPm = "";

    private String startTimeDateStr = "", startTimeClockStr = "", endTimeDateStr = "OPEN", endTimeClockStr = "";
    private ImageView startTimeDate, startTimeClock, endTimeDate, endTimeClock;
    private TextView totalTime, billableTime, startTimeText, endTimeText;
    private String spinnerSelection;
    private EditText descriptionOfWork;
    private Button saveBtn, ignoreBtn;

    private static String fDate,fTime,sDate,sTime;

    private static String userName = "", customerName = "";

    private String[] monthName = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    private TimePickerDialog timePickerDialog;
    private String clicked = "";

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

            userName = getIntent().getStringExtra("name");
            customerName = getIntent().getStringExtra("customerName");
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
        saveBtn = findViewById(R.id.saveTime);
        ignoreBtn = findViewById(R.id.ignoreTime);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        startTimeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker("start");
            }
        });
        startTimeClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showWatch("start");
            }
        });

        endTimeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(startTimeText.getText().toString())) {
//                    startTimeText.setError("Select the start time first!");
//                    startTimeText.requestFocus();

                    Toast.makeText(AddTimeActivity.this, "Select the start time first!", Toast.LENGTH_SHORT).show();

                    return;
                }
                showDatePicker("end");
            }
        });
        endTimeClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(startTimeText.getText().toString())) {
                    //startTimeText.setError("Select the start time first!");
                    //startTimeText.requestFocus();
                    Toast.makeText(AddTimeActivity.this, "Select the start time first!", Toast.LENGTH_SHORT).show();
                    return;
                }
                showWatch("end");
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

    private void showWatch(String clickeds) {

        clicked = clickeds;
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        timePickerDialog = new TimePickerDialog(this, this, hour, minute, true);
        timePickerDialog.show();

//        TimePickerDialog dialog = new TimePickerDialog(AddTimeActivity.this, new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker timePicker, int i, int i1) {
//                hour = i;
//                time = i1;
//
////                if (hour >= 12) {
////                    amPm = "PM";
////                } else {
////                    amPm = "AM";
////                }
//
//                Calendar calendar = Calendar.getInstance();
//                calendar.set(0, 0, 0, hour, time);
//
//                String time = DateFormat.format("hh:mm", calendar).toString();
//
//                //String taskTime = time + " " + amPm;
//                String taskTime = time;
//                if (clicked.equals("start")) {
//                    startTimeClockStr = time;
//                    startTimeText.setText(String.format("%s", startTimeDateStr + " " + startTimeClockStr));
//                } else {
//                    endTimeClockStr = time;
//                    if (endTimeDateStr.equals("OPEN")) {
//                        endTimeDateStr = "";
//                    }
//                    endTimeText.setText(String.format("%s", endTimeDateStr + " " + endTimeClockStr));
//                }
//
//                Toast.makeText(AddTimeActivity.this, "You've selected " + taskTime, Toast.LENGTH_SHORT).show();
//            }
//        //}, 24, 0, true);
//        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
//        //dialog.updateTime(hour, time);
//        dialog.show();
    }

    private void showDatePicker(String clicked) {
        datePickerDialog = new DatePickerDialog(AddTimeActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                int j = i1 + 1;

                //date = i + "-" + j + "-" + i2;
                date = i2 + " " + monthName[j - 1] + " " + i;
                fDate =i1+"/"+
                //2022-1-15
                Log.d("TEST_DATE", date);
                if (clicked.equals("start")) {
                    startTimeDateStr = date;
                    startTimeText.setText(String.format("%s", startTimeDateStr + " " + startTimeClockStr));
                } else {
                    endTimeDateStr = date;
                    if (endTimeDateStr.equals("OPEN")) {
                        endTimeDateStr = "";
                    }
                    endTimeText.setText(String.format("%s", endTimeDateStr + " " + endTimeClockStr));
                }

                Toast.makeText(AddTimeActivity.this, "You've selected " + date, Toast.LENGTH_LONG).show();
                //Toast.makeText(AddProperty.this, "" + availableStatus, Toast.LENGTH_SHORT).show();

            }
        }, day, month, year);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        datePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        String time = i + ":" + i1;
        if (clicked.equals("start")) {
            startTimeClockStr = time;
            startTimeText.setText(String.format("%s", startTimeDateStr + " " + startTimeClockStr));
        } else {
            endTimeClockStr = time;
            if (endTimeDateStr.equals("OPEN")) {
                endTimeDateStr = "";
            }
            calculateTotalTime();
            endTimeText.setText(String.format("%s", endTimeDateStr + " " + endTimeClockStr));
        }
    }

    private void calculateTotalTime() {
        String[] startTime = startTimeClockStr.split(":");
        String[] endTime = endTimeClockStr.split(":");

        int hour = Integer.parseInt(endTime[0]) - Integer.parseInt(startTime[0]);
        int minutes = Integer.parseInt(endTime[1]) - Integer.parseInt(startTime[1]);
        if (minute < 0) {
            minute = minutes * (-1);
        }

        int totalTimes = hour * 60 + minutes;
        totalTime.setText("" + totalTimes);
    }
}