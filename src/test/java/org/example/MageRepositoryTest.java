package org.example;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

public class MageRepositoryTest {

    @Test
    public void testFindNotFound(){
        //Arrange
        MageRepository mageRepository = new MageRepository();
        //Act
        Optional<Mage> opt = mageRepository.find("Julian");
        //Assert
        assert opt.isEmpty() : "Should be empty";
    }

    @Test
    public void testFindFound(){
        //Arrange
        Mage mage = mock(Mage.class);
        String name = "Julian";
        int level = 99;
        when(mage.getName()).thenReturn(name);
        when(mage.getLevel()).thenReturn(level);

        MageRepository mageRepository = new MageRepository();
        mageRepository.save(mage);
        //Act
        Optional<Mage> opt = mageRepository.find("Julian");
        //Assert
        assert !opt.isEmpty() : "Should return not empty optional";
    }

    @Test
    public void testDeleteNotContain(){
        //Arrange
        MageRepository mageRepository = new MageRepository();
        //Act
        //Assert
        assertThrows(IllegalArgumentException.class,() -> mageRepository.delete("Julian"));
    }

    @Test
    public void testDeleteDone(){
        //Arrange
        Mage mage = mock(Mage.class);
        String name = "Julian";
        int level = 99;
        when(mage.getName()).thenReturn(name);
        when(mage.getLevel()).thenReturn(level);

        MageRepository mageRepository = new MageRepository();
        mageRepository.getCollection().put(name, mage);
        //Act
        mageRepository.delete("Julian");
        //Assert
        assert mageRepository.getCollection().isEmpty() : "Should be empty";
    }

    @Test
    public void testSaveAlreadyContainSameKey(){
        //Arrange
        Mage mage = mock(Mage.class);
        String name = "Julian";
        int level = 99;
        when(mage.getName()).thenReturn(name);
        when(mage.getLevel()).thenReturn(level);

        Mage mage2 = mock(Mage.class);
        String name2 = "Julian";
        int level2 = 85;
        when(mage2.getName()).thenReturn(name2);
        when(mage2.getLevel()).thenReturn(level2);

        MageRepository mageRepository = new MageRepository();
        mageRepository.getCollection().put(name, mage);
        //Act
        //Assert
        assertThrows(IllegalArgumentException.class,() -> mageRepository.save(mage2));
    }

    @Test
    public void testSaveDone(){
        //Arrange
        Mage mage = mock(Mage.class);
        String name = "Julian";
        int level = 99;
        when(mage.getName()).thenReturn(name);
        when(mage.getLevel()).thenReturn(level);
        MageRepository mageRepository = new MageRepository();
        //Act
        mageRepository.save(mage);
        //Assert
        assert !mageRepository.getCollection().isEmpty() : "Should be not empty";
    }
}
