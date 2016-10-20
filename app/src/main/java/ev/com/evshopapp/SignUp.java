package ev.com.evshopapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import SQliteDatabase.UsersSQLiteInfo;

public class SignUp extends AppCompatActivity {

    Button insert, keys;
    EditText first, last, phone, mail;

    Spinner country;

    String n;

    public static final String PREFS_NAME = "AOP_PREFS";
    public static final String SGINUP = "nameKey";

    UsersSQLiteInfo myDB;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        myDB = new UsersSQLiteInfo(this);

        insert = (Button) findViewById(R.id.insert);
        keys = (Button) findViewById(R.id.countrykey);

        first = (EditText) findViewById(R.id.first);
        last = (EditText) findViewById(R.id.last);
        phone = (EditText) findViewById(R.id.newphone);
        mail = (EditText) findViewById(R.id.mail);

        country = (Spinner) findViewById(R.id.countries);

        keys.setEnabled(false);

        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (country.getSelectedItem().toString().equals("Oman")) {

                    keys.setText("+968");
                } else if (country.getSelectedItem().toString().equals("India")) {

                    keys.setText("+91");
                } else if (country.getSelectedItem().toString().equals("Philippines")) {

                    keys.setText("+63");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                AndroidNetworking.post("http://139.59.14.123:3000/user/insert")
                        .addBodyParameter("cname", first.getText().toString())
                        .addBodyParameter("clname", last.getText().toString())
                        .addBodyParameter("cphone", keys.getText().toString() +phone.getText().toString())
                        .addBodyParameter("cemail", mail.getText().toString())
                        .addBodyParameter("cpassword", "1234qwer")
                        .setPriority(Priority.MEDIUM)
                        .build()

                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject responseString) {

                                try {
                                    if (responseString.getString("Error").equals(false)) {

                                        n = responseString.getString("results");

                                        save(context,n);
                                        startActivity(new Intent(SignUp.this,Login.class));

                                        boolean isinserted = myDB.insetData(first.getText().toString(), mail.getText().toString());

                                        if (isinserted == true)

                                            Toast.makeText(SignUp.this, "Data inserted", Toast.LENGTH_SHORT).show();
                                        else
                                            Toast.makeText(SignUp.this, "Data not inserted", Toast.LENGTH_SHORT).show();


                                    }else{

                                        Toast.makeText(SignUp.this, "already username registerd", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {e.printStackTrace();}

                            }

                            @Override
                            public void onError(ANError anError) {

                            }
                        });



            }
        });

    }


    public void save(Context context, String signupToken) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.putString(SGINUP, signupToken);
        editor.commit();
    }

}
