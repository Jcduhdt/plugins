package com.jcduhdt.sharp.markbook.processor;

import com.jcduhdt.sharp.markbook.data.NoteData;

import java.util.List;

public interface SourceNoteData {
    public String getFileName();
    public String getTopic();
    public List<NoteData> getNoteList();
}
