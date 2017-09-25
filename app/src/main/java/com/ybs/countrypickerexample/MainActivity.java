package com.ybs.countrypickerexample;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.ybs.countrypicker.CountryPicker;
import com.ybs.countrypicker.CountryPickerListener;

public class MainActivity extends AppCompatActivity {

    CountryPicker picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        picker = CountryPicker.newInstance("Select Country");  // dialog title
        picker.setListener(new CountryPickerListener() {
            @Override
            public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {

                EditText countryCode = (EditText)findViewById(R.id.countryCode);
                EditText countryName = (EditText)findViewById(R.id.countryName);
                EditText countryDialCode = (EditText)findViewById(R.id.countryDialCode);
                ImageView countryIcon = (ImageView)findViewById(R.id.countryIcon);

                countryCode.setText(code);
                countryName.setText(name);
                countryDialCode.setText(dialCode);
                countryIcon.setImageResource(flagDrawableResID);

                picker.dismiss();

            }
        });



    }

    public void openPicker(View view){
        picker.show(getSupportFragmentManager(), "COUNTRY_PICKER");
    }

}
