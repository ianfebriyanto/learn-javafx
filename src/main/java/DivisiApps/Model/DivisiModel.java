package DivisiApps.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DivisiModel {
    private StringProperty kodeDivisi;
    private StringProperty divisi;
    private StringProperty kodeKantor;

    public DivisiModel() {
        this.kodeDivisi = new SimpleStringProperty();
        this.divisi = new SimpleStringProperty();
        this.kodeKantor = new SimpleStringProperty();
    }

    public String getKodeDivisi() {
        return kodeDivisi.get();
    }

    public void setKodeDivisi(String kodeDivisi) {
        this.kodeDivisi.set(kodeDivisi);
    }

    public StringProperty kodeDivisiProperty() {
        return kodeDivisi;
    }

    public String getDivisi() {
        return divisi.get();
    }

    public void setDivisi(String divisi) {
        this.divisi.set(divisi);
    }

    public StringProperty divisiProperty() {
        return divisi;
    }

    public String getKodeKantor() {
        return kodeKantor.get();
    }

    public void setKodeKantor(String kodeKantor) {
        this.kodeKantor.set(kodeKantor);
    }

    public StringProperty kodeKantorProperty() {
        return kodeKantor;
    }
}
