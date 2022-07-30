package com.jcduhdt.sharp.markbook.data;

import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;
import java.util.List;

public class DataCenter {
    public static String SELECT_TEXT;
    public static String FINAL_NAME;
    public static List<NoteData> NOTE_LIST = new LinkedList<>();

    public static String[] COLUMN = {"title", "remark", "doc name", "code"};

    public static DefaultTableModel TABLE_MODEL = new DefaultTableModel(null, COLUMN);

    public static void reset(){
        NOTE_LIST.clear();
        TABLE_MODEL.setDataVector(null,COLUMN);
    }
}
