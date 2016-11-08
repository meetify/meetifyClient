package placeDataBase;

/**
 * Created by Egor on 07.11.2016.
 */

public class PlaceResource {
    public static final String DB_NAME = "place_db_my_adapter";
    public static final int DB_VER = 1;
    public static final class Place {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String IMAGE = "image";
        public static final String TABLE_NAME = "places";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT(255), " + IMAGE + " BLOB);";

    }
}
