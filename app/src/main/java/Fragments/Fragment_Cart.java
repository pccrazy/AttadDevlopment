package Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ev.com.evshopapp.R;

public class Fragment_Cart extends Fragment {


    Context context;

    public Fragment_Cart() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        RecyclerView purchaseProducts = (RecyclerView) view.findViewById(R.id.Cart_recyclerview);


        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        purchaseProducts.setLayoutManager(layoutManager);






        return view;
    }

}
