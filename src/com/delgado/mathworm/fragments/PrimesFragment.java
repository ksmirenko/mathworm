package com.delgado.mathworm.fragments;

import java.util.List;

import com.delgado.math.MathLogic;
import com.delgado.mathworm.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PrimesFragment extends Fragment {
    private EditText inputN;
    private TextView output1, output2;
    private final int MAX_NUMBER = (int) (1e6);
    private final int MAX_UPPER_BOUND = (int) (1e4);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	    Bundle savedInstanceState) {
	LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.frag_primes,
		container, false);

	inputN = (EditText) ll.findViewById(R.id.frag_primes_editTextN);
	output1 = (TextView) ll.findViewById(R.id.frag_primes_textViewAns1);
	output2 = (TextView) ll.findViewById(R.id.frag_primes_textViewAns2);
	((Button) ll.findViewById(R.id.frag_primes_buttonIsPrime))
		.setOnClickListener(ocl_IsPrime);
	((Button) ll.findViewById(R.id.frag_primes_buttonFactorise))
		.setOnClickListener(ocl_Factorise);
	((Button) ll.findViewById(R.id.frag_primes_buttonPrimesList))
		.setOnClickListener(ocl_PrimesList);

	return ll;
    }

    private OnClickListener ocl_IsPrime = new OnClickListener() {
	@Override
	public void onClick(View arg0) {
	    try {
		String sN = inputN.getText().toString();
		int n = Integer.parseInt(sN);
		if (n > MAX_NUMBER) {
		    output1.setText(R.string.ans_errorNumberTooBig);
		    return;
		}
		if (MathLogic.INSTANCE.isPrime(n))
		    output1.setText(R.string.ans_isPrime);
		else
		    output1.setText(R.string.ans_isNotPrime);
	    } catch (NumberFormatException e) {
		output1.setText(R.string.ans_errorNumberFormat);
	    } catch (Exception e) {
		Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG)
			.show();
	    }
	}
    };

    private OnClickListener ocl_Factorise = new OnClickListener() {
	@Override
	public void onClick(View arg0) {
	    try {
		String sN = inputN.getText().toString();
		long n = Long.parseLong(sN);
		if (n > MAX_NUMBER) {
		    output1.setText(R.string.ans_errorNumberTooBig);
		    return;
		}
		output1.setText(MathLogic.INSTANCE.factoriseToString(n));
	    } catch (NumberFormatException e) {
		output1.setText(R.string.ans_errorNumberFormat);
	    } catch (Exception e) {
		Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG)
			.show();
	    }
	}
    };

    private OnClickListener ocl_PrimesList = new OnClickListener() {
	@Override
	public void onClick(View arg0) {
	    try {
		String sN = inputN.getText().toString();
		int n = Integer.parseInt(sN);
		if (n > MAX_UPPER_BOUND) {
		    output2.setText(R.string.ans_errorNumberTooBig);
		    return;
		}
		List<Integer> primesList = MathLogic.INSTANCE.getPrimesList();
		output2.setText("");
		for (int pr : primesList) {
		    if (pr > n)
			break;
		    output2.setText(output2.getText() + String.valueOf(pr)
			    + " ");
		}
	    } catch (NumberFormatException e) {
		output2.setText(R.string.ans_errorNumberFormat);
	    } catch (Exception e) {
		Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG)
			.show();
	    }
	}
    };
}