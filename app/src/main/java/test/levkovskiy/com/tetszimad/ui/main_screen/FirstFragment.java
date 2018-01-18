package test.levkovskiy.com.tetszimad.ui.main_screen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import test.levkovskiy.com.tetszimad.R;
import test.levkovskiy.com.tetszimad.net.NetworkService;
import test.levkovskiy.com.tetszimad.net.model.AnimalModel;
import test.levkovskiy.com.tetszimad.ui.details.DetailsActivity;


public class FirstFragment extends Fragment {
    public static final int CAT_TYPE = 0;
    public static final int DOG_TYPE = 1;
    public static final String TYPE = "TYPE";
    public static final String OBJECT = "object";
    public static final String POSITION = "position";
    NetworkService service;
    Adapter adapter;
    int currentType = 0;
    @BindView(R.id.rv_animals)
    RecyclerView rvAnimals;
    Unbinder unbinder;

    private Parcelable listState;
    private String LIST_STATE_KEY = "list_state";

    public static FirstFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt(TYPE, type);
        FirstFragment fragment = new FirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        unbinder = ButterKnife.bind(this, view);
        service = new NetworkService();
        adapter = new Adapter(new ArrayList<AnimalModel.DataBean>(), getActivity(), new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra(OBJECT, adapter.getItem(pos));
                intent.putExtra(TYPE, currentType);
                intent.putExtra(POSITION, pos);
                startActivity(intent);
            }
        });
        rvAnimals.setAdapter(adapter);
        currentType = getArguments().getInt(TYPE, 0);
        if (currentType == CAT_TYPE) {
            Subscription subscription = service.getCats()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<AnimalModel>() {
                        @Override
                        public void call(AnimalModel animalModelWebResponse) {
                            adapter.addAll(animalModelWebResponse.getData());
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            Log.e("error", throwable.getMessage());
                        }
                    });

        } else if (currentType == DOG_TYPE) {
            Subscription subscription = service.getDogs()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<AnimalModel>() {
                        @Override
                        public void call(AnimalModel animalModelWebResponse) {
                            adapter.addAll(animalModelWebResponse.getData());
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            Log.e("error", throwable.getMessage());
                        }
                    });

        }
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        listState = rvAnimals.getLayoutManager().onSaveInstanceState();
        outState.putParcelable(LIST_STATE_KEY, listState);
        super.onSaveInstanceState(outState);


    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        rvAnimals.getLayoutManager().onRestoreInstanceState(listState);
        if (savedInstanceState != null)
            listState = savedInstanceState.getParcelable(LIST_STATE_KEY);


    }

    @Override
    public void onResume() {
        super.onResume();
        if (listState != null) {
            rvAnimals.getLayoutManager().onRestoreInstanceState(listState);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
