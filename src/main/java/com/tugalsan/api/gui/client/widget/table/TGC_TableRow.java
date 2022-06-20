package com.tugalsan.api.gui.client.widget.table;

public class TGC_TableRow {

    public TGC_TableRow(int rowIdx, int colSize) {
        this.rowIdx = rowIdx; 
        this.cells = new String[colSize];
    }
    public Integer rowIdx;
    public String[] cells;
}
