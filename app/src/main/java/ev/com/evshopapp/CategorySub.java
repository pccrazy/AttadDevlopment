package ev.com.evshopapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Adapters.CurrentAdapter;
import Adapters.Subcategory;

public class CategorySub extends AppCompatActivity {

    ImageButton components,chips,sensers,boards,others,kits;

    RecyclerView Categorylist;

    public static final String EXTRA = "category";
    String currentsub;

    public String getCurrentsub() {
        return currentsub;
    }

    public void setCurrentsub(String currentsub) {
        this.currentsub = currentsub;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_components);


        components = (ImageButton) findViewById(R.id.imageButton);
        chips = (ImageButton) findViewById(R.id.imageButton2);
        sensers = (ImageButton) findViewById(R.id.imageButton3);
        boards = (ImageButton) findViewById(R.id.imageButton4);
        others = (ImageButton) findViewById(R.id.imageButton5);
        kits = (ImageButton) findViewById(R.id.imageButton6);

        Categorylist = (RecyclerView) findViewById(R.id.recyclerSubView);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(CategorySub.this,LinearLayoutManager.VERTICAL,false);
        Categorylist.setLayoutManager(layoutManager);

        String valuOfButton = getIntent().getStringExtra(EXTRA);
        changeSubcategory(valuOfButton);



        components.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                changeSubcategory("Components");
                setCurrentsub("Components");
            }
        });

        chips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                changeSubcategory("Chips");

                setCurrentsub("Chips");
            }
        });

        sensers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                changeSubcategory("Sensors");

                setCurrentsub("Sensors");
            }
        });

        boards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                changeSubcategory("Boards");

                setCurrentsub("Boards");
                }
        });

        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              changeSubcategory("Other");

                setCurrentsub("Other");
            }
        });

        kits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                changeSubcategory("Kits");
                setCurrentsub("Kits");

            }
        });
    }
    void changeSubcategory(String category){

        AndroidNetworking.post("http://139.59.14.123:3000/subcat/getSubcat")
                .addBodyParameter("category_name", category)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject responseString) {

                        try {
                            JSONArray jsonArray = responseString.getJSONArray("result");
                            Subcategory adapter = new Subcategory(jsonArray, getApplicationContext(),Categorylist);
                            Categorylist.setAdapter(adapter);

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
    @Override
    public void onBackPressed() {

        if(CurrentAdapter.getCurrentAdapter().equals("product")){

            changeSubcategory(getCurrentsub());
            // go back to subcategory

        }else{

            this.moveTaskToBack(true);

        }

    }

}
