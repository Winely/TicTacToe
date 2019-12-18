package com.dongyihe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private int rowNum;
    private int columnNum;
    private List<List<Tile>> tiles = new ArrayList<>();

    public Board(int rowNum, int columnNum) {
        this.rowNum = rowNum;
        this.columnNum = columnNum;
        for (int i = 0; i < this.rowNum; i++) {
            List<Tile> row = new ArrayList<>();
            for (int j = 0; j < this.columnNum; j++) {
                row.add(new Tile(i, j));
            }
            this.tiles.add(row);
        }
    }

    public Tile getTile(int row, int column){
        return tiles.get(row).get(column);
    }

    public List<List<Tile>> getTiles() {
        return tiles;
    }

    public int getRowNum() {
        return rowNum;
    }

    public int getColumnNum() {
        return columnNum;
    }

    public boolean isFull() {
        return tiles.stream().allMatch(
                row -> row.stream().noneMatch(Tile::isEmpty)
        );
    }

    public void clear(){
        for (List<Tile> row : this.tiles){
            for (Tile tile : row){
                tile.setState(Tile.EMPTY_STATE);
            }
        }
    }

    @Override
    public String toString() {
        String rowDivider = Utils.repeatString("+--", columnNum) + "+\n";
        return rowDivider + tiles.stream()
                .map(row -> "|" + row.stream()
                        .map(Tile::getState)
                        .collect(Collectors.joining(" |")) + " |\n")
                .collect(Collectors.joining(rowDivider)) +
                rowDivider;
    }
}
