package com.test.org.testforckassa;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.test.org.testforckassa.models.ListItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;
    private List<ListItem> selectionList;
    private LinearLayoutManager linearLayoutManager;
    private DBHelp dbHelp;
    private SQLiteDatabase sqLiteDatabase;
    public boolean isInDeleteMode = false;
    public Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbHelp=new DBHelp(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        listItems = new ArrayList<ListItem>();
        selectionList = new ArrayList<ListItem>();
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        onCreateMainList();
        dbHelp.close();
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);
        if (item.getItemId()==R.id.icon_plus){
            Intent intent = new Intent(this,ActivityDetail.class);
            intent.putExtra("Action","Insert");
            startActivity(intent);
        }
        if (item.getItemId()==R.id.massItemDelete){
            isInDeleteMode = false;
            toolbar.getMenu().clear();
            toolbar.inflateMenu(R.menu.menu_main);
            dbHelp = new DBHelp(this);
            for (ListItem listItem : selectionList){
                dbHelp.deleteARow(listItem.getId());
                listItems.remove(listItem);
            }
            dbHelp.close();
            // бд прикрути и красавчик
            adapter.notifyDataSetChanged();


        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onResume() {
        super.onResume();
        onCreateMainList();
    }
    public void onCreateMainList(){
        listItems = dbHelp.getDataFromDB();
        adapter = new ViewAdapter(listItems,getApplicationContext(),MainActivity.this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
    public void prepareSelections(View view,int position){
        if (((CheckBox)view).isChecked()){
            selectionList.add(listItems.get(position));
        }
        else{
            selectionList.remove(listItems.get(position));
        }
    }
}

