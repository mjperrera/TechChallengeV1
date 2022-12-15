package com.example.mjperrera.cardnobinidentifier.Base;

import android.widget.EditText;

public class Utils {
    public static void editTextSetContentMemorizeSelection(EditText editText, CharSequence charSequence) {
        int selectionStart = editText.getSelectionStart();
        int selectionEnd = editText.getSelectionEnd();
        editText.setText(charSequence.toString());

        if (selectionStart > charSequence.toString().length()) {
            selectionStart = charSequence.toString().length();
        }
        if (selectionStart < 0) {
            selectionStart = 0;
        }

        if (selectionEnd > charSequence.toString().length()) {
            selectionEnd = charSequence.toString().length();
        }
        if (selectionEnd < 0) {
            selectionEnd = 0;
        }

        editText.setSelection(selectionStart, selectionEnd);
    }
}
