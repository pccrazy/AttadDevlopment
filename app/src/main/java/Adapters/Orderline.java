package Adapters;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import ev.com.evshopapp.R;

/**
 * Created by code_crazy on 10/23/16.
 */

public class Orderline extends RecyclerView.Adapter<Orderline.Orderline_holder> {

    @Override
    public Orderline_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(Orderline_holder holder, int position) {

//        try {
//
//
//
//
//            if(data.get(position).getNumber("orderNo")!=null){
//                holder.orderId.setText(data.get(position).getNumber("orderNo").toString());
//            }else{
//                holder.orderId.setText("07");
//            }
//
//
//            holder.status.setText(data.get(position).getString("Status"));
//            holder.total.setText(df.format(data.get(position).getNumber("Tottal").doubleValue()) + " OMR");
//            holder.C_D.setText(String.valueOf(data.get(position).getCreatedAt()).substring(3, 10) + " " + String.valueOf(data.get(position).getCreatedAt()).substring(data.get(position).getCreatedAt().toString().length() - 4));
//        }catch (Exception ignore){
//
//        }

    }

    @Override
    public int getItemCount() {
        return 0;
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
          //  LinearLayoutManager mLayoutManager =  new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
            //prRecyclerView.setLayoutManager(mLayoutManager);
        //    more = (ImageButton)itemView.findViewById(R.id.order_imgbutton);


        }
    }
}
