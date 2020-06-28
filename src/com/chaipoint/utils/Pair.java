package com.chaipoint.utils;

public class Pair<L, R> {
    L left;
    R right;

    private Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public static <L, R> Pair of(L left, R right) {
        return new Pair(left, right);
    }

    public L getLeft() {
        return this.left;
    }

    public R getRight() {
        return this.right;
    }
    public void setRight(R right) {
        this.right = right;
    }
}
