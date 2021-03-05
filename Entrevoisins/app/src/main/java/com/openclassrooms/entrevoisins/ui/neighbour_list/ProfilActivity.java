package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;


import static com.openclassrooms.entrevoisins.model.Neighbour.NEIGHBOUR_KEY;

public class ProfilActivity extends AppCompatActivity {

    private FloatingActionButton mFab;
    private Neighbour neighbour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        neighbour = getCurrentNeighbour();
        initViews();
        updateStarColor(neighbour.isFavorite());

        mFab.setOnClickListener(v -> {
            //Change the value of Favorite at each click on the item
            neighbour.setFavorite(!neighbour.isFavorite());
            // Set the right color depending on the value of Favorite
            updateStarColor(neighbour.isFavorite());
        });

    }

    private void updateStarColor(boolean isFavorite) {
        if (isFavorite) {
            mFab.getDrawable().mutate().setTint(getResources().getColor(R.color.colorFavorite));
        } else {
            mFab.getDrawable().mutate().setTint(getResources().getColor(R.color.colorPrimary));
        }
    }


    private void initViews() {
        //Set default action bar
        setSupportActionBar(findViewById(R.id.toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mFab = findViewById(R.id.profil_activity_fab);
        ((TextView) findViewById(R.id.profil_activity_first_name)).setText(neighbour.getName());
        ((TextView) findViewById(R.id.profil_activity_localisation)).setText(neighbour.getAddress());
        ((TextView) findViewById(R.id.profil_activity_phone_number)).setText(neighbour.getPhoneNumber());
        ((TextView) findViewById(R.id.profil_activity_details_profil)).setText(neighbour.getAboutMe());
        Glide.with(this)
                .load(neighbour.getAvatarUrl())
                .into((ImageView) findViewById(R.id.profil_activity_toolbarImage));

        // Set the name in the toolbar (exemple Caroline)
        ((CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar)).setTitle(neighbour.getName());
    }

    private Neighbour getCurrentNeighbour() {
        // get back the id from MyNeighbourRecyclerViewAdapter
        long id = getIntent().getLongExtra(NEIGHBOUR_KEY, 0);
        // get the list from dependency injection class DI
        NeighbourApiService apiService = DI.getNeighbourApiService();
        // Find the right neighbour in the list and give the value to neighbour attribute
        return apiService.getNeighbourById(id);
    }

    // Allow to come back to display home
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}