package com.example.thomasmaurer.app2;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thomasmaurer.app2.com.exemple.CityJson.api.Response;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Thomas on 18/02/2015.
 */
public class MyFragmentCity extends Fragment{

    private cityListener listener;
    private Context mContext;
    ListView listCity;
    EditText searchInput;
    ArrayAdapter<City> adapter;
    private ArrayList<City> myCities = new ArrayList<>();

    public void setListener(cityListener listener) {
        this.listener = listener;
    }


    public MyFragmentCity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.myfragment, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle
            savedInstanceState) {
        listCity = (ListView) view.findViewById(R.id.listView);
        mContext = this.getActivity().getApplicationContext();



        listCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listener != null){
                    listener.onCityClick(adapter.getItem(position));
                }
            }
        });

        searchInput = (EditText) view.findViewById(R.id.searchCity);
        searchInput.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            if (!event.isShiftPressed()) {
                                // the user is done typing.

                                getMessages();
                            }
                        }
                        return false; // pass on to other listeners.
                    }
                });

    }

    private SpiceManager mSpiceManager = new SpiceManager(APISpiceService.class);

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void onStart() {
        mSpiceManager.start(getActivity());
        super.onStart();
    }
    public void onStop() {
        mSpiceManager.shouldStop();
        super.onStop();
    }

    public void getMessages() {
        Log.v("EditTextbeforerequest", searchInput.getText().toString());
        MessageRequest request = new MessageRequest();
        String nomVille = searchInput.getText().toString();
        if( nomVille.length() > 3 ){
            request.changeCityName(nomVille);
            request.changeContext(this.getActivity().getApplicationContext());

        }else {
            Toast.makeText(mContext, "Aucune information téléchargée",
                    Toast.LENGTH_LONG).show();
            return;
        }
        mSpiceManager.execute(request,
                "message_cache",
                DurationInMillis.ALWAYS_EXPIRED,new RequestListener<Response>() {
                    @Override
                    public void onRequestFailure(SpiceException spiceException) {
                        Log.v("request", "Fail");
                        Toast.makeText(mContext, "Aucune information téléchargée",
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onRequestSuccess(Response rResponse) {
                        if (rResponse.name != null && rResponse.sys.country != null) {
                            City TempCity = new City(rResponse.name,rResponse.sys.country,rResponse.id,rResponse.main.temp,rResponse.windspeed.speed);
                            Log.v("TempCity", TempCity.toString());
                            myCities.add(TempCity);
                            Log.v("ListCity", myCities.toString());

                            adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, myCities);
                            listCity.setAdapter(adapter);
                        }
                        else {
                            Toast.makeText(mContext, "Aucune information téléchargée",
                                    Toast.LENGTH_LONG).show();
                            Log.v("Request", "No city (null element)");
                        }

                    }
                });

    }
}
