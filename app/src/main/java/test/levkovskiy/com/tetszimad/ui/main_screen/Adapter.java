package test.levkovskiy.com.tetszimad.ui.main_screen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.levkovskiy.com.tetszimad.R;
import test.levkovskiy.com.tetszimad.net.model.AnimalModel;

public class Adapter extends RecyclerView.Adapter<Adapter.AnimalViewHolder> {
    private List<AnimalModel.DataBean> animals;
    private Context context;
    private OnItemClickListener listener;

    Adapter(List<AnimalModel.DataBean> animals, Context context, OnItemClickListener clickListener) {
        this.animals = animals;
        this.context = context;
        this.listener = clickListener;
    }

    AnimalModel.DataBean getItem(int pos) {
        return animals.get(pos);
    }

    public interface OnItemClickListener {
        void onItemClick(int pos);
    }


    @Override
    public AnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new AnimalViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(AnimalViewHolder holder, int position) {
        AnimalModel.DataBean model = animals.get(position);
        holder.tvTitle.setText(String.valueOf(position));
        holder.tvSubtitle.setText(model.getTitle());
        Picasso.with(context).load(model.getUrl()).fit().into(holder.image);
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
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_subtitle)
        TextView tvSubtitle;

        AnimalViewHolder(View itemView, final OnItemClickListener onItemClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });

        }
    }
}
