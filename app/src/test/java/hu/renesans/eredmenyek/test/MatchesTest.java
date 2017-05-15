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
import hu.renesans.eredmenyek.model.MatchHeader;
import hu.renesans.eredmenyek.ui.matches.MatchesPresenter;
import hu.renesans.eredmenyek.ui.matches.MatchesScreen;
import hu.renesans.eredmenyek.utils.RobolectricDaggerTestRunner;

import static hu.renesans.eredmenyek.TestHelper.setTestInjector;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MatchesTest {
    private MatchesPresenter matchesPresenter;

    @Mock
    MatchesScreen matchesScreen;

    @Captor
    ArgumentCaptor<List<MatchHeader>> matchesCaptor;

    @Before
    public void setup() {
        initMocks(this);
        setTestInjector();
        matchesPresenter = new MatchesPresenter();
        matchesPresenter.attachScreen(matchesScreen);
    }

    @Test
    public void testFindByTournament() {
        matchesPresenter.findMatchesByTournament(8L);

        verify(matchesScreen).showMatches(matchesCaptor.capture());
        assertTrue(matchesCaptor.getValue().size() > 0);
    }

    @Test
    public void testFindByTeam() {
        matchesPresenter.findMatchesByTeam(17L);

        verify(matchesScreen).showMatches(matchesCaptor.capture());
        assertTrue(matchesCaptor.getValue().size() > 0);
    }

    @After
    public void tearDown() {
        matchesPresenter.detachScreen();
    }
}
