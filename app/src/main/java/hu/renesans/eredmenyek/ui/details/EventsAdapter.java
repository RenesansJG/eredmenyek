package hu.renesans.eredmenyek.ui.details;

import android.content.Context;
import android.support.percent.PercentFrameLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.renesans.eredmenyek.R;
import hu.renesans.eredmenyek.model.Event;
import hu.renesans.eredmenyek.model.TeamType;

import static hu.renesans.eredmenyek.utils.MinuteFormatter.formatMinute;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {
    private List<Event> events;

    public EventsAdapter(List<Event> events) {
        this.events = events;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.li_event, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Event event = events.get(position);

        Context context = holder.containerPFL.getContext();
        holder.containerPFL.setBackgroundColor(ContextCompat.getColor(context, position % 2 == 0 ?
                R.color.pressed_row : android.R.color.transparent));
        holder.playerTV.setText(context.getString(R.string.format_event,
                formatMinute(context, event.getPeriod(), event.getMinute(), false),
                event.getPlayer()));
        int gravity = ((event.getTeam() == TeamType.AWAY) ? Gravity.END : Gravity.START) |
                Gravity.CENTER_VERTICAL;
        ((PercentFrameLayout.LayoutParams) holder.playerTV.getLayoutParams()).gravity = gravity;
        holder.playerTV.setGravity(gravity);

        int drawableId;

        switch (event.getType()) {
            case GOAL:
                drawableId = R.drawable.ic_goal_36dp;
                break;
            case GOAL_PENALTY:
                drawableId = R.drawable.ic_goal_penalty_36dp;
                break;
            case GOAL_OWN:
                drawableId = R.drawable.ic_goal_own_36dp;
                break;
            case MISSED_PENALTY:
                drawableId = R.drawable.ic_missed_penalty_36dp;
                break;
            case CARD_YELLOW:
                drawableId = R.drawable.ic_card_yellow_36dp;
                break;
            case CARD_RED:
                drawableId = R.drawable.ic_card_red_36dp;
                break;
            case CARD_SECOND_YELLOW_RED:
                drawableId = R.drawable.ic_card_second_yellow_red_36dp;
                break;
            case SUBSTITUTION:
                drawableId = R.drawable.ic_substitution_36dp;
                break;
            default:
                drawableId = 0;
                break;
        }

        Glide.with(holder.playerTV.getContext()).load(drawableId).into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                if (event.getTeam() == TeamType.AWAY) {
                    TextViewCompat.setCompoundDrawablesRelativeWithIntrinsicBounds(holder.playerTV,
                            null, null, resource, null);
                } else {
                    TextViewCompat.setCompoundDrawablesRelativeWithIntrinsicBounds(holder.playerTV,
                            resource, null, null, null);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.containerPFL)
        PercentFrameLayout containerPFL;

        @BindView(R.id.playerTV)
        TextView playerTV;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
