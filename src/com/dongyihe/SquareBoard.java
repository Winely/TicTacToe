package com.dongyihe;

public class SquareBoard extends Board {
    private final int dim;

    public SquareBoard(int dim){
        super(dim, dim);
        this.dim = dim;
    }
}
