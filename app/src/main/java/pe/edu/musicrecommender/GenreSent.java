package pe.edu.musicrecommender;

public class GenreSent {

    private int year;
    private float energy;
    private float liveness;
    private float tempo;
    private float speechiness;
    private float acousticness;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    public float getLiveness() {
        return liveness;
    }

    public void setLiveness(float liveness) {
        this.liveness = liveness;
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

    @Override
    public String toString() {
        return "GenreSent{" +
                "year=" + year +
                ", energy=" + energy +
                ", liveness=" + liveness +
                ", tempo=" + tempo +
                ", speechiness=" + speechiness +
                ", acousticness=" + acousticness +
                '}';
    }
}
