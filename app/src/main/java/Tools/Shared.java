package Tools;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by code_crazy on 10/26/16. test
 */
public class Shared {

    private static Shared sSharedPrefs;
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    private boolean mBulkUpdate = false;
    public static final String PREFS_NAME = "Attad";
    public static final String TOKEN = "tokenKey";
    public static final String isLOGGED = "Luser";
    private Shared(Context context) {
        mPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }


    public static Shared getInstance(Context context) {
        if (sSharedPrefs == null) {
            sSharedPrefs = new Shared(context.getApplicationContext());
        }
        return sSharedPrefs;
    }

    public static Shared getInstance() {
        if (sSharedPrefs != null) {
            return sSharedPrefs;
        }
        throw new IllegalArgumentException("Should use getInstance(Context) at least once before using this method.");

    }

    private void doCommit() {
        if (!mBulkUpdate && mEditor != null) {
            mEditor.commit();
            mEditor = null;
        }
    }
    private void doEdit() {
        if (!mBulkUpdate && mEditor == null) {
            mEditor = mPref.edit();
        }
    }




    public void clear() {
        doEdit();
        mEditor.clear();
        doCommit();
    }

    public String getUser(){
        return mPref.getString(TOKEN, null);
    }

    public String getIsLOGGED(){
        return mPref.getString(isLOGGED, null);
    }

    public void setUserToken(String token){
        doEdit();
        mEditor.putString(TOKEN, token);
        doCommit();
    }

    public void setLoggeed(String value){
        doEdit();
        mEditor.putString(isLOGGED, value);
        doCommit();
    }




}
