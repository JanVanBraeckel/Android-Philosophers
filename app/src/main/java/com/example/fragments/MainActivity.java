package com.example.fragments;

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
        if (container != null) {
            if (savedInstanceState != null) {
                return;
            } else {
                ParadoxListFragment fragment = ParadoxListFragment.newInstance();
                fragment.setArguments(getIntent().getExtras());
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();
            }
        }
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
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, descriptionFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
