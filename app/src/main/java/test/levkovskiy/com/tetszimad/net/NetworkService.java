package test.levkovskiy.com.tetszimad.net;


import java.util.List;

import rx.Observable;
import test.levkovskiy.com.tetszimad.net.model.AnimalModel;

public class NetworkService implements INetworkService {
    private INetworkService mInetworkService;

    public NetworkService() {
        mInetworkService = INetworkService.retrofit.create(INetworkService.class);
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
