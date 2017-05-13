package hu.renesans.eredmenyek.ui;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.renesans.eredmenyek.R;
import hu.renesans.eredmenyek.model.Category;
import hu.renesans.eredmenyek.model.Item;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static hu.renesans.eredmenyek.utils.AssetHelper.loadImage;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
    private List<Item> items;
    private List<? extends Item> favorites;
    private OnItemClickListener listener;
    private FavoriteListener favoriteListener;

    private ContextThemeWrapper itemContext;
    private ContextThemeWrapper categoryContext;

    public ItemsAdapter(List<Item> items, List<? extends Item> favorites,
                        OnItemClickListener listener, FavoriteListener favoriteListener) {
        this.items = items;
        this.favorites = favorites;
        this.listener = listener;
        this.favoriteListener = favoriteListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context;

        if (viewType == R.id.category) {
            if (categoryContext == null) {
                categoryContext = new ContextThemeWrapper(parent.getContext(), R.style.CategoryRow);
            }

            context = categoryContext;
        } else {
            if (itemContext == null) {
                itemContext = new ContextThemeWrapper(parent.getContext(), R.style.ItemRow);
            }

            context = itemContext;
        }

        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.li_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = items.get(position);

        loadImage(item.getImageUrl(), holder.imageIV);
        holder.nameTV.setText(item.getName());

        if (getItemViewType(position) == R.id.item) {
            holder.containerLL.setOnClickListener(v -> listener.onItemClick(item));

            SimpleTarget<GlideDrawable> st = new SimpleTarget<GlideDrawable>() {
                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                    holder.starIV.setImageDrawable(resource);
                    holder.starIV.setColorFilter(ContextCompat.getColor(holder.starIV.getContext(),
                            R.color.star_tint));
                }
            };

            Item favorite = getFavorite(item);

            if (favorite != null) {
                Glide.with(holder.starIV.getContext())
                        .load(R.drawable.ic_star_white_36dp).into(st);
                holder.starIV.setOnClickListener(v -> favoriteListener.removeFavorite(favorite));
            } else {
                Glide.with(holder.starIV.getContext())
                        .load(R.drawable.ic_star_outline_white_36dp).into(st);
                holder.starIV.setOnClickListener(v -> favoriteListener.saveFavorite(item));
            }

            holder.starIV.setVisibility(VISIBLE);
        } else {
            holder.containerLL.setOnClickListener(null);
            holder.starIV.setOnClickListener(null);
            holder.starIV.setVisibility(GONE);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (items.get(position) instanceof Category) ? R.id.category : R.id.item;
    }

    private Item getFavorite(Item item) {
        for (Item favorite : favorites) {
            if (favorite.getId().equals(item.getId())) {
                return favorite;
            }
        }

        return null;
    }

    public interface OnItemClickListener {
        void onItemClick(Item item);
    }

    public interface FavoriteListener {
        void saveFavorite(Item item);
        void removeFavorite(Item item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.containerLL)
        LinearLayout containerLL;

        @BindView(R.id.imageIV)
        ImageView imageIV;

        @BindView(R.id.nameTV)
        TextView nameTV;

        @BindView(R.id.starIV)
        ImageView starIV;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
