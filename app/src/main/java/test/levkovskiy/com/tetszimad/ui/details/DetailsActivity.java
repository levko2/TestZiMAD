package test.levkovskiy.com.tetszimad.ui.details;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.levkovskiy.com.tetszimad.R;
import test.levkovskiy.com.tetszimad.net.model.AnimalModel;
import test.levkovskiy.com.tetszimad.ui.BaseActivity;

import static test.levkovskiy.com.tetszimad.ui.main_screen.FirstFragment.OBJECT;
import static test.levkovskiy.com.tetszimad.ui.main_screen.FirstFragment.POSITION;
import static test.levkovskiy.com.tetszimad.ui.main_screen.FirstFragment.TYPE;

public class DetailsActivity extends BaseActivity {

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_subtitle)
    TextView tvSubtitle;
    int currentType = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_details);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            AnimalModel.DataBean dataBean = getIntent().getParcelableExtra(OBJECT);
            Picasso.with(this).load(dataBean.getUrl()).into(image);
            tvTitle.setText(dataBean.getTitle());
            tvSubtitle.setText(String.valueOf(getIntent().getIntExtra(POSITION, 0)));
            currentType = getIntent().getIntExtra(TYPE, 0);
        }
    }


}
