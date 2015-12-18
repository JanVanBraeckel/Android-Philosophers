package com.example.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ParadoxListFragment extends android.support.v4.app.Fragment {
    private OnParadoxSelectedListener paradoxSelectedListener;

    @Bind(R.id.listViewParadoxes)
    ListView listViewParadoxes;

    public ParadoxListFragment() {}

    public static ParadoxListFragment newInstance(){
        ParadoxListFragment fragment = new ParadoxListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout =  inflater.inflate(R.layout.fragment_paradox_list, container, false);
        ButterKnife.bind(this, layout);

        int id = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ? android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;
        listViewParadoxes.setAdapter(new ArrayAdapter<>(getActivity(), id, Paradoxes.ParadoxNames));

        listViewParadoxes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                paradoxSelectedListener.onParadoxSelected(position);
            }
        });
        return layout;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            paradoxSelectedListener = (OnParadoxSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnParadoxSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        paradoxSelectedListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public interface OnParadoxSelectedListener {
        void onParadoxSelected(int index);
    }
}
