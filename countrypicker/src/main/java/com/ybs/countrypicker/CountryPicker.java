package com.ybs.countrypicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import com.ybs.countrypicker.Country;
import com.ybs.countrypicker.CountryAdapter;
import com.ybs.countrypicker.CountryPickerListener;
import com.ybs.countrypicker.R.dimen;
import com.ybs.countrypicker.R.id;
import com.ybs.countrypicker.R.layout;

/**
 * Created by mispc1 on 8/29/17.
 */

public class CountryPicker extends DialogFragment {

    private EditText searchEditText;
    private ListView countryListView;
    private CountryAdapter adapter;
    private List<Country> countriesList = new ArrayList();
    private List<Country> selectedCountriesList = new ArrayList();
    private CountryPickerListener listener;
    private Context context;

    public static CountryPicker newInstance(String dialogTitle) {
        CountryPicker picker = new CountryPicker();
        Bundle bundle = new Bundle();
        bundle.putString("dialogTitle", dialogTitle);
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

        this.searchEditText = (EditText)view.findViewById(id.country_code_picker_search);
        this.countryListView = (ListView)view.findViewById(id.country_code_picker_listview);
        this.selectedCountriesList = new ArrayList(this.countriesList.size());
        this.selectedCountriesList.addAll(this.countriesList);
        this.adapter = new CountryAdapter(this.getActivity(), this.selectedCountriesList);
        this.countryListView.setAdapter(this.adapter);
        this.countryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(CountryPicker.this.listener != null) {
                    Country country = (Country)CountryPicker.this.selectedCountriesList.get(position);
                    CountryPicker.this.listener.onSelectCountry(country.getName(), country.getCode(), country.getDialCode(), country.getFlag());
                }

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
        Iterator var2 = this.countriesList.iterator();

        while(var2.hasNext()) {
            Country country = (Country)var2.next();
            if(country.getName().toLowerCase(Locale.ENGLISH).contains(text.toLowerCase())) {
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
