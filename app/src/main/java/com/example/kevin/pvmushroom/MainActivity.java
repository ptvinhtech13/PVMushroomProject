package com.example.kevin.pvmushroom;

import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.style.TypefaceSpan;
import android.util.TypedValue;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kevin.pvmushroom.Adapters.AdapterTabStrip;
import com.example.kevin.pvmushroom.DanhSachStatic.DS_NAM;
import com.example.kevin.pvmushroom.DanhSachStatic.Ds_Fragment;
import com.example.kevin.pvmushroom.DiaglogKH.DFrag_DanhSachKH;
import com.example.kevin.pvmushroom.Object.Nam;
import com.example.kevin.pvmushroom.XuLy.XL_NghiepVu;

public class MainActivity extends AppCompatActivity  {

    Typeface tf;
    TextView tex1, tex2, tex3, tex4;
    boolean flagsTex1234 = false;

    DFrag_DanhSachKH dsKH;
    ViewPager pager;
    PagerTabStrip tab_strp;
    com.github.clans.fab.FloatingActionMenu fab;

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0){
            getSupportFragmentManager().popBackStack();

            Ds_Fragment.setFrag_current(Ds_Fragment.getFrag_current() - 1);
            DS_NAM.RemoveViewToHSVTimeline();
            DS_NAM.RemoveViewToHSVTimeline();
            if(DS_NAM.getLy_ContainerHSV().getChildCount() == 1)
            {
                DS_NAM.getLy_ContainerHSV().removeAllViews();
                DS_NAM.createTextView(getApplicationContext(), "YEARS", true);
            }


        }
        // Default action on back pressed
        else super.onBackPressed();
        /*if(Ds_Fragment.getFrag_current() == 1)
        {

            Ds_Fragment.getItemList(0).CallFrag(0, true);
        }
        else
            super.onBackPressed();*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // [Optional] Power your app with Local Datastore. For more info, go to
        // https://parse.com/docs/android/guide#local-datastore
        /*Parse.enableLocalDatastore(this);
        Parse.initialize(this);*/



        XL_NghiepVu xl_nghiepVu = new XL_NghiepVu(getApplicationContext());
        xl_nghiepVu.KhoiDongDuLieu();

        /*new AlertDialog.Builder(this)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this entry?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();*/

        /*ParseQuery<ParseObject> query = ParseQuery.getQuery("KHACH_HANG");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, com.parse.ParseException e) {
                Toast.makeText(getApplicationContext(),list.size() + " ",Toast.LENGTH_LONG).show();
            }
        });*/



        /*TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        Date date  = new Date();

        Toast.makeText(this,date.toString(),Toast.LENGTH_LONG).show();
        ParseObject testObject = new ParseObject("PHIEU_THU");
        testObject.put("ID", "0");
        testObject.put("ID_KH", "1");
        testObject.put("NGAY_MUA", date.toString());
        testObject.put("SO_LUONG", 10);
        testObject.put("DON_GIA", 40000);
        testObject.saveInBackground();

        ParseObject testObject2 = new ParseObject("PHIEU_THU");
        testObject2.put("ID", "2");
        testObject2.put("ID_KH", "0");
        testObject2.put("NGAY_MUA", date.toString());
        testObject2.put("SO_LUONG", 40);
        testObject2.put("DON_GIA", 50000);
        testObject2.saveInBackground();*/

        /*final ParseQuery<ParseObject> objectParseQuery = ParseQuery.getQuery("KHACH_HANG");
        objectParseQuery.getInBackground("NFokL72HYt", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, com.parse.ParseException e) {
                if (e == null) {
                    // object will be your game score
                    Toast.makeText(getApplication(),parseObject.getString("ID") + " " +  parseObject.getString("HoTen"),Toast.LENGTH_LONG).show();
                } else {
                    // something went wrong
                    Toast.makeText(getApplication(),"Failed",Toast.LENGTH_LONG).show();

                }
            }
        });*/

        tf = Typeface.createFromAsset(getAssets(), "SanFranciscoDisplay-Light.otf");


        DS_NAM.setFm(getSupportFragmentManager());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.drawable.logo);


        /*fab = (com.github.clans.fab.FloatingActionMenu) findViewById(R.id.fab_menu);
        fab.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_fab_start));

        final com.github.clans.fab.FloatingActionButton butfab= (com.github.clans.fab.FloatingActionButton) findViewById(R.id.menu_item2);
        butfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                butfab.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_fab_start));
            }
        });*/
        final com.github.clans.fab.FloatingActionButton butfab= (com.github.clans.fab.FloatingActionButton) findViewById(R.id.menu_item);

        butfab.setColorNormalResId(R.color.fab_menu_2);
        butfab.setColorPressedResId(R.color.fab_menu_1);

        final DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                if(item.getItemId() == R.id.nav_DS_KH)
                {
                    dsKH = new DFrag_DanhSachKH();
                    dsKH.show(getFragmentManager(),"Danh Sach Khach Hang");


                }
                else if(item.getItemId() == R.id.nav_gallery)
                {
                    Toast.makeText(MainActivity.this, "GALLERY", Toast.LENGTH_SHORT).show();
                }
                if (flagsTex1234 == false) {
                    tex1 = (TextView) findViewById(R.id.tex1);
                    tex1.setTypeface(tf);
                    tex2 = (TextView) findViewById(R.id.tex2);
                    tex2.setTypeface(tf);
                    tex3 = (TextView) findViewById(R.id.tex3);
                    tex3.setTypeface(tf);
                    tex4 = (TextView) findViewById(R.id.tex4);
                    tex4.setTypeface(tf);
                    flagsTex1234 = false;
                }

                TypefaceSpan face = new TypefaceSpan("SanFranciscoDisplay-Light.otf"); // OR  THIS
                SpannableStringBuilder title = new SpannableStringBuilder(item.getTitle());
                title.setSpan(face, 0, title.length(), 0);
                item.setTitle(title);

                item.setCheckable(false);
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });


        AdapterTabStrip mapager = new AdapterTabStrip(getSupportFragmentManager());
        pager=(ViewPager)findViewById(R.id.pager);
        pager.setAdapter(mapager);
        tab_strp=(PagerTabStrip)findViewById(R.id.tab_strip);
        tab_strp.setTextColor(getResources().getColor(R.color.colorAccent));
        tab_strp.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);

        tab_strp.setDrawFullUnderline(true);
        tab_strp.setTabIndicatorColor(getResources().getColor(R.color.colorAccent));

        Typeface fontTypeFace= Typeface.createFromAsset(this.getAssets(),
                "SanFranciscoDisplay-Light.otf");
        for (int i = 0; i < tab_strp.getChildCount(); ++i) {
            View nextChild = tab_strp.getChildAt(i);
            if (nextChild instanceof TextView) {
                TextView textViewToConvert = (TextView) nextChild;
                textViewToConvert.setTypeface(fontTypeFace);
            }
            else
                Toast.makeText(this,"Failed " + i,Toast.LENGTH_SHORT).show();
        }

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



}
