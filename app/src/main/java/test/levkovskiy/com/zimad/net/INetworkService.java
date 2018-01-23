package test.levkovskiy.com.zimad.net;


import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import rx.Observable;
import test.levkovskiy.com.zimad.net.model.AnimalModel;

public interface INetworkService {


    @GET("xim/api.php?query=dog")
    Observable<AnimalModel> getDogs();

    @GET("xim/api.php?query=cat")
    Observable<AnimalModel> getCats();
}
