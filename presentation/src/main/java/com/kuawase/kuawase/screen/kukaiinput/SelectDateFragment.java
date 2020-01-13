package com.kuawase.kuawase.screen.kukaiinput;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
    // TODO: TextViewを内部で抱え込まないようにしたい
    // TODO: SelectDateFragment->KukaiInputViewModel->KukaiInputFragmentへと結果通知する設計へ
    // TODO: newInstance(Fragment targetFragment, int requestCode)したうえで値を返す？
    @Nullable
    private TextView textView = null;

    @Nullable
    private DialogInterface.OnDismissListener onDismissListener;

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

    public void setOnDismissListener(DialogInterface.OnDismissListener l) {
        onDismissListener = l;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (null != onDismissListener) {
            onDismissListener.onDismiss(dialog);
        }
    }
}
