package com.example.fragments;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ParadoxListFragment.OnParadoxSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paradoxes);
        ButterKnife.bind(this);
        FrameLayout container = (FrameLayout) findViewById(R.id.fragment_container);

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                getSupportActionBar().setDisplayHomeAsUpEnabled(getSupportFragmentManager().getBackStackEntryCount() > 0);
            }
        });

        if (container != null) {
            if (savedInstanceState == null) {
                ParadoxListFragment fragment = ParadoxListFragment.newInstance();
                fragment.setArguments(getIntent().getExtras());
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        getSupportFragmentManager().popBackStack();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onParadoxSelected(int index) {
        ParadoxDescriptionFragment fragment = (ParadoxDescriptionFragment) getSupportFragmentManager().findFragmentById(R.id.paradoxDescriptionFragment);

        if (fragment != null) {
            fragment.updateParadoxDescription(index);
        } else {
            ParadoxDescriptionFragment descriptionFragment = ParadoxDescriptionFragment.newInstance();
            Bundle args = new Bundle();
            args.putInt(ParadoxDescriptionFragment.PARADOXINDEX, index);
            descriptionFragment.setArguments(args);
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                    .replace(R.id.fragment_container, descriptionFragment, "description")
                    .addToBackStack(null).commit();
        }
    }
}
