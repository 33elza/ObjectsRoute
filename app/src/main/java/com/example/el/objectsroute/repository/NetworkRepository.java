package com.example.el.objectsroute.repository;

import com.example.el.objectsroute.dataclass.ObjectVisitation;
import com.example.el.objectsroute.dataclass.Response;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by el on 01.03.2018.
 */

public class NetworkRepository implements INetworkRepository {
    private static final NetworkRepository ourInstance = new NetworkRepository();

    public static NetworkRepository getInstance() {
        return ourInstance;
    }

    private NetworkRepository() {
    }

    public Single<Response<List<ObjectVisitation>>> loadObjects() {

        final List<ObjectVisitation> visitationList = new ArrayList<>();

        visitationList.add(new ObjectVisitation(1, false, "Лицей при УлГТУ", "Ульяновск, ул. Радищева, 102", 54.332058f, 48.401308f, "обычный", "Планово-предупредительная работа", 15, ""));
        visitationList.add(new ObjectVisitation(2, false, "Высшее Военное Инженерное Училище Связи", "Ульяновск, ул. Тухачевского, 19", 54.334951f, 48.401568f, "обычный", "Поверка", 180, "Инструменты для поверки"));
        visitationList.add(new ObjectVisitation(3, false, "Ульяновскводоканал", "Ульяновск, ул. Островского, 6", 54.325067f, 48.394854f, "срочный", "Устранение проблемы передачи данных", 30, "Ключ"));
        visitationList.add(new ObjectVisitation(4, false, "ТЦ Вертикаль", "г.Ульяновск, ул. Рябикова, 21Б/49", 54.285229f, 48.303221f, "обычный", "Планово-предупредительная работа", 15, ""));
        visitationList.add(new ObjectVisitation(5, false, "УлГТУ", "г.Ульяновск, ул. Северный Венец, 32", 54.351513f, 48.389595f, "срочный", "Поверка", 180, "Инструменты для поверки"));
        visitationList.add(new ObjectVisitation(6, false, "МБОУ Средняя школа № 37", "г.Ульяновск, Западный бул., 20А", 54.299379f, 48.320508f, "обычный", "Планово-предупредительная работа", 15, ""));
        visitationList.add(new ObjectVisitation(7, false, "Ульяновский строительный колледж", "г.Ульяновск, ул. Любови Шевцовой, 57", 54.354886f, 48.383350f, "обычный", "Планово-предупредительная работа", 15, ""));
        visitationList.add(new ObjectVisitation(8, false, "Гидроаппарат", "г.Ульяновск, Московское ш., 9", 54.300061f, 48.290994f, "обычный", "Планово-предупредительная работа", 15, ""));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Single.just(new Response<List<ObjectVisitation>>(visitationList));
    }

    @Override
    public Single<Response> visitObject(ObjectVisitation object) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Single.just(new Response());
    }
}
