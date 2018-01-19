package test.levkovskiy.com.zimad.ui.main_screen;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.levkovskiy.com.zimad.R;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.sliding_tabs)
    TabLayout tabLayout;

    CatsFragment catsFragment;
    DogsFragment dogsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tabLayout.addTab(tabLayout.newTab().setText("Cats"));
        tabLayout.addTab(tabLayout.newTab().setText("Dogs"));
        if (savedInstanceState != null) {
            catsFragment = (CatsFragment) getSupportFragmentManager().getFragment(savedInstanceState, "catsFragment");
            dogsFragment = (DogsFragment) getSupportFragmentManager().getFragment(savedInstanceState, "dogsFragment");
        }else {

        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tabLayout.getSelectedTabPosition() == 0) {
                    // cats fragment
                    if (catsFragment == null)
                        catsFragment = CatsFragment.newInstance();
                    replaceFragment(catsFragment, "catFragment");

                } else if (tabLayout.getSelectedTabPosition() == 1) {
                    // dogs fragment
                    if (dogsFragment == null)
                        dogsFragment = DogsFragment.newInstance();
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


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (catsFragment != null && catsFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, "catsFragment", catsFragment);
        }

        if (dogsFragment != null && dogsFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, "dogsFragment", dogsFragment);
        }
    }

    private void replaceFragment(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, tag).commit();

    }
}
