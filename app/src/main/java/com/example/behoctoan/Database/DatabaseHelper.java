package com.example.behoctoan.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;


import com.example.behoctoan.Listcauhoi.List1;
import com.example.behoctoan.Listcauhoi.List2;
import com.example.behoctoan.Listcauhoi.List3;
import com.example.behoctoan.Listcauhoi.ListKiemtra;
import com.example.behoctoan.Listcauhoi.ListTapdem;
import com.example.behoctoan.Listcauhoi.Listsosanh;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/com.example.behoctoan/databases/";
    private static String DB_NAME = "behoctoan.sqlite";
    private static final int DB_VERSION = 1;
    private SQLiteDatabase mdatabase;
    private final Context mcontext;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        if (android.os.Build.VERSION.SDK_INT >= 17)
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        else
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.mcontext = context;

        copyDataBase();

        this.getReadableDatabase();

    }

    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    private void copyDataBase() {
        if (!checkDataBase()) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDBFile();
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private void copyDBFile() throws IOException {
        InputStream mInput = mcontext.getAssets().open(DB_NAME);
        //InputStream mInput = mContext.getResources().openRawResource(R.raw.info);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public boolean openDataBase() throws SQLException {
        mdatabase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mdatabase != null;
    }

    public List<List1> getDaTa1() {
        openDataBase();
        List<List1> CauHoi = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        for (int i = 1; i < 16; i++) {
            Cursor cursor = db.rawQuery("select * from Behoctoan where muc = 1 order by random() limit 1", null);
            cursor.moveToFirst();
            do {
                List1 item;
                item = new List1(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5), cursor.getString(6));
                CauHoi.add(item);
            } while (cursor.moveToNext());
        }
        db.close();
        return CauHoi;
    }

    public List<List2> getDaTa2() {
        openDataBase();
        List<List2> CauHoi = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        for (int i = 1; i < 16; i++) {
            Cursor cursor = db.rawQuery("select * from Behoctoan where muc = 2 order by random() limit 1", null);
            cursor.moveToFirst();
            do {
                List2 item;
                item = new List2(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5), cursor.getString(6));
                CauHoi.add(item);
            } while (cursor.moveToNext());
        }
        db.close();
        return CauHoi;
    }

    public List<List3> getDaTa3() {
        openDataBase();
        List<List3> CauHoi = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        for (int i = 1; i < 16; i++) {
            Cursor cursor = db.rawQuery("select * from Behoctoan where muc = 3 order by random() limit 1", null);
            cursor.moveToFirst();
            do {
                List3 item;
                item = new List3(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5), cursor.getString(6));
                CauHoi.add(item);
            } while (cursor.moveToNext());
        }
        db.close();
        return CauHoi;
    }

    public List<Listsosanh> getDaTasosanh() {
        openDataBase();
        List<Listsosanh> CauHoi = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        for (int i = 1; i < 16; i++) {
            Cursor cursor = db.rawQuery("select * from Behoctoan where muc = 'sosanh' order by random() limit 1", null);
            cursor.moveToFirst();
            do {
                Listsosanh item;
                item = new Listsosanh(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(6));
                CauHoi.add(item);
            } while (cursor.moveToNext());
        }
        db.close();
        return CauHoi;
    }

    public List<ListKiemtra> getDaTaKiemtra() {
        openDataBase();
        List<ListKiemtra> CauHoi = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        for (int i = 1; i < 21; i++) {
            Cursor cursor = db.rawQuery("select * from Behoctoan order by random() limit 1", null);
            cursor.moveToFirst();
            do {
                ListKiemtra item;
                item = new ListKiemtra(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5), cursor.getString(6));
                CauHoi.add(item);
            } while (cursor.moveToNext());
        }
        db.close();
        return CauHoi;
    }

    public List<ListTapdem> getDaTaTapdem() {
        openDataBase();
        List<ListTapdem> CauHoi = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        for (int i = 1; i < 11; i++) {
            Cursor cursor = db.rawQuery("select * from Tapdem order by random() limit 1 ", null);
            cursor.moveToFirst();
            do {
                ListTapdem item;
                item = new ListTapdem(cursor.getInt(0), cursor.getBlob(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5), cursor.getString(6));
                CauHoi.add(item);
            } while (cursor.moveToNext());
        }
        db.close();
        return CauHoi;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

