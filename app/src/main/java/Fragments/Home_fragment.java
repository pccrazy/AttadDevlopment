package Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import ev.com.evshopapp.CategorySub;
import ev.com.evshopapp.R;


public class Home_fragment extends Fragment {

    public static final String EXTRA = "category";


    public Home_fragment() {
        // Required empty public constructor
    }

    Button butComponents,butChips,butSensers,butBoards,butOthers,butKits;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        butComponents = (Button) view.findViewById(R.id.butComponents);
        butChips = (Button) view.findViewById(R.id.butChips);
        butSensers = (Button) view.findViewById(R.id.butSensers);
        butBoards = (Button) view.findViewById(R.id.butBoards);
        butOthers = (Button) view.findViewById(R.id.butOthers);
        butKits = (Button) view.findViewById(R.id.butKits);


//        AsyncHttpClient client = new AsyncHttpClient();
//        RequestParams par = new RequestParams();
//
//        String daytime = "2016-09-05";
//        String date = " WHERE period = '0 - 4' AND date like '%"+daytime+"%'";
//
//
//        par.put("query","SELECT power FROM powerConsumption WHERE period ='0 - 4' and date like '"+daytime+"%' " );
//
//        client.post(getActivity(),"http://139.59.14.123:3000/safa/query",par,new JsonHttpResponseHandler(){
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                super.onSuccess(statusCode, headers, response);
//
//                    try {
//
//                        for (int i=0; i <response.length() ;i++) {
//
//                            JSONObject object = response.getJSONObject(i);
//
//                            Log.d("date",String.valueOf(response.getJSONObject(i)));
//
//                          //  Toast.makeText(getActivity(), String.valueOf(response.getJSONObject(i)) , Toast.LENGTH_SHORT).show();
//                        }
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//
//            }
//        });
//

        butComponents.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(getActivity(), CategorySub.class);
                intent1.putExtra(EXTRA, "Components");
                startActivity(intent1);

            }
        });

        butChips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 = new Intent(getActivity(), CategorySub.class);
                intent2.putExtra(EXTRA, "Chips");
                startActivity(intent2);

            }
        });

        butSensers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent3 = new Intent(getActivity(), CategorySub.class);
                intent3.putExtra(EXTRA, "Sensors");
                startActivity(intent3);

            }
        });

        butBoards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent4 = new Intent(getActivity(), CategorySub.class);
                intent4.putExtra(EXTRA, "Boards");
                startActivity(intent4);

            }
        });

        butOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent5 = new Intent(getActivity(), CategorySub.class);
                intent5.putExtra(EXTRA, "Other");
                startActivity(intent5);

            }
        });

        butKits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent6 = new Intent(getActivity(), CategorySub.class);
                intent6.putExtra(EXTRA, "Kits");
                startActivity(intent6);

            }
        });



        return view;
    }

}
