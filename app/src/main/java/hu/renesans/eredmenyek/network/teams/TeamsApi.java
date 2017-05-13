package hu.renesans.eredmenyek.network.teams;

import java.util.List;

import hu.renesans.eredmenyek.model.CategoryWithTeams;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TeamsApi {
    /**
     * Gets all teams
     *
     * @return Call<List<CategoryWithTeams>>
     */
    @GET("teams")
    Call<List<CategoryWithTeams>> getTeams();
}
