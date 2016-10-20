package SQliteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by anaRose on 8/14/16.
 */
public class NavigationItemsSQLiteInfo extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "attadDB2";
    private static final String TABLE_NAME = "Counter";
    private static final String COL_0 = "CountId";
    private static final String COL_1 = "WhishList";
    private static final String COL_2 = "Orders";
    private static final String COL_3 = "Messages";

    public NavigationItemsSQLiteInfo(Context context2) {
        super(context2, DATABASE_NAME, null, 1);

        SQLiteDatabase db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (CountId INTEGER PRIMARY KEY AUTOINCREMENT , WhishList INTEGER, Orders INTEGER , Messages INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);

        onCreate(sqLiteDatabase);
    }

    public  boolean insetData( int wishList, int Orders, int Messages ){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,wishList);
        contentValues.put(COL_2,Orders);
        contentValues.put(COL_3,Messages);

        long result = db.insert(TABLE_NAME,null, contentValues);

        if (result == -1)

            return false;
        else
            return true;

    }

    public Cursor getAllData() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean UpdateData(int wishList, int Orders, int Messages){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,wishList);
        contentValues.put(COL_2,Orders);
        contentValues.put(COL_3,Messages);

        db.update(TABLE_NAME,contentValues, "CountId = 1", null );

        return true;
    }
}
