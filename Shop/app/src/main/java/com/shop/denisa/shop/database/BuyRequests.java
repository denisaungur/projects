package com.shop.denisa.shop.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.shop.denisa.shop.domain.ShoppingItem;

import java.util.ArrayList;

/**
 * Created by Denisa on 06.02.2017.
 */

public class BuyRequests extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Requsts.db";
    public static final String TABLE_NAME = "buy_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "QUANTITY";
    public static final String COL_4 = "STATUS";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COL_1 + " INTEGER PRIMARY KEY," +
                    COL_2 + " TEXT , " +
                    COL_3 + " INTEGER ," +
                    COL_4 + " TEXT " +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public BuyRequests(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }


    public boolean insertData(Integer id, String name, Integer quantity, String date){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, quantity);
        contentValues.put(COL_4,date);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if( result == -1){
            return false;
        }
        return true;
    }

    public ArrayList<ShoppingItem> getAllitems() {
        ArrayList<ShoppingItem> books = new ArrayList<>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_NAME;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        ShoppingItem book = null;
        if (cursor.moveToFirst()) {
            do {
                book = new ShoppingItem();
                book.setId(Integer.parseInt(cursor.getString(0)));
                book.setName(cursor.getString(1));
                book.setQuantity(0);
                book.setStatus(cursor.getString(3));

                // Add book to books
                books.add(book);
            } while (cursor.moveToNext());
        }

        return books;
    }

    public void deleteBook(Integer id){
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_NAME, //table name
                COL_1+" = ?",  // selections
                new String[] {String.valueOf(id)}); //selections args

        // 3. close
        db.close();

    }

}
