package test.levkovskiy.com.zimad.ui.main_screen;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import test.levkovskiy.com.zimad.databinding.ItemListBinding;
import test.levkovskiy.com.zimad.net.model.AnimalModel;

public class Adapter extends RecyclerView.Adapter<Adapter.AnimalViewHolder> {
    private OnItemClick onClick;
    private List<AnimalModel.DataBean> animals;

    Adapter(List<AnimalModel.DataBean> animals, OnItemClick onItemClick) {
        this.animals = animals;
        this.onClick = onItemClick;

    }

    interface OnItemClick {
        void inItemClicked(int pos);
    }

    AnimalModel.DataBean getItem(int pos) {
        return animals.get(pos);
    }


    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageView, String v) {
        Picasso.with(imageView.getContext()).load(v).into(imageView);
    }

    @Override
    public AnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemListBinding binding = ItemListBinding.inflate(inflater, parent, false);
        return new AnimalViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(AnimalViewHolder holder, int position) {
        AnimalModel.DataBean movie = animals.get(position);
        holder.binding.setAnimal(movie);
        holder.binding.setCount(String.valueOf(position));
        holder.binding.getRoot().setOnClickListener(v -> onClick.inItemClicked(position));

    }

    @Override
    public int getItemCount() {
        return animals.size();
    }

    void addAll(List<AnimalModel.DataBean> animalModelWebResponse) {
        animals.addAll(animalModelWebResponse);
        notifyDataSetChanged();
    }

    static class AnimalViewHolder extends RecyclerView.ViewHolder {
        ItemListBinding binding;

        AnimalViewHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
        }
    }
}
