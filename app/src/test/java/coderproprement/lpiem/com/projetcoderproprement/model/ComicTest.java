package coderproprement.lpiem.com.projetcoderproprement.model;

import android.support.annotation.NonNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import coderproprement.lpiem.com.projetcoderproprement.Model.Comic;
import coderproprement.lpiem.com.projetcoderproprement.Model.ComicCreator;

import static org.junit.Assert.*;

public class ComicTest {
    private Comic comic;
    private ComicCreator firstComicCreator;
    private ComicCreator secondComicCreator;

    @Before
    public void setUp(){
        comic = new Comic("A comic title",
                          "A comic description",
                          "qsd7587",
                          new Date(23,6,1955),
                          5.03f,
                          35,
                          "no image url",
                          new ArrayList<ComicCreator>());

        firstComicCreator = new ComicCreator("Bob","Bon");
        secondComicCreator = new ComicCreator("Bob","Bob");
    }

    @Test
    public void addCreator() {
        addFirstElement();
        List<ComicCreator> comicList = cloningCreatorListTest();

        addFirstElement();

        assertEquals(comic.getComicCreatorsList(),comicList);

        comic.addCreator(secondComicCreator);

        assertNotEquals(comic.getComicCreatorsList(),comicList);
    }

    @NonNull
    private List<ComicCreator> cloningCreatorListTest() {
        return new ArrayList<>(comic.getComicCreatorsList());
    }

    private void addFirstElement() {
        comic.addCreator(firstComicCreator);
    }

    @Test
    public void isCreatorPresent() {
        addFirstElement();
        assertTrue(comic.isCreatorPresent(firstComicCreator));

        assertFalse(comic.isCreatorPresent(secondComicCreator));
    }

    @Test
    public void removeCreator(){
        addFirstElement();
        comic.removeCreator(firstComicCreator);

        assertFalse(comic.isCreatorPresent(firstComicCreator));

        List<ComicCreator> comicList = this.cloningCreatorListTest();

        comic.removeCreator(secondComicCreator);

        assertEquals(comicList,comic.getComicCreatorsList());
    }

    @After
    public void tearDown() throws Exception {

    }
}