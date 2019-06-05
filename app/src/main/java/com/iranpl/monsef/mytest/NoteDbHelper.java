package com.iranpl.monsef.mytest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class NoteDbHelper extends SQLiteOpenHelper {

    String TABLE_NAME = "Notes";
    String CREATE_TABLE_QRY = "" +
            "CREATE TABLE " +
            TABLE_NAME +
            "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "title TEXT," +
            "date TEXT," +
            "textnote TEXT" +
            ")" +
            "";

    public NoteDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void insertData(String title, String text) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String insertDataIntoDB = "" +
                "INSERT INTO " +
                TABLE_NAME +
                "(" +
                "title," +
                "date," +
                "textnote" +
                ")" +
                "VALUES" +
                "(" +
                "'" + title + "'" + "," +
                "'" + formatter.format(date) + "'" + "," +
                "'" + text + "'" +
                ")" +
                "";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(insertDataIntoDB);
        db.close();
    }


    public  ArrayList<NoteModel> getNotes() {
        ArrayList<NoteModel> notes = new ArrayList<NoteModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _id, title, date, textnote FROM " + TABLE_NAME, null);
        NoteModel tempNote = null;
        while (cursor.moveToNext()) {
            tempNote = new NoteModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            notes.add(tempNote);
        }

        db.close();
        return notes;
    }

    public  NoteModel getNote(String nid) {
        ArrayList<NoteModel> notes = new ArrayList<NoteModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _id, title, date, textnote FROM " + TABLE_NAME + " WHERE _id = " + nid, null);
        NoteModel tempNote = null;
        while (cursor.moveToNext()) {
            tempNote = new NoteModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        }

        db.close();
        return tempNote;
    }

    public void deleteData() {
        String deleteData = "" +
                "DELETE FROM " +
                TABLE_NAME +
                "";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(deleteData);
        db.close();
    }
    public void delete(String nid) {
        String deleteData = "" +
                "DELETE FROM " +
                TABLE_NAME +
                " WHERE _id = " +
                nid +
                "";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(deleteData);
        db.close();
    }

    public void update(String nid, String tit, String text) {
        String deleteData = "" +
                "UPDATE " +
                TABLE_NAME +
                " SET title = "+
                "'"+ tit +"'"+
                ", textnote ="+
                "'"+ text +"'"+
                " WHERE _id = " +
                nid +
                "";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(deleteData);
        db.close();
    }

}
