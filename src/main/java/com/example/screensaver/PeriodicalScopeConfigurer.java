package com.example.screensaver;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;


import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static java.time.LocalTime.now;

public class PeriodicalScopeConfigurer implements Scope {

    Map<String, Pair<LocalTime, Object>> map = new HashMap<>();

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        // Если ключ отсутствует, создаём новый объект и сохраняем его в map
        if (!map.containsKey(name)) {
            map.put(name, new Pair<>(now(), objectFactory.getObject()));
        }

        // Получаем пару из map
        Pair<LocalTime, Object> pair = map.get(name);

        // Проверяем, прошло ли более 5 секунд с момента последнего запроса
        int secondsSinceLastRequest = now().getSecond() - pair.first.getSecond();
        if (secondsSinceLastRequest > 5) {
            // Если прошло больше 5 секунд, создаём новый объект
            map.put(name, new Pair<>(now(), objectFactory.getObject()));
        }

        // Возвращаем объект из пары
        return map.get(name).second;
    }

    @Override
    public Object remove(String name) {
        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return "";
    }
}
