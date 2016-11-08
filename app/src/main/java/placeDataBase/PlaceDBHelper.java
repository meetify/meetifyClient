package placeDataBase;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import database.Resource;

/**
 * Created by Egor on 07.11.2016.
 */

public class PlaceDBHelper extends SQLiteOpenHelper {
    //СОЗДАЕТ БАЗУ ДАННЫХ, ИСПОЛЬЗУЯ ЗАПРОС, НАПИСАНЫЙ В Resource
    public PlaceDBHelper(Context context) {
        super(context, PlaceResource.DB_NAME, null, PlaceResource.DB_VER);
    }

    public PlaceDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public PlaceDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PlaceResource.Place.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
