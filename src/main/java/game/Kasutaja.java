package game;

public class Kasutaja {
    private String nimi;
    private int skoor;

    public Kasutaja(String nimi, int skoor) {
        this.nimi = nimi;
        this.skoor = skoor;
    }

    public String getNimi() {
        return nimi;
    }

    public int getSkoor() {
        return skoor;
    }
}
