package com.example.marsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.curiosity_button)
    public void buttonCuriosityClicked() {
        roverDescTextView.setText(getString(R.string.mars_curiosity));
        roverTitleTextView.setText("Curiosity");
    }

    @OnClick(R.id.spirit_button)
    public void buttonSpiritClicked() {
        roverDescTextView.setText(getString(R.string.mars_spirit));
        roverTitleTextView.setText("Spirit");
    }

    @OnClick(R.id.opportunity_button)
    public void buttonOnpportunityClicked() {
        roverDescTextView.setText(getString(R.string.mars_opportunity));
        roverTitleTextView.setText("Opportunity");
    }

}
