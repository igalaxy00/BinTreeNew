import BinTreeF.BinTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

 class TestBinTree {

    @Test
    void newNode() {
        BinTree theTree = new BinTree();
        assertTrue(theTree.isEmpty());//проверка на пустоту древа
        theTree.newElement(55);
        theTree.newElement(100);
        theTree.newElement(104);
        theTree.newElement(103);
        theTree.newElement(105);
        theTree.newElement(4);
        assertEquals(theTree.nodes.get(1).key,100);//проверка того как заполняется массив
        assertTrue(theTree.newElement(1));//проверка успешно ли добавляютя элементы
        assertEquals(theTree.root.key, 55);//проверки связанные с корнем
        assertEquals(theTree.root.leftChild.key, 4);
        assertEquals(theTree.root.rightChild.key, 100);
        assertEquals(theTree.find(theTree.root.key).key,55);
        assertEquals(theTree.find(4).key,4);
        theTree.removeElement(4);//проверка на удаление элемента
        assertEquals(theTree.find(1).key,1);//проверка того что происходит с узлами предка которых удалили
        assertEquals(theTree.root.leftChild.key, 1);
        assertEquals(theTree.find(105).parent.key,104);//проверка предка у случайного узла
        assertEquals(theTree.find(104).leftChild.key,103);//проверка левого потомка  у случайного узла
        assertEquals(theTree.find(104).rightChild.key,105);//проверка правого потомка  у случайного узла
        assertThrows(NullPointerException.class, () -> theTree.find(999999999)); //проверка что будет если попробовать
                                                                                // найти несуществующий узел
    }
}
