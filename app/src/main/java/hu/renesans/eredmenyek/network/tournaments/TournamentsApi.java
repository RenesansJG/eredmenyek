package hu.renesans.eredmenyek.network.tournaments;

import java.util.List;

import hu.renesans.eredmenyek.model.CategoryWithTournaments;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TournamentsApi {
    /**
     * Gets all tournaments
     *
     * @return Call<List<CategoryWithTournaments>>
     */
    @GET("tournaments")
    Call<List<CategoryWithTournaments>> getTournaments();
}
