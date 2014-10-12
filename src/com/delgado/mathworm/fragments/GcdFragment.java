package com.delgado.mathworm.fragments;

import com.delgado.math.MathLogic;
import com.delgado.math.Pair;
import com.delgado.math.Triple;
import com.delgado.mathworm.R;

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
import android.widget.Toast;

public class GcdFragment extends Fragment {
    private EditText inputA1, inputB1, inputA2, inputB2, inputC2;
    private TextView output1, output2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	    Bundle savedInstanceState) {
	LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.frag_gcd,
		container, false);

	inputA1 = (EditText) ll.findViewById(R.id.frag_gcd_editTextA);
	inputB1 = (EditText) ll.findViewById(R.id.frag_gcd_editTextB);
	((Button) ll.findViewById(R.id.frag_gcd_buttonGcd))
		.setOnClickListener(ocl_Gcd);
	((Button) ll.findViewById(R.id.frag_gcd_buttonLcm))
		.setOnClickListener(ocl_Lcm);
	output1 = (TextView) ll.findViewById(R.id.frag_gcd_textViewAns1);

	inputA2 = (EditText) ll.findViewById(R.id.frag_gcd_editTextA2);
	inputB2 = (EditText) ll.findViewById(R.id.frag_gcd_editTextB2);
	inputC2 = (EditText) ll.findViewById(R.id.frag_gcd_editTextC2);
	((Button) ll.findViewById(R.id.frag_gcd_buttonDiophGcd))
		.setOnClickListener(ocl_DiophGcd);
	((Button) ll.findViewById(R.id.frag_gcd_buttonModuloDiv))
		.setOnClickListener(ocl_ModuloDiv);
	((Button) ll.findViewById(R.id.frag_gcd_buttonDiophOne))
		.setOnClickListener(ocl_DiophOne);
	((Button) ll.findViewById(R.id.frag_gcd_buttonDiophAll))
		.setOnClickListener(ocl_DiophAll);
	output2 = (TextView) ll.findViewById(R.id.frag_gcd_textViewAns2);

	return ll;
    }

    private OnClickListener ocl_Gcd = new OnClickListener() {
	@Override
	public void onClick(View arg0) {
	    try {
		String sA = inputA1.getText().toString();
		String sB = inputB1.getText().toString();
		long a = Long.parseLong(sA), b = Long.parseLong(sB);
		output1.setText(String.valueOf(MathLogic.gcd(a, b)));
	    } catch (NumberFormatException e) {
		output1.setText(R.string.ans_errorNumberFormat);
	    } catch (Exception e) {
		Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG)
			.show();
	    }
	}
    };

    private OnClickListener ocl_Lcm = new OnClickListener() {
	@Override
	public void onClick(View arg0) {
	    try {
		String sA = inputA1.getText().toString();
		String sB = inputB1.getText().toString();
		long a = Long.parseLong(sA), b = Long.parseLong(sB);
		output1.setText(String.valueOf(MathLogic.lcm(a, b)));
	    } catch (NumberFormatException e) {
		output1.setText(R.string.ans_errorNumberFormat);
	    } catch (Exception e) {
		Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG)
			.show();
	    }
	}
    };

    private OnClickListener ocl_DiophGcd = new OnClickListener() {
	@Override
	public void onClick(View arg0) {
	    try {
		String sA = inputA2.getText().toString();
		String sB = inputB2.getText().toString();
		long a = Long.parseLong(sA);
		long b = Long.parseLong(sB);
		Triple<Long, Long, Long> ans = MathLogic.egcd(a, b);
		output2.setText("x0 = " + ans.get1() + "\r\ny0 = " + ans.get2());
	    } catch (NumberFormatException e) {
		output2.setText(R.string.ans_errorNumberFormat);
	    } catch (Exception e) {
		Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG)
			.show();
	    }
	}
    };

    private OnClickListener ocl_ModuloDiv = new OnClickListener() {
	@Override
	public void onClick(View arg0) {
	    try {
		String sA = inputA2.getText().toString();
		String sB = inputB2.getText().toString();
		String sC = inputC2.getText().toString();
		long a = Long.parseLong(sA);
		long b = Long.parseLong(sB);
		long c = Long.parseLong(sC);
		long ans = MathLogic.solveCongruence(a, b, c);
		if (ans != (-c - 1))
		    output2.setText("x = " + ans);
		else
		    output2.setText(R.string.ans_noSolutions);
	    } catch (NumberFormatException e) {
		output2.setText(R.string.ans_errorNumberFormat);
	    } catch (Exception e) {
		Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG)
			.show();
	    }
	}
    };

    private OnClickListener ocl_DiophOne = new OnClickListener() {
	@Override
	public void onClick(View arg0) {
	    try {
		String sA = inputA2.getText().toString();
		String sB = inputB2.getText().toString();
		String sC = inputC2.getText().toString();
		long a = Long.parseLong(sA);
		long b = Long.parseLong(sB);
		long c = Long.parseLong(sC);
		Triple<Long, Long, Long> ans = MathLogic.solveDiophEqOne(a, b,
			c);
		output2.setText("x0 = " + ans.get1() + "\r\ny0 = " + ans.get2());
	    } catch (NumberFormatException e) {
		output2.setText(R.string.ans_errorNumberFormat);
	    } catch (Exception e) {
		Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG)
			.show();
	    }
	}
    };

    private OnClickListener ocl_DiophAll = new OnClickListener() {
	@Override
	public void onClick(View arg0) {
	    try {
		String sA = inputA2.getText().toString();
		String sB = inputB2.getText().toString();
		String sC = inputC2.getText().toString();
		long a = Long.parseLong(sA);
		long b = Long.parseLong(sB);
		long c = Long.parseLong(sC);
		Pair<String, String> ans = MathLogic.solveDiophEqAllString(a,
			b, c);
		output2.setText(ans.getL() + "\r\n" + ans.getR());
	    } catch (NumberFormatException e) {
		output2.setText(R.string.ans_errorNumberFormat);
	    } catch (Exception e) {
		Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG)
			.show();
	    }
	}
    };
}
