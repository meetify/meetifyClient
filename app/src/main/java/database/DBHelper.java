package database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Maks on 25.09.2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    //СОЗДАЕТ БАЗУ ДАННЫХ, ИСПОЛЬЗУЯ ЗАПРОС, НАПИСАНЫЙ В Resource
    public DBHelper(Context context) {
        super(context, Resource.DB_NAME, null, Resource.DB_VER);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Resource.Friend.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}

