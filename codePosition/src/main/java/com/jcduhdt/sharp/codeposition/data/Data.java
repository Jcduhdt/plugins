package com.jcduhdt.sharp.codeposition.data;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Data {
    public String filePath;
    public String funcName;
    public String fileName;
    public int row;
    public int col;

    public Data(String filePath, String fileName, String funcName, int row, int col) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.funcName = funcName;
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "filePath='" + filePath + '\'' +
                ", funcName='" + funcName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", row=" + row +
                ", col=" + col;
    }

    public Data(String filePath) {
        this.filePath = filePath;
    }
}
