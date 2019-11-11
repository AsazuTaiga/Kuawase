package com.kuawase.kuawase.utility;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import com.kuawase.kuawase.R;

import java.util.Calendar;
import java.util.Objects;

public class SelectDateFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Nullable
    private TextView textView = null;

    public void setTextView(@NonNull TextView textView) {
        this.textView = textView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int yyyy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);

        final FragmentActivity parentActivity = getActivity();
        Objects.requireNonNull(parentActivity);
        return new DatePickerDialog(parentActivity, this, yyyy, mm, dd);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int yyyy, int mm, int dd) {
        if (null == textView) {
            return;
        }
        textView.setText(getResources().getString(R.string.date_format, yyyy, mm + 1, dd));
    }
}