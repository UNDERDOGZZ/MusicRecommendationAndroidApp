package pe.edu.musicrecommender;

public class SongsGenreResponse {

    private String title;
    private String artist;
    private String date;
    private float energy;
    private float liveness;
    private float tempo;
    private float speechiness;
    private float acousticness;
    private int genre;

    public String getTitle() {
        return title;
    }

    public float getLiveness() {
        return liveness;
    }

    public void setLiveness(float liveness) {
        this.liveness = liveness;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    public float getTempo() {
        return tempo;
    }

    public void setTempo(float tempo) {
        this.tempo = tempo;
    }

    public float getSpeechiness() {
        return speechiness;
    }

    public void setSpeechiness(float speechiness) {
        this.speechiness = speechiness;
    }

    public float getAcousticness() {
        return acousticness;
    }

    public void setAcousticness(float acousticness) {
        this.acousticness = acousticness;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "SongsGenreResponse{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", date='" + date + '\'' +
                ", energy=" + energy +
                ", liveness=" + liveness +
                ", tempo=" + tempo +
                ", speechiness=" + speechiness +
                ", acousticness=" + acousticness +
                ", genre=" + genre +
                '}';
    }
}
