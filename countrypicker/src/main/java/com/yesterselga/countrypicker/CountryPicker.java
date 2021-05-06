package com.yesterselga.countrypicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.fragment.app.DialogFragment;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import com.yesterselga.countrypicker.R.dimen;
import com.yesterselga.countrypicker.R.id;
import com.yesterselga.countrypicker.R.layout;

public class CountryPicker extends DialogFragment {

    EditText searchEditText;
    ListView countryListView;
    CountryAdapter adapter;
    List<Country> countriesList = new ArrayList<>();
    List<Country> selectedCountriesList = new ArrayList<>();
    CountryPickerListener listener;
    Context context;
    private static Theme pickerTheme;

    public static CountryPicker newInstance(String dialogTitle, Theme theme) {
        CountryPicker picker = new CountryPicker();
        Bundle bundle = new Bundle();
        bundle.putString("dialogTitle", dialogTitle);
        pickerTheme = theme;
        picker.setArguments(bundle);
        return picker;
    }

    public CountryPicker() {
        this.setCountriesList(Country.getAllCountries());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(layout.country_picker, (ViewGroup)null);
        Bundle args = this.getArguments();
        if(args != null) {
            String dialogTitle = args.getString("dialogTitle");
            this.getDialog().setTitle(dialogTitle);
            int width = this.getResources().getDimensionPixelSize(dimen.cp_dialog_width);
            int height = this.getResources().getDimensionPixelSize(dimen.cp_dialog_height);
            this.getDialog().getWindow().setLayout(width, height);
        }

        if(pickerTheme.equals(Theme.DARK)){
            view = inflater.inflate(layout.country_picker_dark, (ViewGroup)null);
        }

        this.searchEditText = view.findViewById(id.country_code_picker_search);
        this.countryListView = view.findViewById(id.country_code_picker_listview);
        this.selectedCountriesList = new ArrayList(this.countriesList.size());
        this.selectedCountriesList.addAll(this.countriesList);
        this.adapter = new CountryAdapter(this.getActivity(), this.selectedCountriesList, pickerTheme);
        this.countryListView.setAdapter(this.adapter);
        this.countryListView.setOnItemClickListener((parent, view1, position, id) -> {
            if(CountryPicker.this.listener != null) {
                Country country = CountryPicker.this.selectedCountriesList.get(position);
                CountryPicker.this.listener.onSelectCountry(country.getName(), country.getCode(), country.getDialCode(), country.getFlag());
            }

        });
        this.searchEditText.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
                CountryPicker.this.search(s.toString());
            }
        });
        return view;
    }

    public void setListener(CountryPickerListener listener) {
        this.listener = listener;
    }

    @SuppressLint({"DefaultLocale"})
    private void search(String text) {
        this.selectedCountriesList.clear();

        for (Country country : this.countriesList) {
            if (country.getName().toLowerCase(Locale.ENGLISH).contains(text.toLowerCase())) {
                this.selectedCountriesList.add(country);
            }
        }

        this.adapter.notifyDataSetChanged();
    }

    public void setCountriesList(List<Country> newCountries) {
        this.countriesList.clear();
        this.countriesList.addAll(newCountries);
    }

}
