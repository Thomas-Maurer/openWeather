package com.example.thomasmaurer.app2;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.thomasmaurer.app2.com.exemple.CityJson.api.Response;

import java.util.ArrayList;

/**
 * Created by Thomas on 18/02/2015.
 */
public class MyFragmentCityDetail extends Fragment {

    private ListView listJour;
    ArrayAdapter<Response> adapter;
    private ArrayList<Response> meteoDays = new ArrayList<>();
    private TextView mTextViewName;
    private TextView mTextViewCode;
    private TextView mTextViewWind;
    private TextView mTextViewCountry;
    private TextView mTextViewTemp;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.myfragment_detail, container, false);
    }

    public MyFragmentCityDetail() {
    }

    public static MyFragmentCityDetail newInstance(City city) {
        MyFragmentCityDetail fragment = new MyFragmentCityDetail();
        Bundle bundle = new Bundle();
        bundle.putParcelable("city", city);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listJour = (ListView) view.findViewById(R.id.listJours);
        mTextViewName = (TextView) view.findViewById(R.id.nameCity);
        mTextViewCode = (TextView) view.findViewById(R.id.codeCity);
        mTextViewCountry = (TextView) view.findViewById(R.id.countryCity);
        mTextViewTemp = (TextView) view.findViewById(R.id.tempCity);
        mTextViewWind = (TextView) view.findViewById(R.id.windCity);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        City city = getArguments().getParcelable("city");
        mTextViewName.setText(city.name);
        mTextViewCountry.setText(city.country);
        mTextViewTemp.setText(String.valueOf(city.temperature));
        mTextViewCode.setText(String.valueOf(city.CityCode));
        mTextViewWind.setText(String.valueOf(city.windSpeed));
        //adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, meteoDays);
        //listJour.setAdapter(adapter);
    }
}
