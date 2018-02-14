package antrionline.antrionline.aon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import antrionline.antrionline.aon.R;
import antrionline.antrionline.aon.data.model.AccountMenu;

/**
 * Created by adhit on 04/01/2018.
 */

public class AccountMenuAdapter extends RecyclerView.Adapter<AccountMenuAdapter.AccountMenuViewHolder> {
    private Context context;
    private List<AccountMenu> AccountMenuList;
    private OnCardViewClickListener onCardViewClickListener;

    public AccountMenuAdapter(Context context){
        this.context = context;
    }

    public void setData(List<AccountMenu> AccountMenuList){
        this.AccountMenuList = AccountMenuList;
        notifyDataSetChanged();
    }

    @Override
    public AccountMenuAdapter.AccountMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account_menu, parent, false);
        return new AccountMenuAdapter.AccountMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AccountMenuAdapter.AccountMenuViewHolder holder, int position) {
        final AccountMenu AccountMenu = AccountMenuList.get(position);
        holder.tvNameMenu.setText(AccountMenu.getNameMenu());
        holder.imgMenu.setImageResource(AccountMenu.getImageMenu());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCardViewClickListener.onMenuClicked(AccountMenu.getNameMenu());

            }
        });
    }


    public void setOnClickDetailListener(OnCardViewClickListener onCardViewClickListener){
        this.onCardViewClickListener = onCardViewClickListener;

    }

    @Override
    public int getItemCount() {
        if(AccountMenuList == null) return 0;
        return AccountMenuList.size();
    }

    public class AccountMenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameMenu;
        ImageView imgMenu;

        public AccountMenuViewHolder(View itemView) {
            super(itemView);
            imgMenu = itemView.findViewById(R.id.img_menu);
            tvNameMenu = itemView.findViewById(R.id.tv_name_menu);
        }
    }

    public  interface OnCardViewClickListener{
        void onMenuClicked(String string);
    }
}