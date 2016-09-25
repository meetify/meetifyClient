package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.client.maks.client.R;

/**
 * Created by Egor on 04.09.2016.
 */
public class FriendsDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "FRIENDS";
    private static final int DB_VERSION = 1;
    public static final String COLUMN_ID = "_id";
    public static final String NAME = "NAME";
    public static final String IMAGE = "IMAGE";

    public FriendsDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DB_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT, " +
                IMAGE + " INTEGER");
        insertFriend(db, "Никита", R.drawable.friend1);
        insertFriend(db, "Максим", R.drawable.friend2);
        insertFriend(db, "Дима", R.drawable.friend3);
        insertFriend(db, "Никита", R.drawable.friend1);
        insertFriend(db, "Максим", R.drawable.friend2);
        insertFriend(db, "Дима", R.drawable.friend3);
        insertFriend(db, "Никита", R.drawable.friend1);
        insertFriend(db, "Максим", R.drawable.friend2);
        insertFriend(db, "Дима", R.drawable.friend3);
        insertFriend(db, "Никита", R.drawable.friend1);
        insertFriend(db, "Максим", R.drawable.friend2);
        insertFriend(db, "Дима", R.drawable.friend3);
        insertFriend(db, "Никита", R.drawable.friend1);
        insertFriend(db, "Максим", R.drawable.friend2);
        insertFriend(db, "Дима", R.drawable.friend3);
        insertFriend(db, "Никита", R.drawable.friend1);
        insertFriend(db, "Максим", R.drawable.friend2);
        insertFriend(db, "Дима", R.drawable.friend3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static void insertFriend(SQLiteDatabase db, String name,
                                    int resourceId) {
        ContentValues friendsValues = new ContentValues();
        friendsValues.put(NAME, name);
        friendsValues.put(IMAGE, resourceId);
        db.insert(DB_NAME, null, friendsValues);
    }


}
