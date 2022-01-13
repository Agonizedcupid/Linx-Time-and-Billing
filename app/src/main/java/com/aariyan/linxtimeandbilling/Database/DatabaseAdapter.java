package com.aariyan.linxtimeandbilling.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;


import com.aariyan.linxtimeandbilling.Model.UserListModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {


    DatabaseHelper helper;
    private List<UserListModel> list = new ArrayList<>();
    private List<UserListModel> Linelist = new ArrayList<>();
    public static int vendorCheck = 0;
    public static int poHeaderCheck = 0;
    public static int poLineCheck = 0;

    public DatabaseAdapter(Context context) {
        helper = new DatabaseHelper(context);
    }

    //Insert user data
    public long insertUserData(String uID, String pinCode, String name, String companyId) {

        SQLiteDatabase database = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.uID, uID);
        contentValues.put(DatabaseHelper.strPinCode, pinCode);
        contentValues.put(DatabaseHelper.strName, name);
        contentValues.put(DatabaseHelper.intCompanyID, companyId);

        long id = database.insert(DatabaseHelper.USER_TABLE_NAME, null, contentValues);
        return id;
    }

    //Getting all the user
    public List<UserListModel> getUserData() {

        list.clear();
        SQLiteDatabase database = helper.getWritableDatabase();
        String[] columns = {DatabaseHelper.UID, DatabaseHelper.uID, DatabaseHelper.strPinCode, DatabaseHelper.strName, DatabaseHelper.intCompanyID};
        Cursor cursor = database.query(DatabaseHelper.USER_TABLE_NAME, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {

            UserListModel model = new UserListModel(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
            );
            list.add(model);
        }
        return list;

    }

    //Insert customer data
    public long insertCustomerData(String strCustName, String strCustDesc, String Uid) {

        SQLiteDatabase database = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.strCustName, strCustName);
        contentValues.put(DatabaseHelper.strCustDesc, strCustDesc);
        contentValues.put(DatabaseHelper.Uid, Uid);

        long id = database.insert(DatabaseHelper.CUSTOMER_TABLE_NAME, null, contentValues);
        return id;
    }

//
//    public int updatePoLines(String name, int quantity) {
//
//        SQLiteDatabase database = helper.getWritableDatabase();
//        //drop the table if exist:
//        //Create table:
////        try {
////
////            if (vendorCheck == 0) {
////                database.execSQL(DatabaseHelper.DROP_VENDORS_TABLE);
////                database.execSQL(DatabaseHelper.CREATE_VENDORS_TABLE);
////            }
////            vendorCheck++;
////        } catch (Exception e) {
////            e.printStackTrace();
////
////        }
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(DatabaseHelper.decBuyQtyScanned, quantity);
//        String[] args = {name};
//        int id = database.update(DatabaseHelper.PO_LINE_TABLE_NAME, contentValues, DatabaseHelper.strPartNumber + " =? ", args);
//        return id;
//    }


    class DatabaseHelper extends SQLiteOpenHelper {
        private Context context;

        private static final String DATABASE_NAME = "linx_billing.db";
        private static final int VERSION_NUMBER = 3;

        //User Table:
        private static final String USER_TABLE_NAME = "users";
        private static final String UID = "_id";
        private static final String uID = "uID";
        private static final String strPinCode = "strPinCode";
        private static final String strName = "strName";
        private static final String intCompanyID = "strVendDesc";
        //Creating the table:
        private static final String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE_NAME
                + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + uID + " VARCHAR(255),"
                + strPinCode + " VARCHAR(255),"
                + strName + " VARCHAR(255),"
                + intCompanyID + " VARCHAR(255));";
        private static final String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + USER_TABLE_NAME;

        //Customer table:
        private static final String CUSTOMER_TABLE_NAME = "customers";
        private static final String strCustName = "strCustName";
        private static final String strCustDesc = "strCustDesc";
        private static final String Uid = "Uid";
        //Creating the table:
        private static final String CREATE_CUSTOMER_TABLE = "CREATE TABLE " + CUSTOMER_TABLE_NAME
                + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + strCustName + " VARCHAR(255),"
                + strCustDesc + " VARCHAR(255),"
                + Uid + " VARCHAR(255));";
        private static final String DROP_CUSTOMER_TABLE = "DROP TABLE IF EXISTS " + CUSTOMER_TABLE_NAME;


        public DatabaseHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, VERSION_NUMBER);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //Create table:
            try {
                db.execSQL(CREATE_USER_TABLE);
                db.execSQL(CREATE_CUSTOMER_TABLE);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(DROP_USER_TABLE);
                db.execSQL(DROP_CUSTOMER_TABLE);
                onCreate(db);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
