package com.example.tugassederhana;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mhs.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "mhs";
    public static final String COL_NRP = "nrp";
    public static final String COL_NAMA = "nama";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_NRP + " TEXT PRIMARY KEY, " +
                COL_NAMA + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Insert / Simpan Data
    public boolean insertData(String nrp, String nama) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NRP, nrp);
        values.put(COL_NAMA, nama);
        long result = db.insert(TABLE_NAME, null, values);
        return result != -1;
    }

    // Select / Cari Data berdasarkan NRP
    public Cursor getData(String nrp) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_NRP + " = ?", new String[]{nrp});
    }

    // Update Data berdasarkan NRP
    public boolean updateData(String nrp, String nama) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAMA, nama);
        int rowsAffected = db.update(TABLE_NAME, values, COL_NRP + " = ?", new String[]{nrp});
        return rowsAffected > 0;
    }

    // Delete / Hapus Data berdasarkan NRP
    public boolean deleteData(String nrp) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete(TABLE_NAME, COL_NRP + " = ?", new String[]{nrp});
        return rowsDeleted > 0;
    }
}
