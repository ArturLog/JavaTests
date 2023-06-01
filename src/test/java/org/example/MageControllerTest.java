package org.example;

import org.junit.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MageControllerTest {
    @Test
    public void testFindNotFound(){
        //Arrange
        MageRepository mageRepository = mock(MageRepository.class);
        MageController mageController = new MageController(mageRepository);
        //Act
        String ret = mageController.find("Julian");
        //Assert
        assert ret.equals("not found") : "Should return: not found";
    }

    @Test
    public void testFindFound(){
        //Arrange
        Mage mage = mock(Mage.class);
        String name = "Julian";
        int level = 99;
        when(mage.getName()).thenReturn(name);
        when(mage.getLevel()).thenReturn(level);

        MageRepository mageRepository = mock(MageRepository.class);
        when(mageRepository.find(mage.getName())).thenReturn(Optional.of(mage));
        MageController mageController = new MageController(mageRepository);
        //Act
        String ret = mageController.find("Julian");
        //Assert
        assert ret.equals(mage.toString()) : "Should return: mage.toString()";
    }

    @Test
    public void testDeleteNotContain(){
        //Arrange
        MageRepository mageRepository = new MageRepository();
        MageController mageController = new MageController(mageRepository);
        //Act
        String ret = mageController.delete("Julian");
        //Assert
        assert ret.equals("not found") : "Should return: not found";

    }

    @Test
    public void testDeleteDone(){
        //Arrange
        Mage mage = mock(Mage.class);
        String name = "Julian";
        int level = 99;
        when(mage.getName()).thenReturn(name);
        when(mage.getLevel()).thenReturn(level);

        MageRepository mageRepository = mock(MageRepository.class);
        mageRepository.getCollection().put(name, mage);
        MageController mageController = new MageController(mageRepository);
        //Act
        String ret = mageController.delete("Julian");
        //Assert
        assert ret.equals("done") : "Should return: done";
    }

    @Test
    public void testSaveAlreadyContainSameKey(){
        //Arrange
        Mage mage = mock(Mage.class);
        String name = "Julian";
        int level = 99;
        when(mage.getName()).thenReturn(name);
        when(mage.getLevel()).thenReturn(level);

        MageRepository mageRepository = new MageRepository();
        mageRepository.getCollection().put(name, mage);
        MageController mageController = new MageController(mageRepository);
        //Act
        String ret = mageController.save("Julian", 43);
        //Assert
        assert ret.equals("bad request") : "Should return: bad request";
    }

    @Test
    public void testSaveDone(){
        //Arrange
        MageRepository mageRepository = mock(MageRepository.class);
        MageController mageController = new MageController(mageRepository);
        //Act
        String ret = mageController.save("Julian", 43);
        //Assert
        assert ret.equals("done") : "Should return: done";
    }
}
