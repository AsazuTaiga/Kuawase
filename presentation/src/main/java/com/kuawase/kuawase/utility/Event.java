package com.kuawase.kuawase.utility;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Event<T> {
    @NonNull
    private final T content;

    private boolean hasBeenHandled = false;

    public Event(@NonNull T content) {
        this.content = content;
    }

    @Nullable
    public T getContentIfNotHandled() {
        if (hasBeenHandled) {
            return null;
        } else {
            hasBeenHandled = true;
            return content;
        }
    }
}
