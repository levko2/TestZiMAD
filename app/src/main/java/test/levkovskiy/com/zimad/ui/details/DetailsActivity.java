package test.levkovskiy.com.zimad.ui.details;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.levkovskiy.com.zimad.R;
import test.levkovskiy.com.zimad.net.model.AnimalModel;
import test.levkovskiy.com.zimad.ui.BaseActivity;

import static test.levkovskiy.com.zimad.Constants.OBJECT;
import static test.levkovskiy.com.zimad.Constants.POSITION;

public class DetailsActivity extends BaseActivity {

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_subtitle)
    TextView tvSubtitle;


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

        }
    }

}
