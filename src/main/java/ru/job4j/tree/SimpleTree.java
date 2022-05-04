package ru.job4j.tree;


import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E newChild) {
        boolean rsl = false;
        Optional<Node<E>> parentNode = findBy(parent);
        Optional<Node<E>> childNode = findBy(newChild);

        if (parentNode.isPresent() && childNode.isEmpty()) {
            parentNode.get().children.add(new Node<>(newChild));
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(el -> el.value.equals(value));
    }

    public boolean isBinary() {
        return findByPredicate((el) -> el.children.size() > 2).isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> res = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                res = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return res;
    }
}