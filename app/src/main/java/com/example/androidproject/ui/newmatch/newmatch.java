package com.example.androidproject.ui.newmatch;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidproject.R;
import com.example.androidproject.ui.CurrentMatchActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class newmatch extends Fragment implements LocationListener, AdapterView.OnItemSelectedListener{

    private NewmatchViewModel mViewModel;
    private TextView addressField;
    private TextView debugField;
    private Button buttonRun;
    private EditText nom_1;
    private EditText nom_2;
    private  LinearLayout LB;
    private LocationManager locationManager;
    private String provider;
    private String tab[] = {"","","",""};


    public static newmatch newInstance() {
        return new newmatch();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.newmatch_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(NewmatchViewModel.class);

        //get the spinner from the xml.
        Spinner dropdown = getView().findViewById(R.id.spinner1);
        //create a list of items for the spinner.
        String[] items = new String[]{getString(R.string.choice_categ_1), getString(R.string.choice_categ_2), getString(R.string.choice_categ_3)};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);


        addressField = (TextView) getView().findViewById(R.id.TextView05);

        // Get the location manager
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the location provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},200);//MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION

            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);
        //latitudeField.setText("Location not available");
        // Initialize the location fields
        if (location != null) {
            Log.d("Main Java", " provider : " +provider);
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        } else {
            addressField.setText("Location not available");
        }

        buttonRun = getView().findViewById(R.id.Button01);
        buttonRun.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myClickHandler(v);
            }
        });
    }
        // TODO: Use the ViewModel




    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                tab[2] = getString(R.string.choice_categ_1);
                break;
            case 1:
                tab[2] = getString(R.string.choice_categ_2);
                break;
            case 2:
                tab[2] = getString(R.string.choice_categ_3);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //tab[0] = getString(R.string.choice_categ_1);
    }



    /* Request updates at startup */
    @Override
    public void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},200);//MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
            return;
        }

        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        locationManager.requestLocationUpdates(provider, 400, 1, this);
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            Log.d("Main Java", " provider : " +provider);
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        } else {
            addressField.setText("Location not available");
        }
    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    public void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {

        Geocoder geocoder= new Geocoder(getContext(), Locale.getDefault());
        List<Address> address = null; // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        try {
            address = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (address != null) {
            addressField.setText(address.get(0).getAddressLine(0));
            tab[3] = address.get(0).getAddressLine(0);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(getContext(), "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(getContext(), "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 200: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

        }
    }


    public void myClickHandler(View view) {
        String tagg = String.valueOf(view.getTag());

        if (tagg.equals("run")) {

            nom_1 = (EditText) getView().findViewById(R.id.EditText01);
            nom_2 = (EditText) getView().findViewById(R.id.EditText02);

            tab[0] = String.valueOf(nom_1.getText());
            tab[1] = String.valueOf(nom_2.getText());


            Intent myIntent = new Intent(getContext(), CurrentMatchActivity.class);
            myIntent.putExtra("infos", tab);
            startActivity(myIntent);
        }

    }

}
