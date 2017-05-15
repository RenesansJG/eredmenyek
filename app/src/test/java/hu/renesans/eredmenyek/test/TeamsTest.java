package hu.renesans.eredmenyek.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.robolectric.annotation.Config;

import java.util.List;

import hu.renesans.eredmenyek.BuildConfig;
import hu.renesans.eredmenyek.model.CategoryWithTeams;
import hu.renesans.eredmenyek.model.Team;
import hu.renesans.eredmenyek.repository.MemoryRepository;
import hu.renesans.eredmenyek.ui.items.ItemsScreen;
import hu.renesans.eredmenyek.ui.teams.TeamsPresenter;
import hu.renesans.eredmenyek.utils.RobolectricDaggerTestRunner;

import static hu.renesans.eredmenyek.TestHelper.setTestInjector;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class TeamsTest {
    private TeamsPresenter teamsPresenter;

    @Mock
    ItemsScreen<Team> teamsScreen;

    @Captor
    ArgumentCaptor<List<CategoryWithTeams>> teamsCaptor;

    @Captor
    ArgumentCaptor<List<Team>> favoritesCaptor;

    @Captor
    ArgumentCaptor<Boolean> showFavoritesOnlyCaptor;

    @Before
    public void setup() throws Exception {
        initMocks(this);
        setTestInjector();
        teamsPresenter = new TeamsPresenter();
        teamsPresenter.attachScreen(teamsScreen);
    }

    @Test
    public void testGetItems() {
        teamsPresenter.getItems();

        verify(teamsScreen).showItems(teamsCaptor.capture());
        assertTrue(teamsCaptor.getValue().size() > 0);
    }

    @Test
    public void testGetFavorites() {
        teamsPresenter.getFavorites();

        verify(teamsScreen).showFavorites(favoritesCaptor.capture());
        assertTrue(favoritesCaptor.getValue().size() == MemoryRepository.teams.size());
    }

    @Test
    public void testSaveAndRemoveFavorite() {
        Team test = new Team(100L, "Test", "");
        hu.renesans.eredmenyek.model.database.Team t = test.toDbObject();

        assertFalse(MemoryRepository.teams.contains(t));

        teamsPresenter.saveFavorite(test);

        verify(teamsScreen).favoriteSaved(test);
        assertTrue(MemoryRepository.teams.contains(t));

        teamsPresenter.removeFavorite(test);
        verify(teamsScreen).favoriteRemoved(test);
        assertFalse(MemoryRepository.teams.contains(t));
    }

    @Test
    public void testShowFavoritesOnly() {
        verify(teamsScreen).showFavoritesOnlyChanged(showFavoritesOnlyCaptor.capture());
        assertFalse(showFavoritesOnlyCaptor.getValue());

        teamsPresenter.setShowFavoritesOnly(true);

        verify(teamsScreen).showFavoritesOnlyChanged(true);
    }

    @After
    public void tearDown() {
        teamsPresenter.detachScreen();
    }
}
