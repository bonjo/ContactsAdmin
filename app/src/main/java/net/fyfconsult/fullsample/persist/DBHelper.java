package net.fyfconsult.fullsample.persist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bonjo on 19/09/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "MyContactsDb.db";
    // Scripts for create table
    private String TABLE_CONTACT = "Create Table " + ContactContract.Contact.TABLE_NAME +
            "(" + ContactContract.Contact._ID + " Integer Primary Key" +
            ", " + ContactContract.Contact.COLUMN_FIRST_NAMES + " TEXT" +
            ", " + ContactContract.Contact.COLUMN_LAST_NAMES + " TEXT" +
            ", " + ContactContract.Contact.COLUMN_PHONE + " TEXT)";

    public DBHelper(Context ctx){
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CONTACT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        /*
         If we need change something on DB schema.
         1) Change DB_VERSION number
         2) Create necessary Scripts
         3) Call the scripts
          */

    }
}