package SQliteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by anaRose on 10/18/16.
 */

public class CartSQLiteinfo extends SQLiteOpenHelper {


    private static final String DB_Name = "CartDB";
    private static final String TABLE_NAME = "ProductInfo";
    //private static final String COL_0 = "product_id";
    private static final String COL_0 = "product_name";
    private static final String COL_1 = "product_desc";
    private static final String COL_2 = "price";
    private static final String COL_3 = "Stock";
    private static final String COL_4 = "SN";
    private static final String COL_5 = "quentity";

    public CartSQLiteinfo(Context context) {
        super(context, DB_Name, null, 1);

        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase CartsqLiteDB) {

        CartsqLiteDB.execSQL("create table " + TABLE_NAME + " (product_name TEXT PRIMARY KEY , product_desc TEXT, price INTEGER , Stock INTEGER , SN TEXT , quentity INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase CartsqLiteDB, int i, int i1) {

        CartsqLiteDB.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(CartsqLiteDB);

    }

    public  boolean insetData( String name, String desc, int price, int stock, String sn, int quentity ){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_0,name);
        contentValues.put(COL_1,desc);
        contentValues.put(COL_2,price);
        contentValues.put(COL_3,stock);
        contentValues.put(COL_4,sn);
        contentValues.put(COL_5,quentity);

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

}
