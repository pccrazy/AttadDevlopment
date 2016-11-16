package Adapters;


import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import SQliteDatabase.CartSQLiteinfo;
import ev.com.evshopapp.R;


public class ProductView extends RecyclerView.Adapter<ProductView.ViewHolder> implements View.OnClickListener{

    JSONObject productInfo;
    Context context;

    RecyclerView views;

    String pattern="#0.000";
    Locale locale  = new Locale("en", "UK");
    DecimalFormat df = (DecimalFormat) NumberFormat.getNumberInstance(locale);

    CartSQLiteinfo cartinfo;

    public ProductView (Context context, JSONObject jason, RecyclerView listView){

        this.context = context;
        this.productInfo = jason;

        cartinfo = new CartSQLiteinfo(context);

//        Log.d("data", String.valueOf(listView));

        this.views = listView;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_productview,parent,false);

        df.applyPattern(pattern);



        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Log.d("data" , String.valueOf(productInfo));


        try {

            holder.productname.setText(productInfo.getString("name"));
            holder.productdiscription.setText(productInfo.getString("description"));
            holder.itemprice.setText(productInfo.getString( "price_formated"));
            holder.productstock.setText(productInfo.getString("minstock"));

            //holder.productstocknumber.setText(productInfo.getString("؟"));


            RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
            holder.product_image_view.setLayoutManager(layoutManager);


            Log.d("data", String.valueOf(productInfo.getJSONArray("images")));


            Product_imagesAdapter imagesAdapter = new Product_imagesAdapter(context,productInfo.getJSONArray("images"));
            holder.product_image_view.setAdapter(imagesAdapter);


            holder.productview_u25.setText(String.valueOf(df.format(changeTottal(productInfo.getDouble("price") * 0.90))) + " OMR" );
            holder.productview_u50.setText(String.valueOf(df.format(changeTottal(productInfo.getDouble("price") * 0.85))) + " OMR" );






            holder.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    //back button to list products..
                }
            });


            holder.cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // list of cart appear ..

                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {

        return 1;
    }

    public static double changeTottal(double price)
    {
        return (double) Math.round(price * 1000) / 1000;
    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.addtocart){

            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.dialog_enter_quantity);
            final EditText itemquentity = (EditText) dialog.findViewById(R.id.quanitityedittext);
            Button dialogButton1 = (Button) dialog.findViewById(R.id.confirmbutton);

            dialogButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                        if ((Integer.parseInt( productInfo.getString("stock") ) > itemquentity.length()) && (itemquentity.length() == 0) ) {

                            Toast.makeText(context, " empty or invalid quentity !! ", Toast.LENGTH_SHORT).show();

                        } else {

                            Toast.makeText(context, " valid quentity", Toast.LENGTH_SHORT).show();

                            Log.d("data", "Inserting ..");

                            cartinfo.insetData(productInfo.getString("name"),
                                    productInfo.getString("description"),
                                    productInfo.getInt("price_formated"),
                                    productInfo.getInt("minStock"),
                                    productInfo.getString(""),
                                    productInfo.getInt(itemquentity.getText().toString()));

                            Cursor res = cartinfo.getAllData();

                            if (res.getCount() > 0){

                                Toast.makeText(context, "data inserted ", Toast.LENGTH_SHORT).show();

                                dialog.dismiss();
                            }

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
        else if (view.getId() == R.id.addtowishlist){

            // anwar's API

        }
        else if (view.getId() == R.id.prod_like){


            // anwar's API

        }


    }










    public class ViewHolder extends RecyclerView.ViewHolder {



        private ImageButton back;
        private ImageButton cart;

        private CardView card_view_productname_discritption;
        private TextView productname;
        private TextView productdiscription;
        private TextView itemprice;
        private TextView productstock;
        private TextView productstocknumber;

        private RecyclerView product_image_view;

        private TextView productview_u25;
        private TextView productview_u50;


        public ViewHolder(View itemView) {
            super(itemView);


            back = (ImageButton) itemView.findViewById(R.id.btn_back_productview);
            cart = (ImageButton) itemView.findViewById(R.id.productview_sub_fakeactionbar_cart);

            card_view_productname_discritption = (CardView) itemView.findViewById(R.id.card_view_productname_discritption);
            productname = (TextView) itemView.findViewById(R.id.productname);
            productdiscription = (TextView) itemView.findViewById(R.id.productdiscription);
            itemprice = (TextView) itemView.findViewById(R.id.itemprice);
            productstock = (TextView) itemView.findViewById(R.id.productstock);
            productstocknumber = (TextView) itemView.findViewById(R.id.productstocknumber);

            product_image_view = (RecyclerView) itemView.findViewById(R.id.product_image_view);

            productview_u25 = (TextView) itemView.findViewById(R.id.productview_u25);
            productview_u50 = (TextView) itemView.findViewById(R.id.productview_u50);


        }
    }


}
