package ev.com.evshopapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import SQliteDatabase.CartSQLiteinfo;

public class CartView extends AppCompatActivity {


    RecyclerView CartRecyclerView;
    CartSQLiteinfo CartDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartview);

        CartRecyclerView = (RecyclerView) findViewById(R.id.Cart_recyclerview);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(CartView.this, LinearLayoutManager.VERTICAL,false);
        CartRecyclerView.setLayoutManager(layoutManager);

        CartDB = new CartSQLiteinfo(CartView.this);

        Cursor Cres = CartDB.getAllData();

        if (Cres.getCount() == 0) {

            Toast.makeText(CartView.this, "No data found", Toast.LENGTH_SHORT).show();

        }else {

            StringBuffer buffer = new StringBuffer();

        }
    }
}