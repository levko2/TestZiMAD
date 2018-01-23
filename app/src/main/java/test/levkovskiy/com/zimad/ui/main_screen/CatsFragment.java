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

import static test.levkovskiy.com.zimad.Constants.ITEMS;
import static test.levkovskiy.com.zimad.Constants.OBJECT;
import static test.levkovskiy.com.zimad.Constants.POSITION;


public class CatsFragment extends Fragment {

    NetworkService service;
    Adapter adapter;
    @BindView(R.id.rv_animals)
    RecyclerView rvAnimals;
    Unbinder unbinder;


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

        if (adapter == null) {
            adapter = new Adapter(new ArrayList<>(), getActivity(), pos -> {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra(OBJECT, adapter.getItem(pos));
                intent.putExtra(POSITION, pos);
                startActivity(intent);
            });

        }
        rvAnimals.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState == null) {

            if (adapter.getItems().isEmpty()) {
                Subscription subscription = service.getCats()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(animalModelWebResponse -> adapter.addAll(animalModelWebResponse.getData()), throwable -> Log.e("error", throwable.getMessage()));
            }

        } else {
            adapter.addAll(savedInstanceState.getParcelableArrayList(ITEMS));
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(ITEMS, (ArrayList<? extends Parcelable>) adapter.getItems());
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
