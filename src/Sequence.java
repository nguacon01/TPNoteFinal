
/*
 * Copyright (c) 2019. DO Manh Dung - M1 BSIB
 * Class Sequence
 */

import java.util.Map;

public abstract class Sequence {
    private String seqSymbol;
    private String type;
    private int longeur;
    /*
     * Constructor
     */
    public Sequence(String seqSymbol) {
        seqSymbol = seqSymbol.toUpperCase();
        this.seqSymbol = seqSymbol;
        this.longeur = seqSymbol.length();
        System.out.println("Sequence has been created: "+this.seqSymbol);
    }
    /*
     * getter and setter
     */
    public String getSeqSymbol() {
        return seqSymbol;
    }
    public void setSeqSymbol(String seqSymbol) {
        this.seqSymbol = seqSymbol;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getLongeur() {
        return this.longeur;
    }
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return this.seqSymbol;
    }
    /*
     * abstract function massMoleculaire
     */
    public abstract double massMoleculaire();
    /*
     * check if there is subsequence
     * @param: un sequence s
     * @return: true(sequence s is a sub-sequence)
     * false(sequence s is not a sub-sequence)
     */
    public boolean findSubSequence(Sequence s) {
        String subSeq = s.seqSymbol;
        return this.seqSymbol.contains(subSeq);
    }
    /*
     * getMassMoleculaire
     * @param:Map<Character,Double> map and Character symbol
     */
    public double getMassMoleculaire(Map<Character,Double> map, Character symbol) {
        return map.get(symbol);
    }
}
