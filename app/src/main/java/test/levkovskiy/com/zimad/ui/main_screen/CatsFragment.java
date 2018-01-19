package test.levkovskiy.com.zimad.ui.main_screen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import rx.schedulers.Schedulers;
import test.levkovskiy.com.zimad.R;
import test.levkovskiy.com.zimad.net.NetworkService;
import test.levkovskiy.com.zimad.ui.details.DetailsActivity;


public class CatsFragment extends Fragment {
    public static final String OBJECT = "object";
    public static final String POSITION = "position";
    NetworkService service;
    Adapter adapter;

    @BindView(R.id.rv_animals)
    RecyclerView rvAnimals;
    Unbinder unbinder;

    private Parcelable listState;
    private String LIST_STATE_KEY = "list_state";

    public static CatsFragment newInstance() {
        Bundle args = new Bundle();
        CatsFragment fragment = new CatsFragment();
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

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new Adapter(new ArrayList<>(), pos -> {
            Intent intent = new Intent(getActivity(), DetailsActivity.class);
            intent.putExtra(OBJECT, adapter.getItem(pos));
            intent.putExtra(POSITION, pos);
            startActivity(intent);
        });
        rvAnimals.setAdapter(adapter);

        Subscription subscription = service.getCats()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(animalModelWebResponse -> adapter.addAll(animalModelWebResponse.getData()), throwable -> Log.e("error", throwable.getMessage()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvAnimals.getLayoutManager().onRestoreInstanceState(listState);
        if (savedInstanceState != null)
            listState = savedInstanceState.getParcelable(LIST_STATE_KEY);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        listState = rvAnimals.getLayoutManager().onSaveInstanceState();
        outState.putParcelable(LIST_STATE_KEY, listState);
        super.onSaveInstanceState(outState);


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
