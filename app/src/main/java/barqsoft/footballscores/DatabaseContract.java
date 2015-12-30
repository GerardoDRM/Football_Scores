package barqsoft.footballscores;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.format.Time;

/**
 * Created by yehya khaled on 2/25/2015.
 */
public class DatabaseContract
{
    public static final String SCORES_TABLE = "scores_table";

    // To make it easy to query for the exact date, we normalize all dates that go into
    // the database to the start of the the Julian day at UTC.
    public static long normalizeDate(long startDate) {
        // normalize the start date to the beginning of the (UTC) day
        Time time = new Time();
        time.set(startDate);
        int julianDay = Time.getJulianDay(startDate, time.gmtoff);
        return time.setJulianDay(julianDay);
    }
    public static final class scores_table implements BaseColumns
    {
        //Table data
        public static final String LEAGUE_COL = "league";
        public static final String DATE_COL = "date";
        public static final String TIME_COL = "time";
        public static final String HOME_COL = "home";
        public static final String AWAY_COL = "away";
        public static final String HOME_GOALS_COL = "home_goals";
        public static final String AWAY_GOALS_COL = "away_goals";
        public static final String MATCH_ID = "match_id";
        public static final String MATCH_DAY = "match_day";

        //public static Uri SCORES_CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH)
                //.build();

        //Types
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;

        public static Uri buildScoreWithLeague()
        {
            return BASE_CONTENT_URI.buildUpon().appendPath("league").build();
        }
        public static Uri buildScoreWithId()
        {
            return BASE_CONTENT_URI.buildUpon().appendPath("id").build();
        }
        public static Uri buildScoreWithDate()
        {
            return BASE_CONTENT_URI.buildUpon().appendPath("date").build();
        }
        public static Uri buildScoreWithStartDate(long date)
        {
            return CONTENT_URI_GAME.buildUpon().appendPath(Long.toString(normalizeDate(date))).build();
        }


        public static long getDateFromUri(Uri uri) {
            return Long.parseLong(uri.getPathSegments().get(1));
        }

    }
    //URI data
    public static final String CONTENT_AUTHORITY = "barqsoft.footballscores";
    public static final String PATH = "scores";
    public static final String PATH_MATCH = "match";
    public static Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);
    public static final Uri CONTENT_URI_GAME =
            BASE_CONTENT_URI.buildUpon().appendPath(PATH_MATCH).build();
}
