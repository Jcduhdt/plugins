package com.jcduhdt.sharp.markbook.data;

public class DataConvert {
    public static String[] convert(NoteData noteData) {
        String[] raw = new String[]{
                noteData.getTitle(),
                noteData.getMark(),
                noteData.getFileName(),
                noteData.getContent()
        };
        return raw;
    }
}
