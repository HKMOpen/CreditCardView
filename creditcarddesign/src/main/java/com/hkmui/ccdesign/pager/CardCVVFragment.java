package com.hkmui.ccdesign.pager;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.hkmui.ccdesign.R;

import static com.hkmui.ccdesign.CreditCardUtils.EXTRA_CARD_CVV;
import static com.hkmui.ccdesign.CreditCardUtils.EXTRA_CARD_EXPIRY;

/**
 * Created by sharish on 9/1/15.
 */
public class CardCVVFragment extends CreditCardFragment  {

    private EditText mCardCVVView;

    public CardCVVFragment() {

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle state) {

        View v = inflater.inflate(R.layout.lyt_card_cvv, group,false);
        mCardCVVView = (EditText) v.findViewById(R.id.card_cvv);

        String cvv = "";
        if(getArguments() != null && getArguments().containsKey(EXTRA_CARD_CVV)) {
            cvv = getArguments().getString(EXTRA_CARD_CVV);
        }

        if(cvv == null) {
            cvv = "";
        }

        mCardCVVView.setText(cvv);
        mCardCVVView.addTextChangedListener(this);

        return v;
    }

    @Override
    public void afterTextChanged(Editable s) {

        onEdit(s.toString());
        if(s.length() == 3) {
            onComplete();
        }

    }

    @Override
    public void focus() {

        if(isAdded()) {
            mCardCVVView.selectAll();
        }
    }
}
