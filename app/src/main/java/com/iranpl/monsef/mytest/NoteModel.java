package com.iranpl.monsef.mytest;

import java.util.Date;

public class NoteModel {
    private int noteId;
    private String title;
    private String noteDate;
    private String text;
    public NoteModel(){

    }
    public NoteModel(int id, String title, String ndate, String ntext)
    {
            this.noteId = id;
            this.title = title;
            this.noteDate = ndate;
            this.text = ntext;
    }

    public int getId(){
        return noteId;
    }
    public String getTitle(){
        return title;
    }
    public String getDate(){
        return String.valueOf(noteDate);
    }
    public String getText(){
        return text;
    }
}
