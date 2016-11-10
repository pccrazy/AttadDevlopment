package Fragments;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import SQliteDatabase.CartSQLiteinfo;
import SQliteDatabase.NavigationItemsSQLiteInfo;
import ev.com.evshopapp.CategorySub;
import ev.com.evshopapp.R;

/**
 * A simple subclass.
 */
public class Cart_Fragment extends Fragment {

    RecyclerView CartRecyclerView;
    CartSQLiteinfo CartDB;

    public Cart_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cart, container, false);

       CartRecyclerView = (RecyclerView) view.findViewById(R.id.Cart_recyclerview);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        CartRecyclerView.setLayoutManager(layoutManager);

        CartDB = new CartSQLiteinfo(getActivity());

        Cursor Cres = CartDB.getAllData();

        if (Cres.getCount() == 0) {

            Toast.makeText(getActivity(), "No data found", Toast.LENGTH_SHORT).show();

        }else {


        }


        return view;
    }

}
