package test.levkovskiy.com.tetszimad.net;


import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import rx.Observable;
import test.levkovskiy.com.tetszimad.net.model.AnimalModel;

public interface INetworkService {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://kot3.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(new Gson()))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();

    @GET("xim/api.php?query=dog")
    Observable<AnimalModel> getDogs();

    @GET("xim/api.php?query=cat")
    Observable<AnimalModel> getCats();
}
