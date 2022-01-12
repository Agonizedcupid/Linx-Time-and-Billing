package com.aariyan.linxtimeandbilling.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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

    //Inserting on vendor table:
    //Only for VENDORS:
    public long insertUserData(String uID, String pinCode, String name, String companyId) {

        SQLiteDatabase database = helper.getWritableDatabase();
        //drop the table if exist:
        //Create table:
        try {

            if (vendorCheck == 0) {
                database.execSQL(DatabaseHelper.DROP_VENDORS_TABLE);
                database.execSQL(DatabaseHelper.CREATE_VENDORS_TABLE);
            }
            vendorCheck++;
        } catch (Exception e) {
            e.printStackTrace();

        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.uID, uID);
        contentValues.put(DatabaseHelper.strPinCode, pinCode);
        contentValues.put(DatabaseHelper.strName, name);
        contentValues.put(DatabaseHelper.intCompanyID, companyId);

        long id = database.insert(DatabaseHelper.USER_TABLE_NAME, null, contentValues);
        return id;
    }

    //Get all data from vendor table:
    //Only for Vendor table
//    public List<UserListModel> getAllData() {
//
//        list.clear();
//        SQLiteDatabase database = helper.getWritableDatabase();
//        String[] columns = {DatabaseHelper.UID, DatabaseHelper.NAME, DatabaseHelper.DESCRIPTION};
//        Cursor cursor = database.query(DatabaseHelper.VENDORS_TABLE_NAME, columns, null, null, null, null, null);
//
//        StringBuffer buffer = new StringBuffer();
//        while (cursor.moveToNext()) {
//
//            GoodsModel model = new GoodsModel(
//                    cursor.getString(1),
//                    cursor.getString(2)
//            );
//            list.add(model);
//        }
//
//        return list;
//
//    }


    public int updatePoLines(String name, int quantity) {

        SQLiteDatabase database = helper.getWritableDatabase();
        //drop the table if exist:
        //Create table:
//        try {
//
//            if (vendorCheck == 0) {
//                database.execSQL(DatabaseHelper.DROP_VENDORS_TABLE);
//                database.execSQL(DatabaseHelper.CREATE_VENDORS_TABLE);
//            }
//            vendorCheck++;
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.decBuyQtyScanned, quantity);
        String[] args = {name};
        int id = database.update(DatabaseHelper.PO_LINE_TABLE_NAME, contentValues, DatabaseHelper.strPartNumber + " =? ", args);
        return id;
    }


    class DatabaseHelper extends SQLiteOpenHelper {
        private Context context;

        private static final String DATABASE_NAME = "linx_billing.db";
        private static final int VERSION_NUMBER = 1;

        //Vendors Table:
        private static final String USER_TABLE_NAME = "users";
        private static final String UID = "_id";
        private static final String uID = "uID";
        private static final String strPinCode = "strPinCode";
        private static final String strName = "strName";
        private static final String intCompanyID = "strVendDesc";
        //Creating the table:
        private static final String CREATE_VENDORS_TABLE = "CREATE TABLE " + USER_TABLE_NAME
                + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + uID + " VARCHAR(255),"
                + strPinCode + " VARCHAR(255),"
                + strName + " VARCHAR(255),"
                + intCompanyID + " VARCHAR(255));";
        private static final String DROP_VENDORS_TABLE = "DROP TABLE IF EXISTS " + USER_TABLE_NAME;

        //PO Header:
        private static final String PO_HEADER_TABLE_NAME = "po_header";
        //Column
        private static final String strOrdPDocID = "strOrdPDocID";
        private static final String UniID = "UniID";
        private static final String strDeliveryNoteNo = "strDeliveryNoteNo";
        private static final String intAuthInvUserID = "intAuthInvUserID";
        private static final String journalDate = "journalDate";
        private static final String journalTimezone_type = "journalTimezone_type";
        private static final String journalTimezone = "journalTimezone";
        private static final String requiredDate = "requiredDate";
        private static final String requiredTimezone_type = "requiredTimezone_type";
        private static final String requiredTimezone = "requiredTimezone";
        private static final String strUserName = "strUserName";
        private static final String strVendName = "strVendName";
        private static final String strNotes = "strNotes";
        private static final String strCurrency = "strCurrency";
        private static final String decExchange = "decExchange";
        private static final String strAddInfo = "strAddInfo";
        private static final String strReference = "strReference";
        private static final String Total = "Total";

        //Creating the table:
        private static final String CREATE_PO_HEADER_TABLE = "CREATE TABLE " + PO_HEADER_TABLE_NAME
                + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + strOrdPDocID + " VARCHAR(255),"
                + UniID + " VARCHAR(255),"
                + strDeliveryNoteNo + " VARCHAR(255),"
                + intAuthInvUserID + " INTEGER,"
                + journalDate + " VARCHAR(255),"
                + journalTimezone_type + " INTEGER,"
                + journalTimezone + " VARCHAR(255),"
                + requiredDate + " VARCHAR(255),"
                + requiredTimezone_type + " INTEGER,"
                + requiredTimezone + " VARCHAR(255),"
                + strUserName + " VARCHAR(255),"
                + strVendName + " VARCHAR(255),"
                + strNotes + " VARCHAR(255),"
                + strCurrency + " VARCHAR(255),"
                + decExchange + " VARCHAR(255),"
                + strAddInfo + " VARCHAR(255),"
                + strReference + " VARCHAR(255),"
                + Total + " VARCHAR(255));";

        private static final String DROP_PO_HEADER_TABLE = "DROP TABLE IF EXISTS " + PO_HEADER_TABLE_NAME;

        //PO LINES:
        private static final String PO_LINE_TABLE_NAME = "po_line";
        //Column
        private static final String intLineNo = "intLineNo";
        private static final String intLineAuthUserID = "intLineAuthUserID";
        private static final String strPartNumber = "strPartNumber";
        private static final String strLocation = "strLocation";
        private static final String strPartDesc = "strPartDesc";
        private static final String decBuyQtyRemain = "decBuyQtyRemain";
        private static final String decBuyQtyScanned = "decBuyQtyScanned";
        private static final String strInvField4 = "strInvField4";
        private static final String strInvField5 = "strInvField5";
        private static final String Barcode = "Barcode";

        //Creating the table:
        private static final String CREATE_PO_LINE_TABLE = "CREATE TABLE " + PO_LINE_TABLE_NAME
                + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + strOrdPDocID + " VARCHAR(255),"
                + intLineNo + " INTEGER,"
                + intLineAuthUserID + " INTEGER,"
                + strPartNumber + " VARCHAR(255),"
                + strLocation + " VARCHAR(255),"
                + strPartDesc + " VARCHAR(255),"
                + decBuyQtyRemain + " VARCHAR(255),"
                + decBuyQtyScanned + " INTEGER,"
                + strInvField4 + " VARCHAR(255),"
                + strInvField5 + " VARCHAR(255),"
                + Barcode + " VARCHAR(255));";

        private static final String DROP_PO_LINE_TABLE = "DROP TABLE IF EXISTS " + PO_LINE_TABLE_NAME;


        public DatabaseHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, VERSION_NUMBER);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //Create table:
            try {
                db.execSQL(CREATE_VENDORS_TABLE);
                db.execSQL(CREATE_PO_HEADER_TABLE);
                db.execSQL(CREATE_PO_LINE_TABLE);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(DROP_VENDORS_TABLE);
                db.execSQL(DROP_PO_HEADER_TABLE);
                db.execSQL(DROP_PO_LINE_TABLE);
                onCreate(db);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
