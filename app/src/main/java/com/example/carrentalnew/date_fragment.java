package com.example.carrentalnew;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class date_fragment extends Fragment {

    EditText date1,date2;
    int year,month,day,charge;
    String userDate1,userDate2;

    Button book;
    @SuppressLint("MissingInflatedId")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_date_fragment, container, false);

        date1 = view.findViewById(R.id.date_format1);
        date2 = view.findViewById(R.id.date_format2);
        book = view.findViewById(R.id.Book_date);
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = calendar1.get(Calendar.YEAR);
                month = calendar1.get(Calendar.MONTH);
                day = calendar1.get(Calendar.DAY_OF_MONTH);
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

                        date1.setText(String.valueOf(day) + " " +Month + " " + String.valueOf(year));
                        userDate1 = String.valueOf(year) + "-" +String.valueOf(month+1) + "-" + String.valueOf(day);
                        calendar1.set(year,month, day);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = calendar2.get(Calendar.YEAR);
                month = calendar2.get(Calendar.MONTH);
                day = calendar2.get(Calendar.DAY_OF_MONTH);
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

                        date2.setText(String.valueOf(day) + " " +Month + " " + String.valueOf(year));
                        calendar2.set(year, month, day);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Date date1 = calendar1.getTime();
                Date date2 = calendar2.getTime();

                // Calculate the difference in milliseconds
                int differenceInMillis = (int) (date2.getTime() - date1.getTime());

                // Convert the difference to days
                int differenceInDays = differenceInMillis / (24 * 60 * 60 * 1000);

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


                Intent intent = new Intent(getActivity(), calculate_amt_date.class);
                intent.putExtra("Days", differenceInDays);
                intent.putExtra("mobile",mobile);
                intent.putExtra("diff", -1);
                intent.putExtra("name", name);
                intent.putExtra("charge", charge);
                startActivity(intent);
            }
        });
        return view;
    }
}