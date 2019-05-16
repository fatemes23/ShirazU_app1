package ir.andriod_stdio.shirazu_app1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AnnouncmnetListAdeptor extends ArrayAdapter<AnnouncmentRow> {
    private Context mContext ;
    int mResource ;

    public AnnouncmnetListAdeptor(@NonNull Context context, int resource, ArrayList<AnnouncmentRow> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        String title = getItem(position).getAnnouncmnetTitle();
        String time = getItem(position).getAnnouncmnetTime();

        AnnouncmentRow ob = new AnnouncmentRow(title,time);
        LayoutInflater inflater= LayoutInflater.from(mContext);
        convertView =inflater.inflate(mResource,parent,false);

        TextView title_textView = (TextView) convertView.findViewById(R.id.announcment_title);
        TextView time_textView = (TextView)  convertView.findViewById(R.id.announcment_time);

        title_textView.setText(title);
        time_textView.setText(time);

        return convertView;
    }
}
