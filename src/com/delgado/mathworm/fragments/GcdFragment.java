package com.delgado.mathworm.fragments;

import com.delgado.mathworm.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GcdFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	    Bundle savedInstanceState) {
	View v = inflater.inflate(R.layout.frag_gcd, container, false);
	return v;
    }
    
    // TODO: implement button functions
}
