package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Maks on 04.09.2016.
 */
public class ClientDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "clientDatabase";
    private static final int DB_VERSION = 1;

    public ClientDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE DRINK (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "LOGIN TEXT," +
                "FRIENDS TEXT," +
                "PLACES TEXT," +
                "ACTIONS TEXT");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
