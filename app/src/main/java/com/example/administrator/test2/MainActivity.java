package com.example.administrator.test2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sim.activity.TestCoordinatorLayoutActivity;
import com.sim.activity.TestNavigationViewActivity;
import com.sim.activity.WelcomeActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout nameTextInputLayout;
    private TextInputLayout passwordTextInputLayout;
    private EditText userNameEdit;
    private EditText passwordEdit;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameTextInputLayout = (TextInputLayout)findViewById(R.id.username_til);
        passwordTextInputLayout = (TextInputLayout)findViewById(R.id.password_til);
        userNameEdit = (EditText)findViewById(R.id.username_edit);
        passwordEdit = (EditText)findViewById(R.id.password_edit);
        login = (Button)findViewById(R.id.okbut);
        login.setOnClickListener(this);
        userNameEdit.setText("ustory");
        passwordEdit.setText("123245678");
        findViewById(R.id.back).setOnClickListener(this);

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
        Log.i("qiyue","userName="+userNameEdit.getText());
        Log.i("qiyue","v.getId()"+v.getId());
        if (v.getId()==R.id.okbut){
            if("1".equals(userNameEdit.getText().toString())){
                //否则隐藏上次错误
                Log.i("qiyue","loginSuccess");
                nameTextInputLayout.setErrorEnabled(false);
                startActivity(new Intent(this, WelcomeActivity.class));
                Snackbar.make(MainActivity.this.findViewById(R.id.lyj_layout), "登录成功", Snackbar.LENGTH_SHORT).show();
            }else if("ustory".equals(userNameEdit.getText().toString())){
                startActivity(new Intent(this, TestNavigationViewActivity.class));
                Snackbar.make(MainActivity.this.findViewById(R.id.lyj_layout), "登录成功", Snackbar.LENGTH_SHORT).show();
                finish();
            }else if("3".equals(userNameEdit.getText().toString())){
                startActivity(new Intent(this, TestCoordinatorLayoutActivity.class));
                Snackbar.make(MainActivity.this.findViewById(R.id.lyj_layout), "登录成功", Snackbar.LENGTH_SHORT).show();
            }else{
                nameTextInputLayout.setError("username is not exist");
            }
        }else if(v.getId()==R.id.back){
            finish();
        }
    }
}
