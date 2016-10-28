package io.github.loihd.materialdemo.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.loihd.materialdemo.R;
import io.github.loihd.materialdemo.items.ItemFragment;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.tab_layout) TabLayout tabLayout;
  @BindView(R.id.appbar) AppBarLayout appbar;
  @BindView(R.id.view_pager) ViewPager viewPager;
  @BindView(R.id.coordinator_layout) CoordinatorLayout coordinatorLayout;
  @BindView(R.id.navigation_view) NavigationView navigationView;
  @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;

  public static Intent getIntent(Activity activity) {
    return new Intent(activity, MainActivity.class);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    setupToolbar();
    setupNavigationView();

    viewPager.setAdapter(new Adapter(getSupportFragmentManager()));
    tabLayout.setupWithViewPager(viewPager);
  }

  private void setupToolbar() {
    setSupportActionBar(toolbar);
    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  private void setupNavigationView() {
    navigationView.setNavigationItemSelectedListener(
        new NavigationView.OnNavigationItemSelectedListener() {
          @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            item.setChecked(true);
            drawerLayout.closeDrawers();
            return true;
          }
        });
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        drawerLayout.openDrawer(GravityCompat.START);
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  static class Adapter extends FragmentPagerAdapter {
    final String[] TITLES = {
        "Tab 1", "Tab2"
    };

    public Adapter(FragmentManager fm) {
      super(fm);
    }

    @Override public Fragment getItem(int position) {
      return ItemFragment.newInstance();
    }

    @Override public int getCount() {
      return TITLES.length;
    }

    @Override public CharSequence getPageTitle(int position) {
      return TITLES[position];
    }
  }
}
