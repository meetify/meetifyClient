package placeDataBase.pdao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.client.maks.client.Place;

import java.util.ArrayList;
import java.util.List;


import database.dao.core.Dao;
import placeDataBase.PlaceResource;

/**
 * Created by Egor on 07.11.2016.
 */

public class PlaceDao implements Dao<Place> {
    //РЕАЛИЗАЦИЯ ФУНКЦИЙ ДЛЯ РАБОТЫ С БАЗОЙ ДАННЫХ, ПРОПИСАНЫХ ИНТЕРФЕЙСЕ
    //ПОЛУЧАЕТ КУРСОР С ВСЕМИ ЗАПИСЯМИ, ПАРСИТ ЕГО
    //СОХРАНЯЕТ, ПРЕДОСТАВЛЯЕТ ДАННЫЕ С БД
    SQLiteDatabase sqLiteDatabase;

    public PlaceDao(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public long save(Place place) {
        return sqLiteDatabase.insert(PlaceResource.Place.TABLE_NAME, null, setPlace(place));
    }

    @Override
    public List<Place> getAll() {
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + PlaceResource.Place.TABLE_NAME, null);
        return parseCursor(cursor);
    }

    @Override
    public List<Place> parseCursor(Cursor cursor) {
        List<Place> places = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndex(PlaceResource.Place.ID));
                String name = cursor.getString(cursor.getColumnIndex(PlaceResource.Place.NAME));
                byte[] image = cursor.getBlob(cursor.getColumnIndex(PlaceResource.Place.IMAGE));
                //Bitmap image = BitmapFactory.decodeByteArray(b, 0, b.length);
                places.add(new Place(name, image));
            } while (cursor.moveToNext());
        }
        return places;
    }

    private ContentValues setPlace(Place place) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PlaceResource.Place.NAME, place.getName());
        contentValues.put(PlaceResource.Place.IMAGE, place.getImage());
        return contentValues;
    }
}
