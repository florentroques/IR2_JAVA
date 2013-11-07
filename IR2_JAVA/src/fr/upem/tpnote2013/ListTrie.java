package fr.upem.tpnote2013;

import java.util.ArrayList;
import java.util.function.Consumer;

public class ListTrie extends Trie {
    private static class Node {
        private final char letter;
        private final ArrayList<Node> children;
        private final boolean isLastLetter;

        Node(char letter, boolean isLastLetter) {
            this.letter = letter;
            this.isLastLetter = isLastLetter;
            this.children = new ArrayList<>();
        }

        private Node getChild(char letter) {
            for (Node node : children) {
                if (node.letter == letter) {
                   return node;
                }
            }

            return null;
        }
    }
    
    private final Node root;

    public ListTrie() {
        this.root = new Node('\0', false);
    }

    @Override
    public void add(String string) {
        int length = string.length();
        boolean isLastLetter = false;
        Node parent = root;

        for (int i = 0; i < length; i++) {
            char letter = string.charAt(i);
            Node child = parent.getChild(letter);

            if (i == length - 1) {
                isLastLetter = true;
            }

            if (child == null) {
                child = new Node(letter, isLastLetter);
                parent.children.add(child);
            }

            parent = child;
        }
    }

    @Override
    public boolean contains(String string) {
        int length = string.length();
        Node parent = root;

        for (int i = 0; i < length; i++) {
            char letter = string.charAt(i);            
            Node child = parent.getChild(letter);

            if (i == length - 1) {
                return child.isLastLetter;
            }

            if (child == null) {
                return false;
            }

            parent = child;
        }        
        
        return true;
    }

    @Override
    public void forAll(Consumer<String> consumer) {
        
            }

    @Override
    public Trie prefix(String string) {
        // TODO Auto-generated method stub
        return null;
    }

}
