package com.test.org.testforckassa;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private ItemTouchHelper.Callback createHelperCallback() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,0) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        moveItem(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                        return true;
                    }
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                    }

                };

                return simpleItemTouchCallback;
    }

    private void moveItem(int oldPosition, int newPosition) { //старая позиция не меняется, а новая меняется и меняет все за собой
        ListItem itemOld = (ListItem) listItems.get(oldPosition);
//        System.out.println("Olditem="+itemOld+" OldPos="+oldPosition);
//        ListItem itemNew = (ListItem) listItems.get(newPosition);
//        System.out.println("NewItem="+itemNew+" NewPos="+newPosition);
//        dbHelp = new DBHelp(this);
//        dbHelp.getWritableDatabase();
        listItems.remove(oldPosition);
        listItems.add(newPosition,itemOld);
        adapter.notifyItemMoved(oldPosition,newPosition);
//            if (newPosition-oldPosition!=0) {
//                dbHelp.updateARow(itemNew.getId(), itemOld.getHead(), itemOld.getDescription());
//                System.out.println("IdNew=" + itemNew.getId());
//                dbHelp.updateARow(itemOld.getId(), itemNew.getHead(), itemNew.getDescription());//косяки тут
//                System.out.println("IdOld=" + itemOld.getId());
//
//            }
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
            Intent intent = new Intent(this,DetailActivity.class);
            intent.putExtra("Action","Insert");
            startActivity(intent);
        }
        if (item.getItemId()==R.id.massItemDelete){
            isInDeleteMode = false;
            toolbar.getMenu().clear();
            toolbar.inflateMenu(R.menu.menu_main);
            for (ListItem listItem : selectionList){
                dbHelp.deleteARow(listItem.getId());
                listItems.remove(listItem);
            }
            adapter.notifyDataSetChanged();
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_USER);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onResume() {
        super.onResume();
        onCreateMainList();
        if (isInDeleteMode) {
            massDeletePrepare();
        }
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
    public void massDeletePrepare(){
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_delete);
        isInDeleteMode = true;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        adapter.notifyDataSetChanged();
    }
}

