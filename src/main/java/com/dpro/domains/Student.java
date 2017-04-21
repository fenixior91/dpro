package com.dpro.domains;

public class Student extends User {

    private String album;

    /**
     * @return numer albumu
     */
    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return String.format("[%s, album: %s]", super.toString(), getAlbum());
    }
}
