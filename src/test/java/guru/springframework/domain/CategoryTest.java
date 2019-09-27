package guru.springframework.domain;

// Created in Lecture 179 - Added code to getId()

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    // Runs before each test. Creates a new category
    @Before
    public void setUp() {
        category = new Category();
    }

    @Test
    public void getId() {
        Long idValue = 4L;
        category.setId(idValue);
        assertEquals(idValue, category.getId());
    }

    @Test
    public void getDescription() {
        System.out.println("This is from CategoryTest.java");
    }

    @Test
    public void getRecipes() {
    }
}