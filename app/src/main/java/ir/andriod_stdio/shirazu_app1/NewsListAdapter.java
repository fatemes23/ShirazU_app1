package ir.andriod_stdio.shirazu_app1;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
public class NewsListAdapter extends ArrayAdapter<NewsRaw> {
    private Context mContext ;
    private  static final String TAG = "CommentListAdapter";
    int mResource ;

    public NewsListAdapter( Context context,  int resource, ArrayList<NewsRaw> objects) {
        super(context, resource, objects);
        mContext= context;
        mResource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String title = getItem(position).getTitle();
        String date = getItem(position).getDate();
        String summeryNews = getItem(position).getSummeryNews();
        Bitmap imgNews = getItem(position).getImgNews();

        NewsRaw newComment = new NewsRaw(title,date,summeryNews,imgNews);
        LayoutInflater inflater= LayoutInflater.from(mContext);
        convertView =inflater.inflate(mResource,parent,false);

        TextView titleTv = (TextView) convertView.findViewById(R.id.news_text);
        TextView dateTV =  (TextView) convertView.findViewById(R.id.time_text);
        TextView summeryTV =  (TextView) convertView.findViewById(R.id.news_summery);
        ImageView imgTV = (ImageView) convertView.findViewById(R.id.news_img);

        titleTv.setText(title);
        dateTV.setText(date);
        summeryTV.setText(summeryNews);


        //imgTV.setImageBitmap();
        imgTV.setImageResource(R.drawable.mooze);

        return  convertView;
    }
}