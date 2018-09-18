package com.fira.sg_app1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fira.sg_app1.model.User;

import java.util.ArrayList;

import static com.fira.sg_app1.model.User.KEY_ID;
import static com.fira.sg_app1.model.User.KEY_gender;
import static com.fira.sg_app1.model.User.KEY_nama;
import static com.fira.sg_app1.model.User.KEY_umur;

public class UserRepo {
    private DBHelper dbHelper;

    public UserRepo(Context context){
        this.dbHelper = new DBHelper(context);
    }

    public int insert(User user){

        //buka database buat menulis data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User.KEY_nama, user.nama);
        values.put(User.KEY_gender, user.gender);
        values.put(KEY_umur, user.umur);

        long id = db.insert(User.TABLE, null, values);
        db.close();
        return (int) id;
    }

    public void delete(int id){

        //buka databse buat menulis data
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(User.TABLE, KEY_ID + "= ?", new String[] { String.valueOf(id) });
        db.close();
    }

    public void update(User user){

        //buka databse buat menulis data
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.KEY_nama, user.nama);
        values.put(User.KEY_gender, user.gender);
        values.put(KEY_umur, user.umur);

        db.update(User.TABLE, values, KEY_ID + "= ?", new String[] { String.valueOf(user.id) });
        db.close();
    }

    public ArrayList<User> getUserList(){

        //buka databse buat menulis data
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //bikin query select
        String selectQuery = "SELECT * FROM " + User.TABLE;

        //inisialisasi
        ArrayList<User> userList = new ArrayList<>();

        //eksekusi query
        Cursor cursor = db.rawQuery(selectQuery, null);

        //jika datanya ada dan cursor mengarah ke data pertama
        if (cursor.moveToFirst()){
            //looping sampai data terakhir
            do {
                User user = new User();
                user.id = cursor.getInt(cursor.getColumnIndex(User.KEY_ID));
                user.nama = cursor.getString(cursor.getColumnIndex(User.KEY_nama));
                user.gender = cursor.getString(cursor.getColumnIndex(User.KEY_gender));
                user.umur = cursor.getInt(cursor.getColumnIndex(User.KEY_umur));
                userList.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return userList;
    }

}
