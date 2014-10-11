package com.delgado.mathworm.fragments;

import com.delgado.mathworm.R;
import com.delgado.math.MathLogic;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MiscFragment extends Fragment {
    private EditText inputN, inputM;
    private TextView output1;
    private EditText inputA, inputB, inputC;
    private TextView output2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	    Bundle savedInstanceState) {
	LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.frag_misc,
		container, false);

	inputN = (EditText) ll.findViewById(R.id.frag_misc_editTextN);
	inputM = (EditText) ll.findViewById(R.id.frag_misc_editTextM);
	output1 = (TextView) ll.findViewById(R.id.frag_misc_textViewAns1);
	((Button) ll.findViewById(R.id.frag_misc_buttonFact))
		.setOnClickListener(ocl_Fact);
	((Button) ll.findViewById(R.id.frag_misc_buttonChoose))
		.setOnClickListener(ocl_Choose);

	inputA = (EditText) ll.findViewById(R.id.frag_misc_editTextA);
	inputB = (EditText) ll.findViewById(R.id.frag_misc_editTextB);
	inputC = (EditText) ll.findViewById(R.id.frag_misc_editTextC);
	output2 = (TextView) ll.findViewById(R.id.frag_misc_textViewAns2);
	((Button) ll.findViewById(R.id.frag_misc_buttonTriple))
		.setOnClickListener(ocl_Triple);
	((Button) ll.findViewById(R.id.frag_misc_buttonConvert))
		.setOnClickListener(ocl_Convert);

	return ll;
    }

    private OnClickListener ocl_Fact = new OnClickListener() {
	@Override
	public void onClick(View v) {
	    try {
		String sN = inputN.getText().toString();
		long n = Long.parseLong(sN);
		output1.setText(String.valueOf(MathLogic.factorial(n)));
	    } catch (Exception e) {
		output1.setText(R.string.ans_error);
	    }
	}
    };

    private OnClickListener ocl_Choose = new OnClickListener() {
	@Override
	public void onClick(View v) {
	    try {
		String sN = inputN.getText().toString(), sM = inputM.getText()
			.toString();
		long n = Long.parseLong(sN), m = Long.parseLong(sM);
		output1.setText(String.valueOf(MathLogic.choose(n, m)));
	    } catch (Exception e) {
		output1.setText(R.string.ans_error);
	    }
	}
    };

    private OnClickListener ocl_Triple = new OnClickListener() {
	@Override
	public void onClick(View v) {
	    try {
		long a = Long.parseLong(inputA.getText().toString());
		long b = Long.parseLong(inputB.getText().toString());
		long c = Long.parseLong(inputC.getText().toString());
		if (MathLogic.checkPythagoreanTriple(a, b, c))
		    output2.setText(R.string.ans_isTriple);
		else
		    output2.setText(R.string.ans_isNotTriple);
	    } catch (Exception e) {
		output2.setText(R.string.ans_error);
	    }
	}
    };

    private OnClickListener ocl_Convert = new OnClickListener() {
	@Override
	public void onClick(View v) {
	    try {
		String a = inputA.getText().toString();
		int b = Integer.parseInt(inputB.getText().toString());
		int c = Integer.parseInt(inputC.getText().toString());
		output2.setText(MathLogic.convertNumber(a, b, c));
	    } catch (Exception e) {
		output2.setText(R.string.ans_error);
	    }
	}
    };

    // TODO: implement button functions
}
