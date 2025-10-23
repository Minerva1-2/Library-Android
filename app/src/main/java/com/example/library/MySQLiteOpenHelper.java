package com.example.library;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME ="mySQLite.db";
    private static final String TABLE_NAME_LIBRARY="library";
    private static final String CREATE_TABLE_SQL="create table "+ TABLE_NAME_LIBRARY +" (name text ,number " +
            "text primary key,author text not null,price text not null)";
    public MySQLiteOpenHelper(Context context){
        super(context,DB_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertData(Library library)
    {
       SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", library.getNameAdd());
        values.put("number", library.getNumberAdd());
        values.put("author", library.getAuthorAdd());
        values.put("price", library.getPriceAdd());

        return db.insert(TABLE_NAME_LIBRARY,null,values);
    }
    public int deleteData(String name,String number,String author)
    {
        SQLiteDatabase db = getWritableDatabase();

        return db.delete(TABLE_NAME_LIBRARY,"name like ? or number like ? or author like ?",
                new String[]{name,number,author});
    }
    public int changeData(Library library) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", library.getCha_name());
        values.put("number", library.getCha_number());
        values.put("author", library.getCha_author());
        values.put("price", library.getCha_price());

        return db.update(TABLE_NAME_LIBRARY, values, "number=?",
                    new String[]{library.getCha_number()});
    }
    public List<Library> selectData(String name, String number, String author)
    {
        SQLiteDatabase db = getWritableDatabase();
        List<Library> libraries=new ArrayList<>();
        @SuppressLint("Recycle")
        Cursor cursor=db.query(TABLE_NAME_LIBRARY,null,"name like ? or number like ? or author like ?",
                new String[]{name,number,author},null,null,null);
        if (cursor!=null)
        {
            while(cursor.moveToNext())
            {
               @SuppressLint("Range")
               String name1= cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range")
                String number1= cursor.getString(cursor.getColumnIndex("number"));
                @SuppressLint("Range")
                String author1= cursor.getString(cursor.getColumnIndex("author"));
                @SuppressLint("Range")
                String price1= cursor.getString(cursor.getColumnIndex("price"));

                Library library=new Library();
                library.setName(name1);
                library.setNumber(number1);
                library.setAuthor(author1);
                library.setPrice(price1);

                libraries.add(library);
            }
            cursor.close();
        }
        return libraries;
    }
    public List<Library> selectAll()
    {
        SQLiteDatabase db = getWritableDatabase();

        List<Library> libraries=new ArrayList<>();
        @SuppressLint("Recycle")
        Cursor cursor=db.query(TABLE_NAME_LIBRARY,null,null,
                null,null,null,null);
        if (cursor!=null)
        {
            while(cursor.moveToNext())
            {
                Library book=new Library();
                @SuppressLint("Range")
                String name= cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range")
                String number= cursor.getString(cursor.getColumnIndex("number"));
                @SuppressLint("Range")
                String author= cursor.getString(cursor.getColumnIndex("author"));
                @SuppressLint("Range")
                String price= cursor.getString(cursor.getColumnIndex("price"));

                book.setName(name);
                book.setNumber(number);
                book.setAuthor(author);
                book.setPrice(price);

                libraries.add(book);
            }
            cursor.close();
        }
        return libraries;
    }
}
