package com.unifecaf.memorygame2;

import javax.swing.ImageIcon;

/**
 * @author Gleydson
 */
public class Carta {
    private ImageIcon imagemFace;
    private boolean virada = false;
    private boolean descoberta = false;

    public Carta(ImageIcon imagem) { this.imagemFace = imagem; }
    public ImageIcon getImagemFace() { return imagemFace; }
    public boolean isVirada() { return virada; }
    public void setVirada(boolean virada) { this.virada = virada; }
    public boolean isDescoberta() { return descoberta; }
    public void setDescoberta(boolean descoberta) { this.descoberta = descoberta; }
}
