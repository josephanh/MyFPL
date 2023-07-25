package nta.com.music.myfpl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import nta.com.music.myfpl.R;
import nta.com.music.myfpl.model.Campus;

public class ChooseCampusItemAdapter extends BaseAdapter {
    private final Context context;
    private final List<Campus> campusList;

    public ChooseCampusItemAdapter(Context context, List<Campus> campusList){
        this.context = context;
        this.campusList = campusList;
    }

    @Override
    public int getCount() {
        return campusList.size();
    }

    @Override
    public Object getItem(int i) {
        return campusList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return campusList.get(i).getCampusId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.custom_item_dialog, null);
            holder = new ViewHolder();
            holder.txt_campusName = view.findViewById(R.id.txt_campusName);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.txt_campusName.setText(campusList.get(i).getCampusName());
        return view;
    }

    static class ViewHolder {
        TextView txt_campusName;
    }
}
