package com.kuawase.kuawase.screen.haikulist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kuawase.kuawase.R;
import com.kuawase.model.SoundPlayer;
import com.kuawase.model.HaikuInfo;

import java.util.List;
import java.util.Objects;

public class HaikuListAdapter extends ArrayAdapter<HaikuInfo> {
    private final static String FORMAT = "%s点";

    @NonNull
    private List<HaikuInfo> haikuInfos;

    @NonNull
    private LayoutInflater layoutInflater;

    @NonNull
    private Context context;

    @NonNull
    private SoundPlayer player;

    HaikuListAdapter(@NonNull Context context, @NonNull List<HaikuInfo> haikuInfos) {
        super(context, R.layout.haiku_list_item, haikuInfos);
        this.haikuInfos = haikuInfos;
        this.layoutInflater = (LayoutInflater) Objects.requireNonNull(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        this.context = context;
        this.player = SoundPlayer.getInstance(context);
    }

    @Override
    public int getCount() {
        return haikuInfos.size();
    }

    @Override
    @NonNull
    public HaikuInfo getItem(int position) {
        return haikuInfos.get(position);
    }

    @Override
    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (null == convertView) {
            convertView = layoutInflater.inflate(R.layout.haiku_list_item, parent, false);
        }

        TextView numberText = convertView.findViewById(R.id.number_text);
        TextView haikuText = convertView.findViewById(R.id.haiku_text);
        TextView pointText = convertView.findViewById(R.id.point_text);
        ImageView upButton = convertView.findViewById(R.id.up_button);
        ImageView downButton = convertView.findViewById(R.id.down_button);

        HaikuInfo haikuInfo = haikuInfos.get(position);

        numberText.setText(String.valueOf(position + 1));
        haikuText.setText(haikuInfo.getHaiku());
        pointText.setText(String.format(FORMAT, String.valueOf(haikuInfo.getPoint())));
        upButton.setOnClickListener(l -> {
            player.playTapSound();
            int point = haikuInfo.getPoint() + (int)(Math.random() * 10);
            haikuInfo.setPoint(point);
            pointText.setText(String.format(FORMAT, String.valueOf(point)));

        });
        downButton.setOnClickListener(l -> {
            player.playFailedSound();
            int point = haikuInfo.getPoint() - (int)(Math.random() * 10000);
            haikuInfo.setPoint(point);
            pointText.setText(String.format(FORMAT, String.valueOf(point)));

            ImageView v = new ImageView(context);
            int drawableRes = Images.getRandom().getRes();
            v.setImageResource(drawableRes);
            Toast toast = new Toast(context);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(v);
            toast.show();
        });

        return convertView;
    }

    private enum Images {
        Mazui(R.drawable.mazui),
        Money(R.drawable.money),
        Yakisoba(R.drawable.yakisoba),
        Yukidaruma(R.drawable.yukidaruma);

        @DrawableRes
        private int res;

        Images(int res) {
            this.res = res;
        }

        int getRes() {
            return res;
        }

        static Images getRandom() {
            return values()[(int) (Math.random() * values().length)];
        }
    }
}
