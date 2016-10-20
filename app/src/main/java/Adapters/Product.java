package Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import ev.com.evshopapp.R;

/**
 * Created by anaRose on 9/7/16.
 */
public class Product extends RecyclerView.Adapter<Product.ProductsViewHolder> {

    JSONArray products;
    Context context;

    String product_id;

    String pattern="#0.000";
    Locale locale  = new Locale("en", "UK");
    DecimalFormat df = (DecimalFormat) NumberFormat.getNumberInstance(locale);


    RecyclerView productview;
    public Product(JSONArray productsArrey, Context context ,  RecyclerView recyclerView2){
        this.context=context;
        this.products = productsArrey;
        this.productview=recyclerView2;

    }

    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_product,parent,false);

        CurrentAdapter.setCurrentAdapter("product");

        df.applyPattern(pattern);
        return new ProductsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProductsViewHolder holder, int position) {

        try {
            final JSONObject jsonObject = products.getJSONObject(position);

            holder.ProductName.setText(jsonObject.getString("name"));
            holder.ProductDesc.setText(jsonObject.getString("description"));

            Glide.with(context).load("http://139.59.14.123"+jsonObject.getString("thumb_url").replaceAll(" ","%20")).into(holder.productimage);

            holder.productprice.setText(df.format((double) Math.round(jsonObject.getDouble("price") * 1000) / 1000) + " OMR");

            holder.productcard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {

                    try {
                        AndroidNetworking.post("http://139.59.14.123:3000/product/getProduct")
                                .addBodyParameter("productId",String.valueOf(jsonObject.getInt("productId")))
                                .setTag("test")
                                .setPriority(Priority.MEDIUM)
                                .build()
                                .getAsJSONObject(new JSONObjectRequestListener(){
                                    @Override
                                    public void onResponse(JSONObject response) {

                                        Log.d("data",String.valueOf(response));


                                        ProductView adapter =
                                                new ProductView(context, response, productview);

                                        productview.setAdapter(adapter);

                                        notifyDataSetChanged();

                                    }

                                    @Override
                                    public void onError(ANError anError) {
                                        anError.printStackTrace();
                                    }

                                });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });


        } catch (JSONException e) {e.printStackTrace();}


    }

    @Override
    public int getItemCount() {
        Log.d("err", "subcategory counted of :" +products.length());
        return products.length();
    }


    public class ProductsViewHolder extends RecyclerView.ViewHolder {

        private CardView productcard;
        private ImageView productimage;
        private TextView ProductName;
        private TextView ProductDesc;
        private TextView productprice;
        private TextView productcardlike;

        public ProductsViewHolder(View itemView) {
            super(itemView);

            productcard=(CardView)itemView.findViewById(R.id.unfiltercard);
            productimage=(ImageView)itemView.findViewById(R.id.productimage);
            ProductName=(TextView)itemView.findViewById(R.id.unfilterProductName);
            ProductDesc = (TextView)itemView.findViewById(R.id.unfilterProductDesc);
            productprice=(TextView)itemView.findViewById(R.id.unfiltertprice);
            productcardlike=(TextView)itemView.findViewById(R.id.unfilterlike);

        }
    }

}



