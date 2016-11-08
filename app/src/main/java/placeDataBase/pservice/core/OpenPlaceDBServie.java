package placeDataBase.pservice.core;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;

import placeDataBase.PlaceDBHelper;


/**
 * Created by Egor on 07.11.2016.
 */

public class OpenPlaceDBServie {
    //ЗАНИМАЕТСЯ ОТКРЫТИЕМ/ЗАКРЫТИЕМ БАЗЫ ДАННЫХ
    private PlaceDBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    public SQLiteDatabase getSqLiteDatabase() {
        return sqLiteDatabase;
    }

    protected boolean isOpen() {
        return sqLiteDatabase != null && dbHelper != null && sqLiteDatabase.isOpen();
    }

    protected void open(Activity activity) {
        if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
            dbHelper = new PlaceDBHelper(activity);
            sqLiteDatabase = dbHelper.getWritableDatabase();
        }
    }

    protected void close() {
        if (dbHelper != null) {
            dbHelper.close();
        }
    }
}
