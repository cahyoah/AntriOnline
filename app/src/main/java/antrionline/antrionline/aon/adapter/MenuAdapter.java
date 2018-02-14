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
import antrionline.antrionline.aon.data.model.Menu;

/**
 * Created by adhit on 29/01/2018.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private Context context;
    private List<Menu> MenuList;
    private OnDetailListener onDetailListener;

    public MenuAdapter(Context context){
        this.context = context;
    }

    public void setData(List<Menu> MenuList){
        this.MenuList = MenuList;
        notifyDataSetChanged();
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, final int position) {
        final Menu Menu = MenuList.get(position);
        holder.tvNameMenu.setText(Menu.getNameMenu());
        holder.imgMenu.setImageResource(Menu.getImageMenu());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDetailListener.onItemDetailClicked(Menu.getNameMenu());
            }
        });
    }

    public void setOnClickDetailListener(OnDetailListener onDetailListener){
        this.onDetailListener = onDetailListener;
    }

    @Override
    public int getItemCount() {
        if(MenuList == null) return 0;
        return MenuList.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameMenu;
        ImageView imgMenu;

        public MenuViewHolder(View itemView) {
            super(itemView);
            imgMenu = itemView.findViewById(R.id.img_menu);
            tvNameMenu = itemView.findViewById(R.id.tv_name_menu);
        }
    }
    public interface OnDetailListener{
        void onItemDetailClicked(String menu);
    }
}