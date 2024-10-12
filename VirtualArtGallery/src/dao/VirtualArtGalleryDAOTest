package dao;

import entity.Artwork;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VirtualArtGalleryDAOTest {

    private VirtualArtGalleryDAO artGalleryDAO;

    @BeforeEach
    void setUp() {
        artGalleryDAO = new VirtualArtGalleryDAO();
    }

    @Test
    void testAddArtwork() throws Exception {
        // Create a new artwork object
        Artwork artwork = new Artwork(0, "Starry Night", "A famous painting", LocalDate.now(), "Oil", "url/to/image", 1);
        
        // Attempt to add the artwork
        artGalleryDAO.addArtwork(artwork);
        
        // Retrieve all artworks to check if the newly added artwork exists
        List<Artwork> allArtworks = artGalleryDAO.getAllArtworks(); // Assuming this method exists
        boolean artworkExists = allArtworks.stream()
                                            .anyMatch(a -> a.getTitle().equals("Starry Night"));
        
        // Assert that the new artwork was added successfully
        assertTrue(artworkExists, "Artwork should have been added successfully.");
    }

    @Test
    void testUpdateArtwork() throws Exception {
        Artwork artwork = new Artwork(1, "Starry Night", "A famous painting", LocalDate.now(), "Oil", "url/to/image", 1);
        artGalleryDAO.addArtwork(artwork);

        artwork.setTitle("The Starry Night");
        artGalleryDAO.updateArtwork(artwork);

        Artwork updatedArtwork = artGalleryDAO.getArtwork(artwork.getArtworkID());
        assertEquals("The Starry Night", updatedArtwork.getTitle());
    }

    @Test
    void testDeleteArtwork() throws Exception {
        // First, add a new artwork
        Artwork artwork = new Artwork(0, "Temporary Artwork", "A temporary painting", LocalDate.now(), "Oil", "url/to/image", 1);
        artGalleryDAO.addArtwork(artwork);
        
        // Retrieve all artworks to find the ID of the added artwork
        List<Artwork> allArtworks = artGalleryDAO.getAllArtworks();
        int artworkIdToDelete = allArtworks.stream()
                                            .filter(a -> a.getTitle().equals("Temporary Artwork"))
                                            .findFirst()
                                            .orElseThrow(() -> new Exception("Artwork not found"))
                                            .getArtworkID();
        
        // Delete the artwork
        artGalleryDAO.deleteArtwork(artworkIdToDelete);
        
        // Check if the artwork was deleted
        Artwork retrievedArtwork = artGalleryDAO.getArtwork(artworkIdToDelete);
        assertNull(retrievedArtwork, "Artwork should have been deleted successfully.");
    }
    @Test
    void testGetAllArtworks() throws Exception {
        List<Artwork> artworks = artGalleryDAO.getAllArtworks();
        assertFalse(artworks.isEmpty());
    }
}

