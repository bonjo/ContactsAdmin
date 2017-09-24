package net.fyfconsult.fullsample.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import net.fyfconsult.fullsample.entities.Contact;
import net.fyfconsult.fullsample.persist.ContactContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bonjo on 15/09/2017.
 */

public class ContactService extends Service {
    public ContactService(Context ctx) {
        super(ctx);
    }

    public void create(Contact p){
        db = dbHlp.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ContactContract.Contact.COLUMN_FIRST_NAMES, p.getFirstName());
        values.put(ContactContract.Contact.COLUMN_LAST_NAMES, p.getLastName());
        values.put(ContactContract.Contact.COLUMN_PHONE, p.getPhone());
        db.insert(ContactContract.Contact.TABLE_NAME, null, values);
        db.close();
    }

    public List<Contact> findByName(String name){
        db = dbHlp.getReadableDatabase();
        List<Contact> people= new ArrayList<>();
        Cursor cur = db.query(ContactContract.Contact.TABLE_NAME
                , new String[]{ContactContract.Contact.COLUMN_FIRST_NAMES, ContactContract.Contact.COLUMN_LAST_NAMES, ContactContract.Contact.COLUMN_PHONE}
                , ContactContract.Contact.COLUMN_FIRST_NAMES + " Like ?", new String[]{"%" + name + "%"}
                , null, null, null);
        if(cur.moveToFirst()){
            while (cur.isAfterLast() == false){
                people.add(new Contact(cur.getString(0), cur.getString(1)));
                cur.moveToNext();
            }
        }
        db.close();
        return people;
    }

    public void update(Contact p){
        db = dbHlp.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ContactContract.Contact.COLUMN_FIRST_NAMES, p.getFirstName());
        values.put(ContactContract.Contact.COLUMN_LAST_NAMES, p.getLastName());
        values.put(ContactContract.Contact.COLUMN_PHONE, p.getPhone());
        db.update(ContactContract.Contact.TABLE_NAME, values, "_ID = ?", new String[]{p.getId() + ""});
        db.close();
    }

    public void delete(int idContact){
        db = dbHlp.getWritableDatabase();
        db.delete(ContactContract.Contact.TABLE_NAME, "_ID = " + idContact, null);
        db.close();
    }

    public List<Contact> getAll(){
        db = dbHlp.getReadableDatabase();
        List<Contact> people= new ArrayList<>();
        Cursor cur = db.query(ContactContract.Contact.TABLE_NAME
                , new String[]{ContactContract.Contact._ID, ContactContract.Contact.COLUMN_FIRST_NAMES, ContactContract.Contact.COLUMN_LAST_NAMES, ContactContract.Contact.COLUMN_PHONE}
                , null, null, null, null, ContactContract.Contact.COLUMN_LAST_NAMES);
        if(cur.moveToFirst()){
            while (cur.isAfterLast() == false){
                people.add(new Contact(cur.getInt(0), cur.getString(1), cur.getString(2), cur.getString(3)));
                cur.moveToNext();
            }
        }
        db.close();
        return people;
    }
}