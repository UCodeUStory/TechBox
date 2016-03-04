package com.ustory.techbox.views;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.administrator.test2.R;
import com.ustory.techbox.core.BaseAppCompatActivity;

import java.util.HashMap;
import java.util.Map;


public class NavigationViewActivity extends BaseAppCompatActivity {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    protected HashMap<Integer, MenuItem> mMenuItems;
    public static int[] menuIds = new int[]{
            R.id.item_one,
            R.id.item_two,
            R.id.item_three,
            R.id.item_four,
            R.id.item_five,
            R.id.item_six,
            R.id.item_seven,
            R.id.item_eight,
            R.id.item_nine,
            R.id.item_ten,
            R.id.item_eleven,
            R.id.exit
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.navigation_view;
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle(getResources().getString(R.string.main_pager));
        setSupportActionBar(mToolbar);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.open, R.string.close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
    }

    @Override
    protected void initData() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new NewMessageFragment()).commit();
        // 初始化MenuItems
        mMenuItems = new HashMap<>();
        if (menuIds.length > 0) {
            for (int id : menuIds) {
                MenuItem menuItem = this.mNavigationView.getMenu().findItem(id);
                if (menuItem != null) this.mMenuItems.put(id, menuItem);
            }
        }
    }

    @Override
    protected void initListeners() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItemChecked(menuItem.getItemId());
                return true;
            }
        });
    }
    protected boolean menuItemChecked(int itemId) {
        MenuItem old = null;
        MenuItem now;
        if (this.mMenuItems.containsKey(itemId)) {
            for (Map.Entry<Integer, MenuItem> entry : this.mMenuItems.entrySet()) {
                MenuItem menuItem = entry.getValue();
                if (menuItem.isChecked()) {
                    old = menuItem;
                }

                if (old != null && old.getItemId() == itemId)
                    break;
                if (menuItem.getItemId() == itemId) {
                    now = menuItem;
                    menuItem.setChecked(true);
                    this.onMenuItemOnClick(now);
                } else {
                    menuItem.setChecked(false);
                }

            }
            this.mDrawerLayout.closeDrawer(this.mNavigationView);
            return true;
        } else {
            return false;
        }
    }

    protected void onMenuItemOnClick(MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.item_one:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new NewMessageFragment()).commit();
                mToolbar.setTitle(getResources().getString(R.string.main_pager));
                break;
            case R.id.item_two:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new VolleyDemoFragment()).commit();
                mToolbar.setTitle("Volley案例分析");
                break;
            case R.id.exit:
                finish();
                break;
        }
    }
}
