package com.kuawase.kuawase.utility;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

public class ViewModelUtils {
    @NonNull
    public static <T extends ViewModel> T provideViewModel(
            @NonNull FragmentActivity activity, @NonNull Class<T> viewModelClass) {
        Objects.requireNonNull(activity);
        return ViewModelProviders.of(activity).get(viewModelClass);
    }
}
