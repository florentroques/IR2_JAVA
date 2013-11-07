package fr.upem.tpnote2013;

import java.util.function.Consumer;

public abstract class Trie {

    public abstract void add(String string);

    public abstract boolean contains(String string);

    public abstract void forAll(Consumer<String> consumer);

    public abstract Trie prefix(String string);

}