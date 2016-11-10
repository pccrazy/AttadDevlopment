package Adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import ev.com.evshopapp.R;

import static android.R.attr.data;

/**
 * Created by code_crazy on 10/23/16.
 */

public class Orderline extends RecyclerView.Adapter<Orderline.Orderline_holder> {

    JSONArray orders;
    public Orderline(JSONArray orders) {

        this.orders=orders;
    }

    @Override
    public Orderline_holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_orders,parent,false);


        return new Orderline_holder(v);
    }

    @Override
    public void onBindViewHolder(Orderline_holder holder, int position) {

        try {


            JSONObject order= (JSONObject) orders.get(position);
            Log.d("order",order.toString() + " "+position);

            if(order.getString("o_id")!=null){
                holder.orderId.setText(order.getString("o_id"));
                holder.status.setText(order.get("status").equals(null)?"null":"test");
            }else{
                holder.orderId.setText("error");
            }

//
//            holder.status.setText(data.get(position).getString("Status"));
//            holder.total.setText(df.format(data.get(position).getNumber("Tottal").doubleValue()) + " OMR");
//            holder.C_D.setText(String.valueOf(data.get(position).getCreatedAt()).substring(3, 10) + " " + String.valueOf(data.get(position).getCreatedAt()).substring(data.get(position).getCreatedAt().toString().length() - 4));
        }catch (Exception ignore){

        }

    }

    @Override
    public int getItemCount() {
        return orders.length();
    }

    public class Orderline_holder extends RecyclerView.ViewHolder
    {
        TextView orderId;
        TextView status;
        TextView total;
        TextView C_D;
        ImageButton more;
        LinearLayout layout2;
        RecyclerView prRecyclerView;
        public Orderline_holder(View itemView) {
            super(itemView);
            C_D=(TextView)itemView.findViewById(R.id.txt_orderdate);
            orderId= (TextView)itemView.findViewById(R.id.order_number);
            status= (TextView)itemView.findViewById(R.id.fullorder_status);
            total= (TextView)itemView.findViewById(R.id.fullorder_total);
            prRecyclerView=(RecyclerView)itemView.findViewById(R.id.my_recycler_view_orderline);
            prRecyclerView.setHasFixedSize(true);
            LinearLayoutManager mLayoutManager =  new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false);
            prRecyclerView.setLayoutManager(mLayoutManager);
        //    more = (ImageButton)itemView.findViewById(R.id.order_imgbutton);


        }
    }
}
