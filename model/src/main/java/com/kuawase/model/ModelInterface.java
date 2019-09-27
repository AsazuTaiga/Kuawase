package com.kuawase.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Date;
import java.util.List;

public interface ModelInterface {
    // 全句会情報の返却
    @NonNull
    List<KukaiInfo> getAllKukaiInfo(@NonNull Context context);

    // 句会情報をもとに俳句のリストを返却
    @NonNull
    List<Haiku> getHaikuList(@NonNull Context context, @NonNull KukaiInfo kukaiInfo);

    // 句会情報のキャッシュを作成して返却
    @NonNull
    KukaiInfo createKukaiInfo(@NonNull String kukaiName, @NonNull Date startDate, @NonNull Date endDate);

    // 俳句のキャッシュを作成して返却
    @NonNull
    Haiku createHaiku(@NonNull String haiku, @NonNull String author);

    // 句会情報に俳句を紐付け
    void bindHaikuToKukai(@NonNull Haiku haiku, @NonNull KukaiInfo kukaiInfo);

    // 俳句に点数を付加
    void setHaikuPoint(@NonNull Haiku haiku, int point);

    // 句会情報を保存
    void saveKukaiInfo(@NonNull Context context, @NonNull KukaiInfo kukaiInfo);
}
