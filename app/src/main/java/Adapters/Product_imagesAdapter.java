package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ev.com.evshopapp.R;

/**
 * Created by anaRose on 10/4/16.
 */

public class Product_imagesAdapter extends RecyclerView.Adapter<Product_imagesAdapter.ImagesViewHolder>{

    JSONArray imagesarray;
    Context context;
    public Product_imagesAdapter(Context context, JSONArray array){

        this.imagesarray = array;
        this.context = context;

    }
    @Override
    public ImagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_productimages,parent,false);

        return new ImagesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImagesViewHolder holder, int position) {

        try {
            final JSONObject jsonObject = imagesarray.getJSONObject(position);

            Glide.with(context).load("http://139.59.14.123"+jsonObject.getString("thumb").replaceAll(" ","%20")).into(holder.product_image);



        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return imagesarray.length();
    }

    public class ImagesViewHolder extends RecyclerView.ViewHolder {

        private ImageView product_image;

        public ImagesViewHolder(View itemView) {
            super(itemView);

            product_image = (ImageView) itemView.findViewById(R.id.product_image);

        }
    }
}
