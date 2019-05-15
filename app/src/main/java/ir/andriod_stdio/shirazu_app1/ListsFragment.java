package ir.andriod_stdio.shirazu_app1;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import java.util.ArrayList;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import java.util.ArrayList;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_NAME = "NAME";
    private static final String ARG_NUMBER = "ITEM_NUMBER";

    // TODO: Rename and change types of parameters
    private String nameOfPage;
    private int numberOfListRow;



    private OnFragmentInteractionListener mListener;

    public ListsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name Parameter 1.
     * @param number Parameter 2.
     * @return A new instance of fragment Akhbar.
     */

    // TODO: Rename and change types and number of parameters
    public static ListsFragment newInstance(String name, String number) {
        ListsFragment fragment = new ListsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        args.putString(ARG_NUMBER, number);
        fragment.setArguments(args);
        return fragment;
    }

    ArrayList<NewsRaw>  allRows ;
     ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nameOfPage = getArguments().getString(ARG_NAME);
            numberOfListRow = getArguments().getInt(ARG_NUMBER);//
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_lists, container, false);

        //_________________inja ba tavajih be inke che akhbari mikhaii neshoon bedi set mikoni :)
        //ba tavajoh be nameOfPage o numberOfListRow neshoon midi
        //_______________________________sana______________________________________


        ListView listView = (ListView) view.findViewById(R.id.listview);
       // Log.d(TAG , "onCreate: Standard.");
        allRows = new ArrayList<>();

        Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_background);

        //******************************Inja news hayi k az server gereftam ro set kardam****************************karim
      /*  for(int i=0;i<MainActivity.allnews.size();i++){
            allRows.add(new NewsRaw(MainActivity.allnews.get(i).subject,MainActivity.allnews.get(i).date,MainActivity.allnews.get(i).context,icon));
        }*/
        //******************************Inja news hayi k az server gereftam ro set kardam****************************karim


        //*******************************ja ii k bar asase tag ha set mikoni *********
        if(nameOfPage.equals("اخبار دانشگاه")){
            allRows.add(new NewsRaw("inja akhabare danesh gaheeee","date","summery",icon));


            allRows.add(new NewsRaw("sag","date","summery",icon));
            allRows.add(new NewsRaw("gav","date","summery",icon));
            allRows.add(new NewsRaw("title","date","suddmmery",icon));
            allRows.add(new NewsRaw("tccitle","date","summery",icon));
            allRows.add(new NewsRaw("title","date","summery",icon));
            allRows.add(new NewsRaw("title","date","summery",icon));
            allRows.add(new NewsRaw("title","date","summe445ry",icon));

        }else if (nameOfPage.equals("اخبار امروز")){
            allRows.add(new NewsRaw("inja akhabare emroooze","date","summery",icon));
        }else if ( nameOfPage.equals("اخبار مرتبط با دانشگاه")){
            allRows.add(new NewsRaw("inja akhabare mortabet ba danesh gah ","date","summery",icon));
        }else if ( nameOfPage.equals("آموزشی")){
            allRows.add(new NewsRaw("inja etelaiiee ye amoozeshi ","date","summery",icon));
        }else if ( nameOfPage.equals("دانشجویی")){
            allRows.add(new NewsRaw("inja etelaiiee ye دانشجویی ","date","summery",icon));
        }else if ( nameOfPage.equals(" فرهنگی")){
            allRows.add(new NewsRaw("inja etelaiiee ye فرهنگی ","date","summery",icon));
        }else if ( nameOfPage.equals("پژوهشی")){
            allRows.add(new NewsRaw("inja etelaiiee ye پژوهشی ","date","summery",icon));
        }else if ( nameOfPage.equals("سایر")){
            allRows.add(new NewsRaw("inja etelaiiee ye سایر ","date","summery",icon));
        }

        //*************************************


        NewsListAdapter  adapter = new NewsListAdapter(this.getContext() , R.layout.adapter_view_layout , allRows);
        ListView mylistview = (ListView) view.findViewById(R.id.listview);
        mylistview.setAdapter(adapter);



        //______________inja baraye click kardn roo ye har list___________________

        mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                Intent i = new Intent(ListsFragment.this.getActivity(), SingleNewsActivity.class);
                //If you wanna send any data to nextActicity.class you can use
               // i.putExtra(String key, value.get(position));

                //inja har chi bekhaii ezaf koni dige ok hast

                startActivity(i);
            }
        });
        //__________________________________________________________________





        //_______________________________sana________________________________________


        return view;

        //TextView tvLabel = (TextView) view.findViewById(R.id.tvLabel);
        // tvLabel.setText(nameOfPage);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    /*//in ro khodesh neveshte bood error midad be inke main activity bayad implement beshe comment esh kardm
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

}
