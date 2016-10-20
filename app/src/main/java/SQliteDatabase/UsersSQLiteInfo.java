package SQliteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by anaRose on 8/14/16.
 */
public class UsersSQLiteInfo extends SQLiteOpenHelper {

    String dbPath = null;
    private static final String DATABASE_NAME = "attadDB";
    private static final String TABLE_NAME = "Users";
    private static final String COL_1 = "username";
    private static final String COL_2 = "Email";
    private static final String COL_3 = "id";

    public UsersSQLiteInfo(Context context1) {
        super(context1, DATABASE_NAME, null, 1);

        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , USERNAME TEXT, EMAIL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);

        onCreate(db);
    }

    public  boolean insetData(String name, String pass ){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,pass);

        long result = db.insert(TABLE_NAME,null, contentValues);

        if (result == -1)

            return false;
        else
            return true;

    }
}

