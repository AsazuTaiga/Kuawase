package com.kuawase.kuawase.screen.haikulist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kuawase.kuawase.R;
import com.kuawase.model.HaikuInfo;

import java.util.List;
import java.util.Objects;

public class HaikuListAdapter extends ArrayAdapter<HaikuInfo> {
    private final static String FORMAT = "%s点";

    @NonNull
    private List<HaikuInfo> haikuInfos;

    @NonNull
    private LayoutInflater layoutInflater;

    HaikuListAdapter(@NonNull Context context, @NonNull List<HaikuInfo> haikuInfos) {
        super(context, R.layout.haiku_list_item, haikuInfos);
        this.haikuInfos = haikuInfos;
        this.layoutInflater = (LayoutInflater) Objects.requireNonNull(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
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
        ImageButton upButton = convertView.findViewById(R.id.up_button);
        ImageButton downButton = convertView.findViewById(R.id.down_button);

        HaikuInfo haikuInfo = haikuInfos.get(position);

        numberText.setText(String.valueOf(position + 1));
        haikuText.setText(haikuInfo.getHaiku());
        pointText.setText(String.valueOf(haikuInfo.getPoint()));
        upButton.setOnClickListener(l -> {
                    int point = haikuInfo.getPoint() + 1;
                    haikuInfo.setPoint(point);
            pointText.setText(String.format(FORMAT, String.valueOf(point)));
                }
        );
        downButton.setOnClickListener(l -> {
                    int point = haikuInfo.getPoint() - 1;
                    haikuInfo.setPoint(point);
                    pointText.setText(String.valueOf(point));
                }
        );

        return convertView;
    }
}
