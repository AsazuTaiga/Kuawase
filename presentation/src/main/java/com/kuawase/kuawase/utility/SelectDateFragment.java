package com.kuawase.kuawase.utility;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.Calendar;
import java.util.Objects;

public class SelectDateFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    public static SelectDateFragment newInstance(Fragment fragment) {
        SelectDateFragment instance = new SelectDateFragment();
        instance.setTargetFragment(fragment, 0);
        return instance;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);

        final FragmentActivity parentActivity = getActivity();
        Objects.requireNonNull(parentActivity);
        return new DatePickerDialog(parentActivity, this, yy, mm, dd);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
        //
    }
}
