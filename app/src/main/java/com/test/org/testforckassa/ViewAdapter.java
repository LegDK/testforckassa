package com.test.org.testforckassa;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.test.org.testforckassa.models.ListItem;

import java.util.List;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;
    private String id;
    private RecyclerView.Adapter adapter;
    public ViewAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
        this.adapter = adapter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ListItem listItem = listItems.get(position);
        holder.textViewHead.setText(listItem.getHead());
        holder.textViewDescription.setText(listItem.getDescription());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private Context context;
        public TextView textViewHead;
        public TextView textViewDescription;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewHead = (TextView) itemView.findViewById(R.id.textViewHead);
            textViewDescription = (TextView) itemView.findViewById(R.id.textViewDesc);
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Context context = v.getContext();

                    Intent intent = new Intent(context, ActivityDetail.class);
                    //intent.putExtra("Id",listItems.get(getAdapterPosition()).getId());
//                    intent.putExtra("Head",listItems.get(getAdapterPosition()).getHead());
//                    intent.putExtra("Description",listItems.get(getAdapterPosition()).getDescription());
                    intent.putExtra("Access","Read");
                    context.startActivity(intent);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(context, view);
                    popupMenu.getMenuInflater().inflate(R.menu.menu_context,popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.itemChange:
                                    Intent intent = new Intent(context, ActivityDetail.class);
                                    //intent.putExtra("Id",listItems.get(getAdapterPosition()).getId());
//                                    intent.putExtra("Head",listItems.get(getAdapterPosition()).getHead());
//                                    intent.putExtra("Description",listItems.get(getAdapterPosition()).getDescription());
                                    intent.putExtra("Access","Read");
                                    context.startActivity(intent);
                                    break;
                                case R.id.itemDelete:
                                    listItems.remove(listItems.get(getAdapterPosition()));
                                    adapter.notifyDataSetChanged();

                            }
                            return true;
                        }
                    });
                    return true;
                }
            });
        }

    }
}
