package hu.renesans.eredmenyek.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.robolectric.annotation.Config;

import hu.renesans.eredmenyek.BuildConfig;
import hu.renesans.eredmenyek.model.Match;
import hu.renesans.eredmenyek.ui.details.DetailsPresenter;
import hu.renesans.eredmenyek.ui.details.DetailsScreen;
import hu.renesans.eredmenyek.utils.RobolectricDaggerTestRunner;

import static hu.renesans.eredmenyek.TestHelper.setTestInjector;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class DetailsTest {
    private DetailsPresenter detailsPresenter;

    @Mock
    DetailsScreen detailsScreen;

    @Captor
    ArgumentCaptor<Match> matchCaptor;

    @Before
    public void setup() {
        initMocks(this);
        setTestInjector();
        detailsPresenter = new DetailsPresenter();
        detailsPresenter.attachScreen(detailsScreen);
    }

    @Test
    public void testGetMatch() {
        detailsPresenter.getMatch(1);

        verify(detailsScreen).showMatch(matchCaptor.capture());
        assertEquals(Integer.valueOf(1), matchCaptor.getValue().getId());
    }

    @After
    public void tearDown() {
        detailsPresenter.detachScreen();
    }
}
