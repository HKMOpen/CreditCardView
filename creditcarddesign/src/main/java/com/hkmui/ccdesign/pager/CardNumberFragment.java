package com.hkmui.ccdesign.pager;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.hkmui.ccdesign.CreditCardUtils;
import com.hkmui.ccdesign.R;
import com.hkmui.ccdesign.tool.CardType;

import static android.content.Context.CLIPBOARD_SERVICE;
import static com.hkmui.ccdesign.CreditCardUtils.EXTRA_CARD_NUMBER;
import static com.hkmui.ccdesign.CreditCardUtils.MAX_LENGTH_CARD_NUMBER;
import static com.hkmui.ccdesign.CreditCardUtils.MAX_LENGTH_CARD_NUMBER_WITH_SPACES;

/**
 * Created by sharish on 9/1/15.
 */
public class CardNumberFragment extends CreditCardFragment {
    EditText mCardNumberView;
    FloatingActionButton fab_paste;

    public CardNumberFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle state) {
        View v = inflater.inflate(R.layout.lyt_card_number, group, false);
        mCardNumberView = (EditText) v.findViewById(R.id.card_number_field);
        fab_paste = (FloatingActionButton) v.findViewById(R.id.action_paste);
        String number = "";
        if (getArguments() != null && getArguments().containsKey(EXTRA_CARD_NUMBER)) {
            number = getArguments().getString(EXTRA_CARD_NUMBER);
        }
        if (number == null) {
            number = "";
        }
        mCardNumberView.setText(number);
        mCardNumberView.addTextChangedListener(this);
        fab_paste.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View view) {
                processPaste();
            }
        });
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public String readFromClipboard() {
        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboard.hasPrimaryClip()) {
            android.content.ClipDescription description = clipboard.getPrimaryClipDescription();
            android.content.ClipData data = clipboard.getPrimaryClip();
            if (data != null && description != null && description.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN))
                return String.valueOf(data.getItemAt(0).getText());
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void processPaste() {
        String pasted = readFromClipboard();
        if (pasted != null && CardType.BITCOIN.isMatched(pasted)) {
            mCardNumberView.setInputType(EditorInfo.TYPE_CLASS_TEXT);
            mCardNumberView.setText(pasted);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void setClipboard(String text) {
        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
        clipboard.setPrimaryClip(clip);
    }

    @Override
    public void afterTextChanged(Editable s) {
        int cursorPosition = mCardNumberView.getSelectionEnd();
        int previousLength = mCardNumberView.getText().length();
        String cardNumber = CreditCardUtils.handleCardNumber(s.toString());
        int modifiedLength = cardNumber.length();
        mCardNumberView.removeTextChangedListener(this);
        mCardNumberView.setText(cardNumber);
        mCardNumberView.setSelection(cardNumber.length() > MAX_LENGTH_CARD_NUMBER_WITH_SPACES ? MAX_LENGTH_CARD_NUMBER_WITH_SPACES : cardNumber.length());
        mCardNumberView.addTextChangedListener(this);
        if (modifiedLength <= previousLength && cursorPosition < modifiedLength) {
            mCardNumberView.setSelection(cursorPosition);
        }
        onEdit(cardNumber);
        if (cardNumber.replace(CreditCardUtils.SPACE_SEPERATOR, "").length() == MAX_LENGTH_CARD_NUMBER) {
            onComplete();
        }
    }

    @Override
    public void focus() {
        if (isAdded()) {
            mCardNumberView.selectAll();
        }
    }
}
