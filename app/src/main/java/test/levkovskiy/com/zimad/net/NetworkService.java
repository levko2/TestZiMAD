package test.levkovskiy.com.zimad.net;


import rx.Observable;
import test.levkovskiy.com.zimad.net.model.AnimalModel;

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
