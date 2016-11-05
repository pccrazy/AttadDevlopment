package Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import Adapters.ProductView;
import Tools.Shared;
import ev.com.evshopapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Orders_fragment extends Fragment {


    public Orders_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getOrders();
        return inflater.inflate(R.layout.fragment_orders, container, false);
    }

    void getOrders(){

        Shared shared=Shared.getInstance(getActivity());
        Log.d("data",shared.getUser());
        AndroidNetworking.post("http://139.59.14.123:3000/user/GetInvoices")
                .addHeaders("x-access-token",shared.getUser())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener(){
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("data",String.valueOf(response));

                    }
                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                    }

                });

    }

}
