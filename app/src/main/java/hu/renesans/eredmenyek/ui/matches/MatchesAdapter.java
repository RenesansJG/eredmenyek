package hu.renesans.eredmenyek.ui.matches;

import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.renesans.eredmenyek.R;
import hu.renesans.eredmenyek.model.MatchHeader;

import static hu.renesans.eredmenyek.utils.AssetHelper.loadImage;
import static hu.renesans.eredmenyek.utils.MinuteFormatter.formatMinute;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder> {
    private static final SimpleDateFormat DATE_FORMAT =
            new SimpleDateFormat("MM.dd\nHH:mm", Locale.getDefault());

    private final List<MatchHeader> matches;
    private OnMatchClickListener listener;

    public MatchesAdapter(OnMatchClickListener listener) {
        matches = new ArrayList<>();
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.li_match, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MatchHeader match = matches.get(position);

        holder.dateTimeTV.setText(DATE_FORMAT.format(match.getStartTime()));
        holder.scoreTV.setText(holder.scoreTV.getContext().getString(R.string.format_score,
                match.getHomeScore() != null ? Integer.toString(match.getHomeScore()) : "",
                match.getAwayScore() != null ? Integer.toString(match.getAwayScore()) : ""));
        holder.scoreTV.setTextColor(ContextCompat.getColor(holder.scoreTV.getContext(),
                match.isStarted() ? R.color.live_text : R.color.primary_text));
        holder.scoreTV.setTypeface(null, match.isStarted() ? Typeface.BOLD : Typeface.NORMAL);

        if (match.isStarted()) {
            holder.liveTV.setText(formatMinute(holder.liveTV.getContext(),
                    match.getPeriod(), match.getMinute(), false));
        }

        holder.liveTV.setVisibility(match.isStarted() ? View.VISIBLE : View.INVISIBLE);
        loadImage(match.getHomeTeam().getImageUrl(), holder.homeTeamIV);
        loadImage(match.getAwayTeam().getImageUrl(), holder.awayTeamIV);
        holder.homeTeamTV.setText(match.getHomeTeam().getName());
        holder.awayTeamTV.setText(match.getAwayTeam().getName());
        holder.homeTeamTV.setTypeface(null, !match.isStarted() &&
                match.getHomeScore() != null && match.getAwayScore() != null &&
                match.getHomeScore() > match.getAwayScore() ? Typeface.BOLD : Typeface.NORMAL);
        holder.awayTeamTV.setTypeface(null, !match.isStarted() &&
                match.getHomeScore() != null && match.getAwayScore() != null &&
                match.getHomeScore() < match.getAwayScore() ? Typeface.BOLD : Typeface.NORMAL);
        holder.containerRL.setOnClickListener(v -> listener.onMatchClick(match));
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public void setMatches(List<MatchHeader> matches) {
        this.matches.clear();
        this.matches.addAll(matches);
        Collections.sort(this.matches, (o1, o2) -> {
            if (o1.isStarted() && !o2.isStarted()) {
                return -1;
            } else if (!o1.isStarted() && o2.isStarted()) {
                return 1;
            } else if (o1.getStartTime().after(o2.getStartTime())) {
                return -1;
            } else if (o1.getStartTime().before(o2.getStartTime())) {
                return 1;
            } else {
                return 0;
            }
        });
        notifyDataSetChanged();
    }

    public void clearMatches() {
        this.matches.clear();
        notifyDataSetChanged();
    }

    public interface OnMatchClickListener {
        void onMatchClick(MatchHeader match);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.containerRL)
        RelativeLayout containerRL;

        @BindView(R.id.dateTimeTV)
        TextView dateTimeTV;

        @BindView(R.id.scoreTV)
        TextView scoreTV;

        @BindView(R.id.liveTV)
        TextView liveTV;

        @BindView(R.id.homeTeamIV)
        ImageView homeTeamIV;

        @BindView(R.id.awayTeamIV)
        ImageView awayTeamIV;

        @BindView(R.id.homeTeamTV)
        TextView homeTeamTV;

        @BindView(R.id.awayTeamTV)
        TextView awayTeamTV;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
