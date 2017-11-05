package com.test.org.testforckassa;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;


import com.test.org.testforckassa.models.ListItem;

import java.util.List;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;
    private MainActivity mainActivity;
    public ViewAdapter(List<ListItem> listItems, Context context, MainActivity mainActivity) {
        this.listItems = listItems;
        this.context = context;
        this.mainActivity = mainActivity;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v,mainActivity);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ListItem listItem = listItems.get(position);
        holder.textViewHead.setText(listItem.getId()+listItem.getHead());
        holder.textViewDescription.setText(listItem.getDescription());
        if (!mainActivity.isInDeleteMode){
            holder.checkbox.setVisibility(View.GONE);
        } else {
            holder.checkbox.setVisibility(View.VISIBLE);
            holder.checkbox.setChecked(false);
        }
    }
    @Override
    public int getItemCount() {
        return listItems.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewHead;
        public TextView textViewDescription;
        private MainActivity mainActivity;
        private CheckBox checkbox;
        public ViewHolder(View itemView, final MainActivity mainActivity) {
            super(itemView);
            textViewHead = (TextView) itemView.findViewById(R.id.textViewHead);
            textViewDescription = (TextView) itemView.findViewById(R.id.textViewDesc);
            checkbox = (CheckBox) itemView.findViewById(R.id.check_list_item);
            checkbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mainActivity.prepareSelections(view,getAdapterPosition());
                }
            });
            this.mainActivity = mainActivity;
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (!mainActivity.isInDeleteMode) {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, ActivityDetail.class);
                        intent.putExtra("Head", listItems.get(getAdapterPosition()).getHead());
                        intent.putExtra("Description", listItems.get(getAdapterPosition()).getDescription());
                        intent.putExtra("Action", "Read");
                        context.startActivity(intent);
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(final View view) {
                    if (!mainActivity.isInDeleteMode) {
                        PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                        popupMenu.getMenuInflater().inflate(R.menu.menu_context, popupMenu.getMenu());
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.itemChange:
                                        Intent intent = new Intent(view.getContext(), ActivityDetail.class);
                                        intent.putExtra("Id", listItems.get(getAdapterPosition()).getId());
                                        intent.putExtra("Head", listItems.get(getAdapterPosition()).getHead());
                                        intent.putExtra("Description", listItems.get(getAdapterPosition()).getDescription());
                                        intent.putExtra("Action", "Update");
                                        view.getContext().startActivity(intent);
                                        break;
                                    case R.id.itemDelete:
                                        mainActivity.massDeletePrepare();
                                }
                                return true;
                            }
                        });
                        popupMenu.show();
                    }
                    return true;

                    }

            });
        }
    }



}
