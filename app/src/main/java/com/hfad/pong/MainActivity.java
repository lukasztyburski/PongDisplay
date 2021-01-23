package com.hfad.pong;


import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import yuku.ambilwarna.AmbilWarnaDialog;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private int backgroundColor;
    private int textColor;
    private TextView clock;
    private TimePickerDialog.OnTimeSetListener timeSetListener;
    private Spinner backgroundSpinner;
    private EditText displayTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setNumberFilter();
        setSpinner();
        setClock();
    }

    public void selectBackgroundColor(View view) {
        openDialogBackground(false);
    }

    public void selectTextColor(View view) {
        openDialogText(false);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        //TODO zmiana celu do wysyłania

        Toast.makeText(parent.getContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void openDialogText(boolean supportAlpha) {
        AmbilWarnaDialog dialog = new AmbilWarnaDialog(this, textColor, supportAlpha, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                textColor = color;
                Button backgroundColorButton = (Button) findViewById(R.id.text_color_picker_button);
                backgroundColorButton.setBackgroundColor(textColor);
            }
        });

        dialog.show();
    }

    private void openDialogBackground(boolean supportAlpha) {
        AmbilWarnaDialog dialog = new AmbilWarnaDialog(this, backgroundColor, supportAlpha, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                backgroundColor = color;
                Button button = (Button) findViewById(R.id.background_color_picker_button);
                button.setBackgroundColor(backgroundColor);
            }
        });
        dialog.show();
    }

    private void setClock() {
        clock = (TextView) findViewById(R.id.clock);
        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog dialog = new TimePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Dialog,
                        timeSetListener, hourOfDay, minute, true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time = hourOfDay + ":" + minute;
                clock.setText(time);
            }
        };
    }

    private void setSpinner() {
        String[] arraySpinner = new String[]{"Wybrany kolor", "Tęcza"};
        backgroundSpinner = (Spinner) findViewById(R.id.background_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        backgroundSpinner.setAdapter(adapter);
        backgroundSpinner.setOnItemSelectedListener(this);
    }

    private void setNumberFilter() {
        displayTime = (EditText) findViewById(R.id.display_time_edit_text);
        displayTime.setFilters(new InputFilter[]{new InputNumberFIlter("1", "300")});
    }


}