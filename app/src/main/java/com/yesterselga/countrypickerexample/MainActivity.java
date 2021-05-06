package com.yesterselga.countrypickerexample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.yesterselga.countrypicker.CountryPicker;
import com.yesterselga.countrypicker.CountryPickerListener;
import com.yesterselga.countrypicker.Theme;

public class MainActivity extends AppCompatActivity {

    CountryPicker picker;
    Button buttonOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonOpen = findViewById(R.id.buttonOpen);
        picker = CountryPicker.newInstance("Select Country", Theme.DARK);  // Set Dialog Title and Theme
        picker.setListener((name, code, dialCode, flagDrawableResID) -> {

            EditText countryCode = (EditText)findViewById(R.id.countryCode);
            EditText countryName = (EditText)findViewById(R.id.countryName);
            EditText countryDialCode = (EditText)findViewById(R.id.countryDialCode);
            ImageView countryIcon = (ImageView)findViewById(R.id.countryIcon);

            countryCode.setText(code);
            countryName.setText(name);
            countryDialCode.setText(dialCode);
            countryIcon.setImageResource(flagDrawableResID);

            picker.dismiss();

        });

        buttonOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picker.show(getSupportFragmentManager(), "COUNTRY_PICKER");
            }
        });
    }

}
