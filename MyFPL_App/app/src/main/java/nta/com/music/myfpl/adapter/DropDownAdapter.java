package nta.com.music.myfpl.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import nta.com.music.myfpl.R;

public class DropDownAdapter extends ArrayAdapter {
    public DropDownAdapter(@NonNull Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        Log.d(">>>>TAG", "DropDownAdapter: "+objects.size());
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LinearLayout.inflate(parent.getContext(), R.layout.item_spinner_selected, null);
        TextView itemSelect = convertView.findViewById(R.id.item_drop);

        if(this.getItem(position) != null){
            itemSelect.setText(this.getItem(position).toString());
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LinearLayout.inflate(parent.getContext(), R.layout.item_spinner, null);
        TextView item = convertView.findViewById(R.id.item_drop);

        if(this.getItem(position) != null){
            item.setText(this.getItem(position).toString());
        }

        return convertView;
    }
}
