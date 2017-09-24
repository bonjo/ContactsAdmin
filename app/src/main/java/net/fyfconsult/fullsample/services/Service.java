package net.fyfconsult.fullsample.services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import net.fyfconsult.fullsample.persist.DBHelper;

/**
 * Created by Bonjo on 20/09/2017.
 */

public class Service {
    public SQLiteDatabase db;
    public DBHelper dbHlp;

    public Service(Context ctx){
        dbHlp = new DBHelper(ctx);
    }
}