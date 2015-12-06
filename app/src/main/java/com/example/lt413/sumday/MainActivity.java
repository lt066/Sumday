package com.example.lt413.sumday;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Handler handler;
    private boolean isExit= false;

    private Message_view message;
    private Contact_view contact;
    private News_view news;
    private Setting_view setting;

    private View bottom1;
    private View bottom2;
    private View bottom3;
    private View bottom4;

    private ImageView bottom1_img;
    private ImageView bottom2_img;
    private ImageView bottom3_img;
    private ImageView bottom4_img;

    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                isExit = false;
            }
        };
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        initView();
        fragmentManager = getSupportFragmentManager();
        SelectView(0);

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
            startActivity(new Intent(this,SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            Exit();
            return true;
        }

        return false;
    }

    public void Exit()
    {
        if(!isExit)
        {
            isExit = true;
            Toast.makeText(getApplicationContext(),"再按一次退出",Toast.LENGTH_SHORT).show();
            handler.sendEmptyMessageDelayed(0,2000);
        }
        else
        {
            finish();
            System.exit(0);
        }
    }

    private void initView()
    {


        bottom1 = findViewById(R.id.bottom1);
        bottom2 = findViewById(R.id.bottom2);
        bottom3 = findViewById(R.id.bottom3);
        bottom4 = findViewById(R.id.bottom4);

        bottom1_img = (ImageView) findViewById(R.id.bottom1_img);
        bottom2_img = (ImageView) findViewById(R.id.bottom2_img);
        bottom3_img = (ImageView) findViewById(R.id.bottom3_img);
        bottom4_img = (ImageView) findViewById(R.id.bottom4_img);

        bottom1.setOnClickListener(this);
        bottom2.setOnClickListener(this);
        bottom3.setOnClickListener(this);
        bottom4.setOnClickListener(this);


    }

    private void ClearView()
    {
        bottom1_img.setImageResource(R.drawable.message_unselected);
        bottom2_img.setImageResource(R.drawable.contacts_unselected);
        bottom3_img.setImageResource(R.drawable.news_unselected);
        bottom4_img.setImageResource(R.drawable.setting_unselected);
    }

    private void SelectView(int Index)
    {
        ClearView();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        HideFragment(transaction);
        switch (Index)
        {
            case 0:
                bottom1_img.setImageResource(R.drawable.message_selected);
                if(message ==null)
                {

                    message = new Message_view();
                    transaction.add(R.id.frameLayout,message);
                }
                else
                {
                    transaction.show(message);
                }
                break;
            case 1:
                bottom2_img.setImageResource(R.drawable.contacts_selected);
                if(contact ==null)
                {

                    contact = new Contact_view();
                    transaction.add(R.id.frameLayout,contact);
                }
                else
                {
                    transaction.show(contact);
                }
                break;
            case 2:
                bottom3_img.setImageResource(R.drawable.news_selected);
                if(news==null)
                {

                    news = new News_view();
                    transaction.add(R.id.frameLayout,news);
                }
                else
                {
                    transaction.show(news);
                }
                break;
            case 3:
                bottom4_img.setImageResource(R.drawable.setting_selected);
                if(setting ==null)
                {

                    setting = new Setting_view();
                    transaction.add(R.id.frameLayout,setting);
                }
                else
                {
                    transaction.show(setting);
                }
                break;
            default:
                break;
        }
        transaction.commit();

    }

    private void HideFragment(FragmentTransaction transaction)
    {
        if(message != null)
        {
            transaction.hide(message);
        }
        if(contact != null)
        {
            transaction.hide(contact);
        }
        if(setting != null)
        {
            transaction.hide(setting);
        }
        if(news != null)
        {
            transaction.hide(news);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bottom1:
                SelectView(0);
                break;
            case R.id.bottom2:
                SelectView(1);
                break;
            case R.id.bottom3:
                SelectView(2);
                break;
            case R.id.bottom4:
                SelectView(3);
                break;
            default:
                break;
        }
    }
}
