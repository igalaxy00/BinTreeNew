package BinTreeF;

import java.util.*;


public class BinTree {

    public ArrayList<Node> nodes = new ArrayList<>();
    public Node root= null;

    public Node find(int key) {
        Node current;
            for (current=root; current != null; current=key < current.key ? current.leftChild : current.rightChild)
                if (key == current.key)
                    return current;
        throw new NullPointerException("Такого узла нет");
        }
    public boolean isEmpty(){
        return root==null;
}

    //добавление элемента
    public boolean newElement(int key) {
        Node node = new Node();
        node.key = key;
        if (root == null) {
            root = node;
            nodes.add(node);
        } else {
            Node current = root;
            Node previous;
            while (true) {
                previous = current;
                if (key < previous.key) {
                    current = current.leftChild;
                    if (current == null) {
                        previous.leftChild = node;
                        node.parent = previous;
                        nodes.add(node);
                        return true;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {
                        previous.rightChild = node;
                        node.parent = previous;
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

    private void replaceChild(Node current){
        if (current.leftChild != null)
            newElement(current.leftChild.key);
        if (current.rightChild != null)
            newElement(current.rightChild.key);
    }

    public void removeElement(int key) {
        Node current = find(key);
        Node cParent = current.parent;
        if (cParent.rightChild == current) {// очистка ссылок
            cParent.rightChild = null;
        } else
            cParent.leftChild = null;
        replaceChild(current);
        nodes.remove(current);
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