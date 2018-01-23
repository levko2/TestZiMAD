package test.levkovskiy.com.zimad.net;


import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import test.levkovskiy.com.zimad.net.model.AnimalModel;

public class NetworkService implements INetworkService {
    private INetworkService mInetworkService;


    public NetworkService() {


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://kot3.com/").client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mInetworkService = retrofit.create(INetworkService.class);
    }

    @Override
    public Observable<AnimalModel> getDogs() {
        return mInetworkService.getDogs();
    }

    @Override
    public Observable<AnimalModel> getCats() {
        return mInetworkService.getCats();
    }
}
