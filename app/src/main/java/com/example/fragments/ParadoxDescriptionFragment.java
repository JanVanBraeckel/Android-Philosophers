package com.example.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ParadoxDescriptionFragment extends android.support.v4.app.Fragment {
    private int currentIndex = -1;

    @Bind(R.id.paradoxDescription)
    public TextView description;

    public static String PARADOXINDEX = "PARADOXINDEX";

    public ParadoxDescriptionFragment() {
    }

    public static ParadoxDescriptionFragment newInstance() {
        ParadoxDescriptionFragment fragment = new ParadoxDescriptionFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_paradox_description, container, false);
        ButterKnife.bind(this, layout);

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(PARADOXINDEX);
        }

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        return layout;
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle argsBundle = getArguments();
        if (argsBundle != null) {
            int id = argsBundle.getInt(PARADOXINDEX);
            currentIndex = id;
            updateParadoxDescription(currentIndex);
        } else if (currentIndex != -1) {
            updateParadoxDescription(currentIndex);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (currentIndex != -1) {
            outState.putInt(PARADOXINDEX, currentIndex);
        }
    }

    public void updateParadoxDescription(int i) {
        description.setText(Paradoxes.ParadoxDescription[i]);
    }
}
