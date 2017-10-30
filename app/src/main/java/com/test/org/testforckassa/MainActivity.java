package com.test.org.testforckassa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.test.org.testforckassa.models.ListItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DBHelp dbHelp;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;
    private LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelp=new DBHelp(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        listItems = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ViewAdapter(listItems,getApplicationContext());
        recyclerView.setAdapter(adapter);
        ListItem item = new ListItem("Head","Description","ID");
        listItems.add(item);
        adapter.notifyDataSetChanged();

    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);
        if (item.getItemId()==R.id.icon_plus){
            Intent intent = new Intent(this,ActivityDetail.class);
            intent.putExtra("Access","Write");
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
