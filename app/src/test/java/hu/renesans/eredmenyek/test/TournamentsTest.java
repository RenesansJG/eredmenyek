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
import hu.renesans.eredmenyek.model.CategoryWithTournaments;
import hu.renesans.eredmenyek.model.Tournament;
import hu.renesans.eredmenyek.repository.MemoryRepository;
import hu.renesans.eredmenyek.ui.items.ItemsScreen;
import hu.renesans.eredmenyek.ui.tournaments.TournamentsPresenter;
import hu.renesans.eredmenyek.utils.RobolectricDaggerTestRunner;

import static hu.renesans.eredmenyek.TestHelper.setTestInjector;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class TournamentsTest {
    private TournamentsPresenter tournamentsPresenter;

    @Mock
    ItemsScreen<Tournament> tournamentsScreen;

    @Captor
    ArgumentCaptor<List<CategoryWithTournaments>> tournamentsCaptor;

    @Captor
    ArgumentCaptor<List<Tournament>> favoritesCaptor;

    @Captor
    ArgumentCaptor<Boolean> showFavoritesOnlyCaptor;

    @Before
    public void setup() throws Exception {
        initMocks(this);
        setTestInjector();
        tournamentsPresenter = new TournamentsPresenter();
        tournamentsPresenter.attachScreen(tournamentsScreen);
    }

    @Test
    public void testGetItems() {
        tournamentsPresenter.getItems();

        verify(tournamentsScreen).showItems(tournamentsCaptor.capture());
        assertTrue(tournamentsCaptor.getValue().size() > 0);
    }

    @Test
    public void testGetFavorites() {
        tournamentsPresenter.getFavorites();

        verify(tournamentsScreen).showFavorites(favoritesCaptor.capture());
        assertTrue(favoritesCaptor.getValue().size() == MemoryRepository.tournaments.size());
    }

    @Test
    public void testSaveAndRemoveFavorite() {
        Tournament test = new Tournament(100L, "Test", "");
        hu.renesans.eredmenyek.model.database.Tournament t = test.toDbObject();

        assertFalse(MemoryRepository.tournaments.contains(t));

        tournamentsPresenter.saveFavorite(test);

        verify(tournamentsScreen).favoriteSaved(test);
        assertTrue(MemoryRepository.tournaments.contains(t));

        tournamentsPresenter.removeFavorite(test);
        verify(tournamentsScreen).favoriteRemoved(test);
        assertFalse(MemoryRepository.tournaments.contains(t));
    }

    @Test
    public void testShowFavoritesOnly() {
        verify(tournamentsScreen).showFavoritesOnlyChanged(showFavoritesOnlyCaptor.capture());
        assertFalse(showFavoritesOnlyCaptor.getValue());

        tournamentsPresenter.setShowFavoritesOnly(true);

        verify(tournamentsScreen).showFavoritesOnlyChanged(true);
    }

    @After
    public void tearDown() {
        tournamentsPresenter.detachScreen();
    }
}
