package com.example.el.objectsroute.repository.network;

import com.example.el.objectsroute.dataclass.ObjectVisitation;
import com.example.el.objectsroute.dataclass.http.ObjectVisitationResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by el on 01.03.2018.
 */

public class NetworkRepository implements INetworkRepository {
    private static final NetworkRepository ourInstance = new NetworkRepository();

    private RetrofitService service;

    private NetworkRepository() {
        service = new Retrofit
                .Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
                .create(RetrofitService.class);
    }

    public static NetworkRepository getInstance() {
        return ourInstance;
    }

    public Single<List<ObjectVisitation>> loadObjects() {

        final List<ObjectVisitationResponse> visitationList = new ArrayList<>();

        visitationList.add(new ObjectVisitationResponse(1, false, "Лицей при УлГТУ", "Ульяновск, ул. Радищева, 102", 54.332058f, 48.401308f, "обычный", "Планово-предупредительная работа", 15, ""));
        visitationList.add(new ObjectVisitationResponse(2, false, "Высшее Военное Инженерное Училище Связи", "Ульяновск, ул. Тухачевского, 19", 54.334951f, 48.401568f, "обычный", "Поверка", 180, "Инструменты для поверки"));
        visitationList.add(new ObjectVisitationResponse(3, false, "Ульяновскводоканал", "Ульяновск, ул. Островского, 6", 54.325067f, 48.394854f, "срочный", "Устранение проблемы передачи данных", 30, "Ключ"));
        visitationList.add(new ObjectVisitationResponse(4, false, "ТЦ Вертикаль", "г.Ульяновск, ул. Рябикова, 21Б/49", 54.285229f, 48.303221f, "обычный", "Планово-предупредительная работа", 15, ""));
        visitationList.add(new ObjectVisitationResponse(5, false, "УлГТУ", "г.Ульяновск, ул. Северный Венец, 32", 54.351513f, 48.389595f, "срочный", "Поверка", 180, "Инструменты для поверки"));
        visitationList.add(new ObjectVisitationResponse(6, false, "МБОУ Средняя школа № 37", "г.Ульяновск, Западный бул., 20А", 54.299379f, 48.320508f, "обычный", "Планово-предупредительная работа", 15, ""));
        visitationList.add(new ObjectVisitationResponse(7, false, "Ульяновский строительный колледж", "г.Ульяновск, ул. Любови Шевцовой, 57", 54.354886f, 48.383350f, "обычный", "Планово-предупредительная работа", 15, ""));
        visitationList.add(new ObjectVisitationResponse(8, false, "Гидроаппарат", "г.Ульяновск, Московское ш., 9", 54.300061f, 48.290994f, "обычный", "Планово-предупредительная работа", 15, ""));

        return Single.create(new SingleOnSubscribe<List<ObjectVisitationResponse>>() {
            @Override
            public void subscribe(SingleEmitter<List<ObjectVisitationResponse>> emitter) throws Exception {
                Thread.sleep(5000);
                emitter.onSuccess(visitationList);
                // TODO: 22.03.2018  throw new GoToAuthException();
            }
        }).map(new Function<List<ObjectVisitationResponse>, List<com.example.el.objectsroute.dataclass.ObjectVisitation>>() {
            @Override
            public List<com.example.el.objectsroute.dataclass.ObjectVisitation> apply(List<ObjectVisitationResponse> objectVisitationResponseList) throws Exception {
                return new ObjectsMapper().map(objectVisitationResponseList);
            }
        }).observeOn(Schedulers.io());
    }

    @Override
    public Single<String> visitObject(ObjectVisitation object) {
        return service.visitObject("nikitalevchenko");
    }

    private interface RetrofitService {
        @GET("users/{user}/repos")
        Single<String> visitObject(@Path("user") String user);
    }

    public static class GoToAuthException extends Exception {

        public GoToAuthException() {
        }

        public GoToAuthException(String message) {
            super(message);
        }
    }
}
