package com.journaldev.vrStreetView;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends Activity  {
    Button addressButton;
    TextView addressTV;
    TextView latLongTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        addressTV = (TextView) findViewById(R.id.addressTV);
        latLongTV = (TextView) findViewById(R.id.latLongTV);

        GeocoderHandler g1 = new GeocoderHandler();
        String myString = g1.locationAddress;

        final GeocodingLocation g2 = new GeocodingLocation();
//        final double lt= g2.location[0];
        //      final double lng= g2.location[1];

        final double lt = g2.a;
        final double lng = g2.b;


        addressButton = (Button) findViewById(R.id.addressButton);
        addressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                EditText editText = (EditText) findViewById(R.id.addressET);
                String address = editText.getText().toString();

                GeocodingLocation locationAddress = new GeocodingLocation();
                locationAddress.getAddressFromLocation(address,
                        getApplicationContext(), new GeocoderHandler());

                //double value= (double) GeocodingLocation.getWidth();

                //Toast.makeText(getApplicationContext(),"Latitude is : "+GeocodingLocation.a+" Longitude is : "+GeocodingLocation.b,Toast.LENGTH_LONG).show();

                double latitudeValue = GeocodingLocation.a;
                double longitudeValue = GeocodingLocation.b;

                Toast.makeText(getApplicationContext(), " correct Lat: " + latitudeValue + " correct Lng: " + longitudeValue, Toast.LENGTH_LONG).show();


                Intent intent = new Intent(MainActivity.this, tourActivity.class);
                intent.putExtra("lat", latitudeValue);
                intent.putExtra("long", longitudeValue);


                startActivity(intent);
                //GeocoderHandler gg= new GeocoderHandler();
                //String ggg= gg.locationAddress;
                //Toast.makeText(getApplicationContext() ,"Latitude is : "+lng,Toast.LENGTH_LONG).show();


            }
        });

        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
    }


    public class GeocoderHandler extends Handler {
        String locationAddress;
        @Override
        public void handleMessage(Message message) {
            //String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
                GeocodingLocation abc= new GeocodingLocation();
            String mm=abc.result;
            //latLongTV.setText(locationAddress);
            //Toast.makeText(getApplicationContext(),"Latitude is : "+locationAddress,Toast.LENGTH_LONG).show();


        }
    }
}