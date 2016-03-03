package com.ustory.techbox.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.test2.R;
import com.ustory.techbox.bean.User;
import com.ustory.techbox.core.BaseAppCompatActivity;
import com.ustory.techbox.iviews.MainView;
import com.ustory.techbox.presenter.MainPresenter;

public class MainActivity extends BaseAppCompatActivity implements MainView,View.OnClickListener {

    private TextInputLayout nameTextInputLayout;
    private TextInputLayout passwordTextInputLayout;
    private EditText userNameEdit;
    private EditText passwordEdit;
    private Button login;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        nameTextInputLayout = (TextInputLayout) findViewById(R.id.username_til);
        passwordTextInputLayout = (TextInputLayout) findViewById(R.id.password_til);
        userNameEdit = (EditText) findViewById(R.id.username_edit);
        passwordEdit = (EditText) findViewById(R.id.password_edit);
        login = (Button) findViewById(R.id.okbut);
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initListeners() {
        login.setOnClickListener(this);
        findViewById(R.id.back).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        this.presenter = new MainPresenter();
        this.presenter.attachView(this);
        userNameEdit.setText("ustory");
        passwordEdit.setText("123456");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.okbut) {
            this.presenter.login();
        } else if (v.getId() == R.id.back) {
            finish();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.presenter.detachView();
    }

    @Override
    public String getUserName() {
        return userNameEdit.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordEdit.getText().toString();
    }

    @Override
    public void clearUserName() {
        userNameEdit.setText("");
    }

    @Override
    public void clearPassword() {
        passwordEdit.setText("");
    }

    @Override
    public void showLoading() {
        //显示控件
    }

    @Override
    public void hideLoading() {
        //隐藏控件
    }

    @Override
    public void toWelcomexActivity(User user) {
        Intent intent = new Intent(this,NavigationViewActivity.class);
        intent.putExtra("userName",""+user.getUserName());
        startActivity(intent);
        finish();
    }

    @Override
    public void onFailure(String failedMessage) {
        showToast(failedMessage);
    }
}
