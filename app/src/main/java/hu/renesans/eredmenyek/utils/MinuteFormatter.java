package hu.renesans.eredmenyek.utils;

import android.content.Context;
import android.support.annotation.StringRes;

import hu.renesans.eredmenyek.R;
import hu.renesans.eredmenyek.model.Period;

public class MinuteFormatter {
    private static String getMinuteStr(int minute, int end) {
        return (minute <= end) ? Integer.toString(minute) : end + "+" + (minute - end);
    }

    private static String formatMinute(Context context, String minuteStr) {
        return context.getString(R.string.format_minute, minuteStr);
    }

    private static String formatMinute(Context context, @StringRes int periodId, String minuteStr) {
        return context.getString(R.string.format_period_minute, context.getString(periodId), minuteStr);
    }

    private static String formatMinute(Context context, @StringRes int periodId, String minuteStr, boolean includePeriod) {
        return includePeriod ? formatMinute(context, periodId, minuteStr) : formatMinute(context, minuteStr);
    }

    public static String formatMinute(Context context, Period period, int minute, boolean includePeriod) {
        switch (period) {
            case FIRST_HALF:
                return formatMinute(context, R.string.first_half, getMinuteStr(minute, 45), includePeriod);
            case SECOND_HALF:
                return formatMinute(context, R.string.second_half, getMinuteStr(minute, 90), includePeriod);
            case FIRST_EXTRA:
                return formatMinute(context, R.string.first_extra, getMinuteStr(minute, 105), includePeriod);
            case SECOND_EXTRA:
                return formatMinute(context, R.string.second_extra, getMinuteStr(minute, 120), includePeriod);
            case PENALTIES:
                return context.getString(includePeriod ? R.string.penalties : R.string.penalties_short);
            default:
                return formatMinute(context, Integer.toString(minute));
        }
    }
}
