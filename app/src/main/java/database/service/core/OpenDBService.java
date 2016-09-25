package database.service.core;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import database.DBHelper;

/**
 * Created by Maks on 25.09.2016.
 */
public class OpenDBService {
    //ЗАНИМАЕТСЯ ОТКРЫТИЕМ/ЗАКРЫТИЕМ БАЗЫ ДАННЫХ
    private DBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    public SQLiteDatabase getSqLiteDatabase() {
        return sqLiteDatabase;
    }

    protected boolean isOpen() {
        return sqLiteDatabase != null && dbHelper != null && sqLiteDatabase.isOpen();
    }

    protected void open(Activity activity) {
        if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
            dbHelper = new DBHelper(activity);
            sqLiteDatabase = dbHelper.getWritableDatabase();
        }
    }

    protected void close() {
        if (dbHelper != null) {
            dbHelper.close();
        }
    }
}