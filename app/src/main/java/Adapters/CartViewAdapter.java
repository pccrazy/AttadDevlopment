package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.sql.Array;

import ev.com.evshopapp.R;

/**
 * Created by anaRose on 11/2/16.
 */

public class CartViewAdapter extends RecyclerView.Adapter<CartViewAdapter.CartHolder> {

    Array CartSQL;
    Context context;
    public CartViewAdapter(Context context, Array mySQLCart){

        this.CartSQL = mySQLCart;
        this.context = context;

    }

    @Override
    public CartViewAdapter.CartHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_cart,parent,false);

        return new CartHolder(v);
    }

    @Override
    public void onBindViewHolder(CartHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CartHolder extends RecyclerView.ViewHolder {


        public CartHolder(View itemView) {
            super(itemView);
        }
    }
}
