package hu.renesans.eredmenyek.network.matches;

import java.util.List;

import hu.renesans.eredmenyek.model.Match;
import hu.renesans.eredmenyek.model.MatchHeader;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MatchesApi {
    /**
     * Finds matches by tournament
     *
     * @param id tournament ID
     * @return Call<List<MatchHeader>>
     */
    @GET("matches/byTournament")
    Call<List<MatchHeader>> findMatchesByTournament(@Query("id") Long id);

    /**
     * Gets match by ID
     *
     * @param id match ID
     * @return Call<Match>
     */
    @GET("matches/{id}")
    Call<Match> getMatch(@Path("id") Integer id);

    /**
     * Finds matches by team
     *
     * @param id team ID
     * @return Call<List<MatchHeader>>
     */
    @GET("matches/byTeam")
    Call<List<MatchHeader>> findMatchesByTeam(@Query("id") Long id);
}
