package com.example.carrentalnew;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;


public class time_fragment extends Fragment {

    EditText date,time1,time2;
    int year,month,day,hr,min;
    String userDate;
    LocalTime t1,t2;
    Button book;
    int charge;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_time_fragment, container, false);
        date = view.findViewById(R.id.date_format);
        time1 = view.findViewById(R.id.time_format1);
        time2 = view.findViewById(R.id.time_format2);
        book = view.findViewById(R.id.Book);
        Calendar calendar = Calendar.getInstance();
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String Month;
                        if(month==0)
                            Month = "Jan";
                        else if(month==1)
                            Month = "Feb";
                        else if(month==2)
                            Month = "March";
                        else if(month==3)
                            Month = "April";
                        else if(month==4)
                            Month = "May";
                        else if(month==5)
                            Month = "June";
                        else if(month==6)
                            Month = "July";
                        else if(month==7)
                            Month = "Aug";
                        else if(month==8)
                            Month = "Sept";
                        else if(month==9)
                            Month = "Oct";
                        else if(month==10)
                            Month = "Nov";
                        else
                            Month = "Dec";

                        date.setText(String.valueOf(day) + " " +Month + " " + String.valueOf(year));
                        userDate = String.valueOf(year) + "-" +String.valueOf(month+1) + "-" + String.valueOf(day);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        time1.setOnClickListener(new View.OnClickListener() {
            LocalTime currentTime = LocalTime.now();

            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        hr = hour;
                        min = minute;
                        time1.setText(String.valueOf(hour) + ":" + String.valueOf(minute));
                        t1 = LocalTime.of(hr,min);
                    }
                },hr,min,true);
                timePickerDialog.show();
            }
        });

        time2.setOnClickListener(new View.OnClickListener() {
            LocalTime currentTime = LocalTime.now();


            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        hr = hour;
                        min = minute;
                        time2.setText(String.valueOf(hour) + ":" + String.valueOf(minute));
                        t2 = LocalTime.of(hr,min);
                    }
                },hr,min,true);
                timePickerDialog.show();
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date currentDate = calendar.getTime();

                // Parse user inputted date
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date parsedUserDate;
                try {
                    parsedUserDate = sdf.parse(userDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return; // Handle parsing error
                }

                // Compare the date
                if (sdf.format(parsedUserDate).equals(sdf.format(currentDate))||parsedUserDate.after(currentDate)) {

                    LocalTime currentTime = LocalTime.now();

                    // Compare the times
                    if (t1.isAfter(currentTime)||t1.equals(currentTime)) {
                        // User inputted time is in the future
                        Toast.makeText(getContext(), "User inputted time is in the future", Toast.LENGTH_SHORT).show();
                        diff();

                    } else {
                        // User inputted time is in the past
                        Toast.makeText(getContext(), "You can't select this time", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // User inputted date is in the past
                    Toast.makeText(getContext(), "You can't select this date", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void diff() {
        Duration duration = Duration.between(t1, t2);

        if (getActivity() != null) {
            Intent intent = getActivity().getIntent();
            if (intent != null && intent.hasExtra("charge")) {
                charge = intent.getIntExtra("charge", 0);
                // Use the charge value as needed
            }
        }

        String name = null;
        if (getActivity() != null) {
            Intent intent = getActivity().getIntent();
            if (intent != null && intent.hasExtra("name")) {
                name = intent.getStringExtra("name");
                // Use the charge value as needed
            }
        }

        int mobile=0;
        if (getActivity() != null) {
            Intent intent1 = getActivity().getIntent();
            if (intent1 != null && intent1.hasExtra("mobile")) {
                mobile = intent1.getIntExtra("mobile",0);
                // Use the charge value as needed
            }
        }
        // Get the difference in hours and minutes
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        String t = String.valueOf(hours) + ":" + String.valueOf(minutes);
        Intent intent = new Intent(getContext(), calculate_amt_time.class);
        intent.putExtra("mobile",mobile);
        intent.putExtra("diff", t);
        intent.putExtra("charge", charge);
        intent.putExtra("name", name);
        intent.putExtra("Days", -1);
        startActivity(intent);
    }
}