package placeDataBase.pservice;

import android.app.Activity;


import com.client.maks.client.Place;

import java.util.List;


import database.service.core.Service;
import placeDataBase.PlaceResource;
import placeDataBase.pdao.PlaceDao;
import placeDataBase.pservice.core.OpenPlaceDBServie;

/**
 * Created by Egor on 07.11.2016.
 */

public class PlaceService extends OpenPlaceDBServie implements Service<Place> {
    // КОРРЕКТНО ИСПОЛЬЗУЕТ ФУНКЦИИ С USERDAO, ПРОВЕРЯЯ ОТКРЫТА ЛИ БАЗА ДАННЫХ, ПОСЛЕ РАБОТЫ С НЕЙ ЗАКРЫВАЯ ЕЕ
    // ВСЯ РАБОТА С БАЗАМИ ДАННЫХ ОСУЩЕСТВЛЯЕТСЯ ЧЕРЕЗ НЕГО
    // НАСЛЕДУЕТ ПОЛЕ DATABASE ОТ ОТЦА, В КОТОРОМ ХРАНИТСЯ НАША БАЗА ДАННЫХ
    private Activity activity;

    public PlaceService(Activity activity) {
        this.activity = activity;
    }

    @Override
    public long save(Place place) {
        try {
            if (!isOpen()) {// ХРАНИТСЯ ЛИ БАЗА ДАННЫХ В ПОЛЕ
                open(activity);
            }
            return new PlaceDao(getSqLiteDatabase()).save(place);//GET - (ПОЛУЧАЕМ ЕЕ ЧЕРЕЗ DBHelper)
        } finally {
            if (isOpen())
                close();
        }
    }

    @Override
    public List getAll() {
        try {
            if (!isOpen()) {
                open(activity);
            }
            return new PlaceDao(getSqLiteDatabase()).getAll();
        } finally {
            if (isOpen())
                close();
        }
    }
}
