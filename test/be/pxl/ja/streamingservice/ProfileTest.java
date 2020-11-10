package be.pxl.ja.streamingservice;

import be.pxl.ja.streamingservice.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.TypeVariable;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileTest {
    private Profile profile;
    private static final Movie MOVIE1 = new Movie("Titanic", Rating.MATURE);
    private static final Movie MOVIE2 = new Movie("Fightclub", Rating.MATURE);
    private static final TVShow TVSHOW = new TVShow("Mr. Robot", Rating.MATURE, 4);

    @BeforeEach
    public void init(){
        profile = new Profile("Test");
    }

    @Test
    public void constructorShouldInitializeCurrentlyWatchingAndRecentlyWatchedAsEmptyArrayLists(){
        assertEquals(new ArrayList<Content>(), profile.getCurrentlyWatching(), "The currentlyWatching ArrayList should be initialized as an empty ArrayList<Content> in the constructor");
        assertEquals(new ArrayList<Content>(), profile.getRecentlyWatched(), "The recentlyWatched ArrayList should be initialized as an empty ArrayList<Content> in the constructor");
    }

    @Test
    public void startedWatchingShouldAddContentToTheCurrentlyWatchingArrayList(){
        int oldCount = profile.getCurrentlyWatching().size();

        profile.startWatching(MOVIE1);
        assertEquals(oldCount + 1, profile.getCurrentlyWatching().size());
        assertTrue(profile.getCurrentlyWatching().contains(MOVIE1));
    }

    @Test
    public void startedWatchingShouldNotAddContentToTheCurrentlyWatchingArrayListWhenContentAlreadyInTheList(){

        profile.startWatching(MOVIE2);
        int oldCount = profile.getCurrentlyWatching().size();
        profile.startWatching(MOVIE2);
        assertEquals(oldCount, profile.getCurrentlyWatching().size());
    }

    @Test
    public void finishedWatchingShouldRemoveContentFromCurrentlyWatchingAndAddItToRecentlyWatched(){
        profile.startWatching(MOVIE1);
        profile.startWatching(MOVIE2);
        profile.startWatching(TVSHOW);
        int oldCurrentlyWatchingCount = profile.getCurrentlyWatching().size();
        int oldRecentlyWatchedCount = profile.getRecentlyWatched().size();

        profile.finishWatching(MOVIE2);

        assertEquals(oldCurrentlyWatchingCount - 1, profile.getCurrentlyWatching().size());
        assertEquals(oldRecentlyWatchedCount + 1, profile.getRecentlyWatched().size());
        assertTrue(profile.getRecentlyWatched().contains(MOVIE2));
        assertFalse(profile.getCurrentlyWatching().contains(MOVIE2));
    }
}
