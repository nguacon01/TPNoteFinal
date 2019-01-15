/*
 * Copyright (c) 2019. DO Manh Dung - M1 BSIB
 * Class Protein
 */

public class Protein extends Sequence {
    String sequence = getSeqSymbol();
    public Protein(String seqSymbol) {
        super(seqSymbol);
        setType("PROTEIN");
    }
    /*
    calculate mass of molecules
    use hashMap AMINOACIDS_MOLECULAR_MASSES
     */
    public double massMoleculaire() {
        double masse = 18.02;
        int seqLength = getSeqSymbol().length();
        if(seqLength > 0) {
            for(int i=0;i<seqLength;i++) {
                char symbol = getSeqSymbol().charAt(i);
                masse = masse + getMassMoleculaire(Util.AMINOACIDS_MOLECULAR_MASSES, symbol);
            }
        }
        return masse;
    }

    /*
        estimerCharge
        @param: double pH
        @return charge of protein
     */
    private double estimerCharge(double pH) {

        double charge = 0;
        double r;
        for(int i=0; i < sequence.length();i++) {
            /*
            find the charge of amino acid at position i in the sequence
            use HashMap pKa_AMINO_ACIDS.
             */
            if(Util.pKa_AMINO_ACIDS.containsKey(sequence.charAt(i))){
                double pKa = Util.pKa_AMINO_ACIDS.get(sequence.charAt(i));
                r = Math.pow(10,pH-pKa) / (Math.pow(10,pH-pKa) +1.0);
                /*
                check if amino acid is a acid
                use HashMap IS_ACID_AA
                 */
                if(Util.IS_ACID_AA.get(sequence.charAt(i))) {
                    charge+=-1.0*r;
                }else {
                    charge+=1-r;
                }
            }
        }
        return charge;
    }

    public double  estimerPointIsoElectrique() {
        double pH=0;
        double chargeMin = estimerCharge(pH);
        double palier = 7.0;
        while(Math.abs(chargeMin) > 0.001) {
            double pHTest = pH+palier;
            double charge = estimerCharge(pHTest);
            if(Math.abs(charge) < Math.abs(chargeMin)) {
                chargeMin = charge;
                pH = pHTest;
            }else {
                palier = Math.abs(palier)/2.0;
                if(chargeMin < 0.0) {
                    palier = palier * (-1.0);
                }
            }
        }
        System.out.println(estimerCharge(pH));
        return pH;
    }
}
