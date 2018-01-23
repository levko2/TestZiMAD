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
    CatsFragment catsFragment;
    DogsFragment dogsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        catsFragment = CatsFragment.newInstance();
        dogsFragment = DogsFragment.newInstance();
        if (savedInstanceState == null) {

            tabLayout.addOnTabSelectedListener(listener);
            tabLayout.addTab(tabLayout.newTab().setText("Cats"));
            tabLayout.addTab(tabLayout.newTab().setText("Dogs"));
        } else {
            int selected = savedInstanceState.getInt("selectedTab", 0);
            if (selected == 0) {
                tabLayout.addTab(tabLayout.newTab().setText("Cats"), true);
                tabLayout.addTab(tabLayout.newTab().setText("Dogs"));
            } else if (selected == 1) {
                tabLayout.addTab(tabLayout.newTab().setText("Cats"));
                tabLayout.addTab(tabLayout.newTab().setText("Dogs"), true);
            }

            tabLayout.addOnTabSelectedListener(listener);

        }
    }

    TabLayout.OnTabSelectedListener listener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {

            if (tabLayout.getSelectedTabPosition() == 0) {
                // cats fragment

                replaceFragment(catsFragment, "catsFragment");


            } else if (tabLayout.getSelectedTabPosition() == 1) {
                // dogs fragment

                replaceFragment(dogsFragment, "dogsFragment");

            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
        }
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("selectedTab", tabLayout.getSelectedTabPosition());
    }

    private void replaceFragment(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, tag).commit();

    }

    private void attachFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().attach(fragment).commit();

    }

    private void detachFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().detach(fragment).commit();

    }
}
