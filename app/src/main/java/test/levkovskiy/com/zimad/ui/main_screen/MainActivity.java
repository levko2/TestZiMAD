package test.levkovskiy.com.zimad.ui.main_screen;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.levkovskiy.com.zimad.R;
import test.levkovskiy.com.zimad.ui.BaseActivity;

public class MainActivity extends BaseActivity {

    @BindView(R.id.sliding_tabs)
    TabLayout tabLayout;


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
                    CatsFragment catsFragment = CatsFragment.newInstance();
                    replaceFragment(catsFragment, "catFragment");

                } else if (tabLayout.getSelectedTabPosition() == 1) {
                    // dogs fragment
                    DogsFragment dogsFragment = DogsFragment.newInstance();
                    replaceFragment(dogsFragment, "dogFragment");
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

    private void replaceFragment(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, tag).commit();

    }
}
