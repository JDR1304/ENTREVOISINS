package com.openclassrooms.entrevoisins.service;


import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
        resetListFavoriteFalse();
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
        resetListFavoriteFalse();
    }

    @Test
    public void getFavoritesWithSuccess() {
        Neighbour favoriteNeighbour = service.getNeighbours().get(0);
        assertFalse(service.getFavorites().contains(favoriteNeighbour));
        favoriteNeighbour.setFavorite(true);
        assertTrue(service.getFavorites().contains(favoriteNeighbour));
        resetListFavoriteFalse();
    }

    @Test
    public void getNeighbourByIdWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        Neighbour neighbour = neighbours.get(1);
        long id = neighbour.getId();
        assertEquals(neighbour, service.getNeighbourById(id));
        resetListFavoriteFalse();
    }

    @Test
    public void createNeighbourWithSuccess() {
        Neighbour neighbourCreated = new Neighbour(13, "Jerome", "https://i.pravatar.cc/150?u=a042581f4e29026704a", "Aix en Provence",
                "+33 6 86 57 90 14", "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..");
        service.createNeighbour(neighbourCreated);
        assertTrue(service.getNeighbours().contains(neighbourCreated));
        resetListFavoriteFalse();
    }

    /**
     * Reset the list to have all the item with with favorite boolean at false
     */

    public void resetListFavoriteFalse(){
        for (int i = 0; i< service.getNeighbours().size(); i++){
            service.getNeighbours().get(i).setFavorite(false);
        }

    }
}
