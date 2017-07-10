package com.example.rattanak.recyclerviewplaystore.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.rattanak.recyclerviewplaystore.Data.SingleItemModel;
import com.example.rattanak.recyclerviewplaystore.PatternDesign.AppSingleton;
import com.example.rattanak.recyclerviewplaystore.R;

import java.util.ArrayList;

/**
 * Created by rattanak on 6/30/17.
 */

public class SectionListDataAdapter extends RecyclerView.Adapter<SectionListDataAdapter.SingleItemRowHolder> {
     private ArrayList<SingleItemModel> itemsList;
    //SingleItemModel[] itemsList;
    private Context mContext;
    public OnRecyclerviewItemClickListener recyclerViewItemClickListener;

    //set click listener to adapt interface and fragment
    public void setRecyclerViewItemClickListener(OnRecyclerviewItemClickListener itemClickListener){
        this.recyclerViewItemClickListener = itemClickListener;
    }

    public SectionListDataAdapter(Context context, ArrayList<SingleItemModel> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
        //notifyDataSetChanged();
    }

    public void setBook(ArrayList<SingleItemModel> itemsList){
        this.itemsList = itemsList;
        notifyDataSetChanged();
    }

    public  ArrayList<SingleItemModel> getBooks(int position)
    {
        SingleItemModel item = (SingleItemModel)itemsList.get(position);
        return item;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_single_card, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {
        //combine it to single item becasue it is single item click
        SingleItemModel singleItem = itemsList.get(i);
        //array
        //SingleItemModel singleItem = itemsList[i];
        holder.tvTitle.setText(singleItem.getBook_name());
        holder.itemImage.setImageUrl("http://10.0.2.2" + singleItem.getThumbnailUrl(),(AppSingleton.getInstance(mContext).getImageLoader()));

       /* Glide.with(mContext)
                .load(feedItem.getImageURL())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.bg)`
                .into(feedListRowHolder.thumbView);*/
    }

    @Override
    public int getItemCount() {
        //get its size if null return 0 otherwise return size
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tvTitle;

        protected NetworkImageView itemImage;


        public SingleItemRowHolder(View view) {
            super(view);

            this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            this.itemImage = (NetworkImageView) view.findViewById(R.id.itemImage);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                   // Toast.makeText(v.getContext(), tvTitle.getText(), Toast.LENGTH_SHORT).show();
                   // Toast.makeText(v.getContext(), , Toast.LENGTH_SHORT).show();
//                    if(recyclerViewItemClickListener != null){
//                        recyclerViewItemClickListener.onRecyclerViewItemClickListener(getAdapterPosition());
//                    }

                }
            });


        }

    }
}
