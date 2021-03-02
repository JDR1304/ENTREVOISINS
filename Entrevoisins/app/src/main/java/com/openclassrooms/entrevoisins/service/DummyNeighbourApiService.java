package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getFavorites() {
        List<Neighbour> favoriteNeighbours = new ArrayList<>();
        for (int i = 0; i < neighbours.size(); i++) {
            if (neighbours.get(i).isFavorite()) {
                favoriteNeighbours.add(neighbours.get(i));
            }
        }
        return favoriteNeighbours;
    }

    @Override
    public Neighbour getNeighbourById(long id) {
        int i =0;
        while(id != neighbours.get(i).getId()){
            i++;
        }
        return neighbours.get(i);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     *
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }
}
