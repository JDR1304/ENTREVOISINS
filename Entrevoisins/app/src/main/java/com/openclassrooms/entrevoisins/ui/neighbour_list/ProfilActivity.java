package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import static com.openclassrooms.entrevoisins.model.Neighbour.NEIGHBOUR_KEY;

public class ProfilActivity extends AppCompatActivity {

    private ImageView mImage;
    private TextView mFirstName;
    private TextView mPhoneNumber;
    private TextView mLocalisation;
    private TextView mDetailsProfil;
    private FloatingActionButton mFab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        mImage = findViewById(R.id.profil_activity_toolbarImage);
        mFirstName = findViewById(R.id.profil_activity_first_name);
        mLocalisation = findViewById(R.id.profil_activity_localisation);
        mPhoneNumber = findViewById(R.id.profil_activity_phone_number);
        mDetailsProfil =findViewById(R.id.profil_activity_details_profil);
        mFab = findViewById(R.id.profil_activity_fab);

        //Pour mettre le bouton de retour par defaut android
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Neighbour neighbour = intent.getParcelableExtra(NEIGHBOUR_KEY);
        mFirstName.setText(neighbour.getName());
        mLocalisation.setText(neighbour.getAddress());
        mPhoneNumber.setText(neighbour.getPhoneNumber());
        mDetailsProfil.setText(neighbour.getAboutMe());
        Glide.with(mImage.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(mImage);


    }

    // Permet de revenir sur mon display home
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}