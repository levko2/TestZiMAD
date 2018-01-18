package test.levkovskiy.com.tetszimad.ui.main_screen;

import android.os.Bundle;
import android.support.design.widget.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.levkovskiy.com.tetszimad.R;
import test.levkovskiy.com.tetszimad.ui.BaseActivity;

public class MainActivity extends BaseActivity {

    @BindView(R.id.sliding_tabs)
    TabLayout tabLayout;
    private FirstFragment firstFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tabLayout.getSelectedTabPosition() == 0) {
                    // cats fragment
                    firstFragment = FirstFragment.newInstance(FirstFragment.CAT_TYPE);
                    replaceFragment("catFragment");

                } else if (tabLayout.getSelectedTabPosition() == 1) {
                    // dogs fragment
                    firstFragment = FirstFragment.newInstance(FirstFragment.DOG_TYPE);
                    replaceFragment("dogFragment");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.addTab(tabLayout.newTab().setText("Send"), true);
        tabLayout.addTab(tabLayout.newTab().setText("Send & Post"));

    }

    private void replaceFragment(String tag) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, firstFragment, tag).commit();

    }
}
