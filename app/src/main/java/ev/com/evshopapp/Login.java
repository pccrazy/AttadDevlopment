package ev.com.evshopapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import NavDrower.CouNavItem;
import SQliteDatabase.NavigationItemsSQLiteInfo;

public class Login extends AppCompatActivity {

    Button login;
    EditText member,password;
    TextView sign_up,forget_pass;

    public static final String PREFS_NAME = "Attad";
    public static final String LOGIN = "tokenKey";

    String token;
    Context context;

    SharedPreferences sharedpreferences;
    public static final String user = "Luser";

    NavigationItemsSQLiteInfo myDB;
    CouNavItem couNavItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        couNavItem=new CouNavItem();

        myDB = new NavigationItemsSQLiteInfo(this);

        Cursor res = myDB.getAllData();

        if (res.getCount() == 0){

            InsertStaticData();
        }

        //.............. insert data into SQLite db ..............................................


        sharedpreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);


        login =(Button)findViewById(R.id.attad);
        member =(EditText)findViewById(R.id.Muser);
        password =(EditText)findViewById(R.id.Mpas);
        sign_up =(TextView)findViewById(R.id.signup);
        forget_pass =(TextView)findViewById(R.id.forgetpass);

        if (sharedpreferences.getString(user,null) != null)

           startActivity(new Intent(Login.this, MainHome.class));



//..................................................................................................

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("email", member.getText());
                    jsonObject.put("password", password.getText());


                } catch (JSONException e) {e.printStackTrace();}

                AndroidNetworking.post("http://139.59.14.123:3000/user/login")
                        .addJSONObjectBody(jsonObject) // posting json
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject responseString) {

                                try {
                                    if (responseString.getBoolean("error")==false) {

                                        token = responseString.getString("result");

                                        save2(getApplicationContext(),token);

                                        String n2  = "userLogin";
                                        SharedPreferences.Editor editor = sharedpreferences.edit();
                                        editor.putString(user, n2);
                                        editor.commit();

                                        startActivity(new Intent(Login.this,MainHome.class));

                                    }else{
                                        Toast.makeText(Login.this,"Wrong Credentioals",Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {e.printStackTrace();}

                            }
                            @Override
                            public void onError(ANError error) {
                                // handle error
                            }
                        });



                //if user already login to app.






            }
        });



        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sign_up.setTextColor(Color.rgb(51, 204, 51));

                startActivity(new Intent(Login.this,SignUp.class));


            }
        });



        forget_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                forget_pass.setTextColor(Color.rgb(51, 204, 51));

//                startActivity(new Intent(Login.this,forgetpassword.class));
            }
        });
    }

    public void save2(Context context, String LoginToken) {

        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.putString(LOGIN, LoginToken);
        editor.commit();

    }

    public void InsertStaticData() {

        boolean isinserted = myDB.insetData(couNavItem.getCountwhishlist(),couNavItem.getCountOrder(),couNavItem.getCountmessages());
        if (isinserted){
            Toast.makeText(Login.this, "new Data added", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(Login.this, "new Data not added", Toast.LENGTH_SHORT).show();
    }

}
