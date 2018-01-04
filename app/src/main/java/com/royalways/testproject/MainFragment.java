package com.royalways.testproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Intel on 04-01-2018.
 */

public class MainFragment extends Fragment {

    private TextView textView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.main_fragment, container, false);

        String value = getArguments().getString("val");

        textView = (TextView) v.findViewById(R.id.txt_fragment_name);

        textView.setText(value);
        return v;
    }
}
