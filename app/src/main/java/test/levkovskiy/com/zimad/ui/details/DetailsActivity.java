package test.levkovskiy.com.zimad.ui.details;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import test.levkovskiy.com.zimad.R;
import test.levkovskiy.com.zimad.databinding.ActivityDetailsBinding;
import test.levkovskiy.com.zimad.net.model.AnimalModel;

import static test.levkovskiy.com.zimad.ui.main_screen.CatsFragment.OBJECT;
import static test.levkovskiy.com.zimad.ui.main_screen.CatsFragment.POSITION;

public class DetailsActivity extends AppCompatActivity {


    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageView, String v) {
        Picasso.with(imageView.getContext()).load(v).into(imageView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        if (getIntent() != null) {
            AnimalModel.DataBean dataBean = getIntent().getParcelableExtra(OBJECT);
            binding.setAnimal(dataBean);
            binding.setPosition(String.valueOf(getIntent().getIntExtra(POSITION, 0)));

        }
    }

}
