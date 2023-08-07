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

    List<String> objects;
    public DropDownAdapter(@NonNull Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.objects = objects;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LinearLayout.inflate(parent.getContext(), R.layout.item_spinner_selected, null);
        TextView itemSelect = convertView.findViewById(R.id.item_drop);

        if(this.getItem(position) != null){
            itemSelect.setText(objects.get(position));
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LinearLayout.inflate(parent.getContext(), R.layout.item_spinner, null);
        TextView item = convertView.findViewById(R.id.item_drop);

        if(this.getItem(position) != null){
            item.setText(objects.get(position));
        }

        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
//        Log.d(">>>>TAG", "notifyDataSetChanged: "+objects.size());
    }
}
