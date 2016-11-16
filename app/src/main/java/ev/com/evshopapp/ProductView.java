package ev.com.evshopapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageButton;

public class ProductView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productview);

        ImageButton productview_sub_fakeactionbar_cart = (ImageButton) findViewById(R.id.productview_sub_fakeactionbar_cart);

        productview_sub_fakeactionbar_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(ProductView.this, CartView.class));

            }
        });
    }
}
