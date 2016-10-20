package Adapters;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ev.com.evshopapp.R;

public class Subcategory extends RecyclerView.Adapter<Subcategory.CategoryViewHolder>{

    JSONArray subcategory;
    Context context;
    RecyclerView products;

    public Subcategory(JSONArray sub_category, Context context, RecyclerView recyclerView){
        this.context=context;
        this.subcategory = sub_category;
        this.products=recyclerView;

    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_sub,parent,false);

        CurrentAdapter.setCurrentAdapter("subcategory");

        return new CategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, int position) {

        try {
            final  JSONObject jsonObject = subcategory.getJSONObject(position);

            final String nameofsub = jsonObject.getString("englishName");
            holder.category_name.setText(nameofsub);
            holder.category_desc.setText(jsonObject.getString("englishDesc"));


            Glide.with(context).load("http://139.59.14.123"+jsonObject.getJSONObject("image").getString("url").replaceAll(" ","%20")).into(holder.category_img);
            try{
                if(jsonObject.get("subtype").toString().length()>8){
                   // JSONArray array= (JSONArray) jsonObject.get("subtype");
                    ArrayList<String> subtype_arraylist=new ArrayList<>();

                    JSONArray subtypes=new JSONArray(jsonObject.get("subtype").toString());

                    for (int e=0; e<subtypes.length(); e++){
                        subtype_arraylist.add(e,subtypes.getString(e));
                    }

                    holder.subtype_spinner.setAdapter(
                            new ArrayAdapter<String>(
                            context, android.R.layout.simple_spinner_dropdown_item,
                            subtype_arraylist));


                    holder.subtype_spinner.setVisibility(View.VISIBLE);



                    Log.d("data","subtype : "+subtypes);
                  //  Log.d("data","subtype : "+array.getString(0));

                }else{
                    holder.subtype_spinner.setVisibility(View.GONE);
                }

            }catch (Exception ez){
                Log.d("data",ez.getMessage());
            }
            holder.card_category.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // if it containes subtype you will require the products of the subcategory with the subtype
                    // else send only subcategory name

                    try {
                        if(jsonObject.get("subtype").toString().length()>8){

                            AndroidNetworking.post("http://139.59.14.123:3000/product/ProductsBySubcategory")
                                    .addBodyParameter("subcategoryname",nameofsub)
                                    .addBodyParameter("subtype",holder.subtype_spinner.getSelectedItem().toString())
                                    .setTag("test")
                                    .setPriority(Priority.MEDIUM)
                                    .build()
                                    .getAsJSONObject(new JSONObjectRequestListener(){
                                        @Override
                                        public void onResponse(JSONObject response) {


                                            try {
                                                Log.d("data", String.valueOf(response.getJSONArray("result").toString()));

                                                Product product =
                                                        new Product(response.getJSONArray("result"),context,products);
                                                products.setAdapter(product);
                                                notifyDataSetChanged();

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }

                                        @Override
                                        public void onError(ANError anError) {
                                            anError.printStackTrace();
                                        }

                                    });

                        }else{
                            AndroidNetworking.post("http://139.59.14.123:3000/product/ProductsBySubcategory")
                                    .addBodyParameter("subcategoryname",nameofsub)
                                    .setTag("test")
                                    .setPriority(Priority.MEDIUM)
                                    .build()
                                    .getAsJSONObject(new JSONObjectRequestListener(){
                                        @Override
                                        public void onResponse(JSONObject response) {


                                            try {

                                                Product product =
                                                        new Product(response.getJSONArray("result"),context,products);
                                                products.setAdapter(product);
                                                notifyDataSetChanged();
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }

                                        @Override
                                        public void onError(ANError anError) {
                                            anError.printStackTrace();
                                        }

                                    });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }







                }
            });





        } catch (JSONException e) {e.printStackTrace();}


    }

    @Override
    public int getItemCount() {
        Log.d("err", "subcategory counted of :" +subcategory.length());
         return subcategory.length();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        private TextView category_name;
        private CardView card_category;
        private ImageView category_img;
        private TextView category_desc;
        private Spinner subtype_spinner;

        public CategoryViewHolder(View itemView) {
            super(itemView);

            category_name=(TextView)itemView.findViewById(R.id.subTitle);
            card_category=(CardView)itemView.findViewById(R.id.card_view_sub);
            category_img=(ImageView)itemView.findViewById(R.id.Categoryimage);
            category_desc = (TextView)itemView.findViewById(R.id.subDesc);
            subtype_spinner = (Spinner)itemView.findViewById(R.id.subtype_spinner);

        }
    }
}




// 1,325 product on parse
// 1,152 new server  - 1,325 = 172
// transesters 98 product
//resesters 128 producct
// capaciters 47
// 1195

