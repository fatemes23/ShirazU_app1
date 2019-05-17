package ir.andriod_stdio.shirazu_app1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.List;

public class EmailListAdaptor extends ArrayAdapter<EmailsRow> {

    private Context activity;
    private List<EmailsRow> friendList;
    int mResource;
    String whichPage;

    public EmailListAdaptor(Context context, int resource, List<EmailsRow> objects , String whichPage) {
        super(context, resource ,objects );
        this.activity = context;
        this.friendList = objects;
        this.mResource=resource;
        this.whichPage=whichPage;//sentOrInbox _ trash
    }


    @Override
    public int getCount() {
        return friendList.size();
    }

    @Override
    public EmailsRow getItem(int position) {
        //get item ro bara in mikhad k harf e avale esme taraf ro bezare ro ax

        return friendList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        // If holder not exist then locate all view from UI file.
        if (convertView == null) {
            // inflate UI from XML file
            convertView = inflater.inflate(R.layout.email_list_adaptor_layout, parent, false);
            // get all UI view
            holder = new ViewHolder(convertView
                    ,getItem(position).getDate()
                    ,getItem(position).getSummeryOfEmail()
                    ,getItem(position).getTitle());
            // set tag for holder
            convertView.setTag(holder);
        } else {
            // if holder created, get tag from view
            holder = (ViewHolder) convertView.getTag();
        }

        holder.friendsUsername.setText(getItem(position).getUserName());

        //get first letter of each String item
        String firstLetter = String.valueOf(getItem(position).getUserName().charAt(0));

        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
        // generate random color
        int color = generator.getColor(getItem(position));
        //int color = generator.getRandomColor();

        TextDrawable drawable = TextDrawable.builder()
                .buildRound(firstLetter, color); // radius in px

        holder.imageView.setImageDrawable(drawable);// inja image set mikone mitooni taghiresh bedi be axe khode taraf

        return convertView;
    }

    private class ViewHolder {
        private ImageView imageView;//nnmidoonm hanooz che type i begiram bara in
        private TextView friendsUsername;
        private TextView date;
        private TextView summeryOfEmail;
        private TextView title;


        public ViewHolder(View v ,String datee , String sumeryy , String titlee ) {
            imageView = (ImageView) v.findViewById(R.id.emailprofile_img);
            friendsUsername = (TextView) v.findViewById(R.id.emailUsername);
            date = (TextView) v.findViewById(R.id.emaildate);
            summeryOfEmail = (TextView) v.findViewById(R.id.summeryOfemail);
            title=(TextView)v.findViewById(R.id.titleOfEmail);
            title.setText(titlee);
            summeryOfEmail.setText(sumeryy);
            date.setText(datee);


        }
    }
}

