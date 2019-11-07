package br.com.pedroenju.model;

/**
 *
 * @author Pedro Enju
 */
public class Estado {

    private long idEstado;
    private String nomeEstado;
    private String uf;

    public long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(long idEstado) {
        this.idEstado = idEstado;
    }

    public String getNomeEstado() {
        return nomeEstado;
    }

    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return String.valueOf(this.idEstado) + " - " + this.nomeEstado + " - " + this.uf;
    }
    
}
