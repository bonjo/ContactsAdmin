package net.fyfconsult.fullsample.persist;

import android.provider.BaseColumns;

/**
 * Created by Bonjo on 19/09/2017.
 */

public class ContactContract {
    private void  ContactContract(){}

    public static class Contact implements BaseColumns{
        public static final String TABLE_NAME = "contact";
        public static final String COLUMN_FIRST_NAMES = "name";
        public static final String COLUMN_LAST_NAMES = "last";
        public static final String COLUMN_PHONE = "phone";
    }
}