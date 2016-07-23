package com.virgulilla.microg.traveler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.latitude_origin)
    EditText etLatitudeOrigin;
    @BindView(R.id.longitude_origin)
    EditText etLongitudeOrigin;
    @BindView(R.id.wander_around)
    CheckBox cbWanderAround;
    @BindView(R.id.wandering_max_radius)
    EditText etWanderingMaxRadius;

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

        updateFields();
    }

    @OnClick(R.id.update_location)
    public void submit(View view) {
        double lat = Double.parseDouble(etLatitudeOrigin.getText().toString());
        double lon = Double.parseDouble(etLongitudeOrigin.getText().toString());
        prefs.setOriginLocation(lat, lon);

        prefs.setWanderAround(cbWanderAround.isChecked());
        prefs.setWanderingMaxRadius(Double.parseDouble(etWanderingMaxRadius.getText().toString()));

        updateFields();
    }

    @OnCheckedChanged(R.id.wander_around)
    public void saveWanderAroundPreference(CompoundButton buttonView, boolean isChecked) {
        prefs.setWanderAround(isChecked);
        updateFields();
    }

    private void updateFields() {
        double[] origin_location = prefs.getOriginLocation();
        etLatitudeOrigin.setText(String.valueOf(origin_location[0]));
        etLongitudeOrigin.setText(String.valueOf(origin_location[1]));

        cbWanderAround.setChecked(prefs.isWanderingAroundEnabled());
        etWanderingMaxRadius.setEnabled(cbWanderAround.isChecked());
        etWanderingMaxRadius.setText(String.valueOf(prefs.getWanderingMaxRadius()));
    }
}
