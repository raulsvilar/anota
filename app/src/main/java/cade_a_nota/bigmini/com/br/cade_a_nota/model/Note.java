package cade_a_nota.bigmini.com.br.cade_a_nota.model;

/**
 * Created by Kanda on 11/03/2017.
 */

public class Note {

    public Note() {
    }

    private String noteTitle;
    private String noteDescription;
    private String url;
    private String id;
    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Note{" +
                "noteTitle='" + noteTitle + '\'' +
                ", noteDescription='" + noteDescription + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
