package dao;
import java.util.List;

import entity.Gallery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VirtualArtGalleryGalleryDAOTest {

    private VirtualArtGalleryDAO artGalleryDAO;

    @BeforeEach
    void setUp() {
        artGalleryDAO = new VirtualArtGalleryDAO();
    }

    void testAddGallery() throws Exception {
        // Create a new gallery object
        Gallery gallery = new Gallery(0, "Modern Art Gallery", "A gallery for modern art", "NYC", 1, "9 AM - 5 PM");
        
        // Attempt to add the gallery
        artGalleryDAO.addGallery(gallery);
        
        // Set the gallery ID to the new gallery's ID (this assumes ID is generated in some way)
        // Since we are not modifying the addGallery method, you may need to retrieve it after adding
        List<Gallery> allGalleries = artGalleryDAO.getAllGalleries();
        
        // Assert that the new gallery is in the list of galleries
        boolean galleryExists = allGalleries.stream()
                                            .anyMatch(g -> g.getName().equals("Modern Art Gallery"));
        assertTrue(galleryExists, "Gallery should have been added successfully.");
    }


    @Test
    void testUpdateGallery() throws Exception {
        Gallery gallery = new Gallery(1, "Modern Art Gallery", "A gallery for modern art", "NYC", 1, "9 AM - 5 PM");
        artGalleryDAO.addGallery(gallery);

        gallery.setName("Contemporary Art Gallery");
        artGalleryDAO.updateGallery(gallery);

        Gallery updatedGallery = artGalleryDAO.getGallery(gallery.getGalleryID());
        assertEquals("Contemporary Art Gallery", updatedGallery.getName());
    }

    void testDeleteGallery() throws Exception {
        // First, add a new gallery
        Gallery gallery = new Gallery(0, "Temporary Gallery", "A temporary gallery", "NYC", 1, "9 AM - 5 PM");
        artGalleryDAO.addGallery(gallery);

        // Assume that gallery ID is generated here; you'd need to retrieve it again
        List<Gallery> allGalleries = artGalleryDAO.getAllGalleries();
        int galleryIdToDelete = allGalleries.stream()
                                             .filter(g -> g.getName().equals("Temporary Gallery"))
                                             .findFirst()
                                             .orElseThrow(() -> new Exception("Gallery not found"))
                                             .getGalleryID();
        
        // Delete the gallery
        artGalleryDAO.deleteGallery(galleryIdToDelete);

        // Check if the gallery was deleted
        Gallery retrievedGallery = artGalleryDAO.getGallery(galleryIdToDelete);
        assertNull(retrievedGallery, "Gallery should have been deleted successfully.");
    }

    @Test
    void testGetAllGalleries() throws Exception {
        List<Gallery> galleries = artGalleryDAO.getAllGalleries();
        assertFalse(galleries.isEmpty());
    }
}

