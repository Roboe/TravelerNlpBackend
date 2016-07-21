package com.virgulilla.microg.traveler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.latitude_origin)
    EditText etLatitudeOrigin;
    @BindView(R.id.longitude_origin)
    EditText etLongitudeOrigin;

    private LocationPrefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        prefs = LocationPrefs.getInstance(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();

        fillFields(prefs.getOriginLocation());
    }

    @OnClick(R.id.update_location)
    public void submit(View view) {
        double lat = Double.parseDouble(etLatitudeOrigin.getText().toString());
        double lon = Double.parseDouble(etLongitudeOrigin.getText().toString());

        prefs.setOriginLocation(lat, lon);

        fillFields(prefs.getOriginLocation());
    }

    private void fillFields(double[] coordinates) {
        etLatitudeOrigin.setText(Double.toString(coordinates[0]));
        etLongitudeOrigin.setText(Double.toString(coordinates[1]));
    }
}
