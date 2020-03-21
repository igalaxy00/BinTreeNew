package BinTreeF;

import java.util.*;


public class BinTree {

    public ArrayList<Node> nodes = new ArrayList<>(); // массив всех узлов которые сейчас есть в дереве
    public Node root= null; //создание начального пустого корня

    /**
     * метод ниже осуществляет поиск элемента в дереве
     *@param key ключ узла который мы намереваемся найти
     *@return узел который мы нашли или ошибку в случае если узла в дереве нет
     */
    public Node find(int key) {
        Node current;
            for (current=root; current != null; current=key < current.key ? current.leftChild : current.rightChild)
                if (current.key == key)
                    return current;
        throw new NullPointerException("Такого узла нет");
    }
    public boolean isEmpty(){ //проверка на пустоту древа
        return root==null;
}


    /**
     * метод ниже осуществляет добавление элемента в дереве
     *@param key ключ узла который мы намереваемся создать
     *@return успешноность выполнения доперации
     */
public boolean newElement(int key) {    //добавление элемента
        Node node = new Node();
        node.key = key;
        if (isEmpty()) {// если корня нет и дерево ещё не создано
            root = node;
            nodes.add(root);
        } else { // если корень есть то добавляем элемент
            Node current = root;
            Node previous;
            while (true) {
                previous = current;
                if (key < previous.key) {//если искомый ключ меньше текущего то идём влево
                    current = current.leftChild;
                    if (current == null) {
                        node.parent = previous;
                        previous.leftChild = node;
                        nodes.add(node);
                        return true;
                    }
                } else {//если искомый ключ больше текущего то идём вправо
                    current = current.rightChild;
                    if (current == null) {
                        node.parent = previous;
                        previous.rightChild = node;
                        nodes.add(node);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int leftChild(int key) {
        return find(key).leftChild.key;
    }

    public int rightChild(int key) {
        return find(key).rightChild.key;
    }

    public int parent(int key) {
        return find(key).parent.key;
    }
    /**
     * метод ниже осуществляет добавление элемента в дереве
     *@param key ключ узла который мы намереваемся создать
     */
    public void removeElement (int key){
        Node current2 = null;
        Node current1 = root;
        nodes.remove(find(key));
        while(current1 != null){
            int compared = Integer.compare(key, current1.key);
            if(compared == 0)
                break;  //выход из цикла если найден элемент
             else {
                current2 = current1;
                if(compared < 0)
                    current1 = current1.leftChild;
                 else
                    current1 = current1.rightChild;
            }
        }
        if(current1 == null)
             return;   // если не нашли узел или дерево пусто
        if(current1.rightChild == null){   // если у узла нет правого потомка
            if(current2 == null)     // или узел это корень и не имеет правого потомка
                root = current1.leftChild;
             else {  //если узел не корень и у него нет правого потомка
                if(current1 != current2.leftChild)
                    current2.rightChild = current1.leftChild;
                else
                   current2.leftChild = current1.leftChild;
            }
        } else {//найден правый птоомок у узла и ищем самый левый узел из правых
            Node endLeft = current1.rightChild;
            current2 = null;
            while(endLeft.leftChild != null) {
                current2 = endLeft;
                endLeft = endLeft.leftChild;
            }
            if(current2 != null)
                current2.leftChild = endLeft.rightChild;
             else
                current1.rightChild = endLeft.rightChild;
            current1.key = endLeft.key;
        }
    }
    //колличество узлов в древе
    public int amountOfNodes(){
        return nodes.size();
    }

    public void clearTree(){
    while (root!= null)
        removeElement(root.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root);
    }
    @Override
    public String toString() {
        return nodes.toString();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof BinTree) {
            BinTree other = (BinTree) obj;
            return nodes == other.nodes;
        }
        return false;
    }
}

