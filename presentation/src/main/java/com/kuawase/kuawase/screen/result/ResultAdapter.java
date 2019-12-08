package com.kuawase.kuawase.screen.result;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kuawase.kuawase.R;
import com.kuawase.model.HaikuInfo;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class ResultAdapter extends ArrayAdapter<HaikuInfo> {
    @NonNull
    private List<HaikuInfo> haikuInfos;

    @NonNull
    private LayoutInflater layoutInflater;

    public ResultAdapter(@NonNull Context context, @NonNull List<HaikuInfo> haikuInfos) {
        super(context, R.layout.result_item, haikuInfos);
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
    public void sort(Comparator<? super HaikuInfo> comparator) {
        super.sort(comparator);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (null == convertView) {
            convertView = layoutInflater.inflate(R.layout.result_item, parent, false);
        }

        TextView haikuText = convertView.findViewById(R.id.haiku_text);
        TextView authorText = convertView.findViewById(R.id.author_text);
        TextView pointText = convertView.findViewById(R.id.point_text);

        HaikuInfo haikuInfo = haikuInfos.get(position);

        haikuText.setText(haikuInfo.getHaiku());
        authorText.setText(haikuInfo.getAuthor());
        pointText.setText(String.valueOf(haikuInfo.getPoint()));

        if (0 == position) {
            convertView.setBackgroundColor(getContext()
                    .getResources()
                    .getColor(R.color.colorAccent, null));
        }

        return convertView;
    }
}
