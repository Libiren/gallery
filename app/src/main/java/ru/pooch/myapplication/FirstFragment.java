package ru.pooch.myapplication;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FirstFragment extends Fragment implements FragmentChangeListener {

    public static final String TAG = "FirstFragment";
    FragmentChangeListener listener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_start, container, false);
Button btn_start = (Button) view.findViewById(R.id.button_start);
        Button btn_photo = (Button) view.findViewById(R.id.button_photo);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnClickFragments();

            }
        });
        btn_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnClickImageFragment();

            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentChangeListener) {
            listener = (FragmentChangeListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "must implement OnFragmentChangeListener");

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


    @Override
    public void OnClickFragments() {

    }

    @Override
    public void OnClickImageFragment() {

    }
}
