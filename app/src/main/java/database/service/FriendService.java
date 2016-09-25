package database.service;

import android.app.Activity;

import com.client.maks.client.Friend;
import database.dao.FriendDao;
import database.service.core.OpenDBService;
import database.service.core.Service;

import java.util.List;

/**
 * Created by Maks on 25.09.2016.
 */
public class FriendService extends OpenDBService implements Service<Friend> {
    // КОРРЕКТНО ИСПОЛЬЗУЕТ ФУНКЦИИ С USERDAO, ПРОВЕРЯЯ ОТКРЫТА ЛИ БАЗА ДАННЫХ, ПОСЛЕ РАБОТЫ С НЕЙ ЗАКРЫВАЯ ЕЕ
    // ВСЯ РАБОТА С БАЗАМИ ДАННЫХ ОСУЩЕСТВЛЯЕТСЯ ЧЕРЕЗ НЕГО
    // НАСЛЕДУЕТ ПОЛЕ DATABASE ОТ ОТЦА, В КОТОРОМ ХРАНИТСЯ НАША БАЗА ДАННЫХ
    private Activity activity;

    public FriendService(Activity activity) {
        this.activity = activity;
    }

    @Override
    public long save(Friend friend) {
        try {
            if (!isOpen()) {// ХРАНИТСЯ ЛИ БАЗА ДАННЫХ В ПОЛЕ
                open(activity);
            }
            return new FriendDao(getSqLiteDatabase()).save(friend);//GET - (ПОЛУЧАЕМ ЕЕ ЧЕРЕЗ DBHelper)
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
            return new FriendDao(getSqLiteDatabase()).getAll();
        } finally {
            if (isOpen())
                close();
        }
    }
}

