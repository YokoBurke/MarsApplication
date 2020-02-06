package com.example.marsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.curiosity_button)
    MaterialButton buttonCuriosity;

    @BindView(R.id.opportunity_button)
    MaterialButton buttonOpportunity;

    @BindView(R.id.spirit_button)
    MaterialButton buttonSpirit;

    @BindView(R.id.text_rover_description)
    TextView roverDescTextView;

    @BindView(R.id.text_rover_title)
    TextView roverTitleTextView;

    @BindView(R.id.button_go)
    FloatingActionButton fabGo;

    String roverName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roverName = getString(R.string.curiosity);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.curiosity_button)
    public void buttonCuriosityClicked() {
        roverName = getString(R.string.curiosity);
        roverDescTextView.setText(getResources().getString(R.string.mars_curiosity));
        roverTitleTextView.setText(roverName);
    }

    @OnClick(R.id.spirit_button)
    public void buttonSpiritClicked() {
        roverName = getString(R.string.spirit);
        roverDescTextView.setText(getResources().getString(R.string.mars_spirit));
        roverTitleTextView.setText(roverName);
    }

    @OnClick(R.id.opportunity_button)
    public void buttonOnpportunityClicked() {
        roverName = getString(R.string.opportunity);
        roverDescTextView.setText(getResources().getString(R.string.mars_opportunity));
        roverTitleTextView.setText(roverName);
    }

    @OnClick(R.id.button_go)
    public void buttonFabGoClicked() {

        Intent intent = new Intent(this, RoverActivity.class);
        intent.putExtra("rover_id", roverName);
        startActivity(intent);


    }

}
