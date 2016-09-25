package database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.client.maks.client.Friend;
import database.dao.core.Dao;
import database.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maks on 25.09.2016.
 */
public class FriendDao implements Dao<Friend> {
    //РЕАЛИЗАЦИЯ ФУНКЦИЙ ДЛЯ РАБОТЫ С БАЗОЙ ДАННЫХ, ПРОПИСАНЫХ ИНТЕРФЕЙСЕ
    //ПОЛУЧАЕТ КУРСОР С ВСЕМИ ЗАПИСЯМИ, ПАРСИТ ЕГО
    //СОХРАНЯЕТ, ПРЕДОСТАВЛЯЕТ ДАННЫЕ С БД
    SQLiteDatabase sqLiteDatabase;

    public FriendDao(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public long save(Friend friend) {
        return sqLiteDatabase.insert(Resource.Friend.TABLE_NAME, null, setFriend(friend));
    }

    @Override
    public List<Friend> getAll() {
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + Resource.Friend.TABLE_NAME, null);
        return parseCursor(cursor);
    }

    @Override
    public List<Friend> parseCursor(Cursor cursor) {
        List<Friend> friends = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndex(Resource.Friend.ID));
                String name = cursor.getString(cursor.getColumnIndex(Resource.Friend.NAME));
                int image = cursor.getInt(cursor.getColumnIndex(Resource.Friend.IMAGE));
                friends.add(new Friend(name, image));
            } while (cursor.moveToNext());
        }
        return friends;
    }

    private ContentValues setFriend(Friend friend) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Resource.Friend.NAME, friend.getName());
        contentValues.put(Resource.Friend.IMAGE, friend.getImage());
        return contentValues;
    }
}
