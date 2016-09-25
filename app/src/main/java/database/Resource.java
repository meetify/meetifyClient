package database;

/**
 * Created by Maks on 25.09.2016.
 */
public class Resource {
    public static final String DB_NAME = "db_my_adapter";
    public static final int DB_VER = 1;
    public static final class Friend {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String IMAGE = "image";
        public static final String TABLE_NAME = "friends";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT(255), " + IMAGE + " INTEGER);";

    }
}




