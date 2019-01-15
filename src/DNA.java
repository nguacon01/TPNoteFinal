/*
 * Copyright (c) 2019. DO Manh Dung - M1 BSIB
 * Class DNA
 */

public class DNA extends Sequence {
    String sequence = getSeqSymbol();
    public DNA(String seqSymbol) {
        super(seqSymbol);
        setType("DNA");
    }
    /*
    calculate mass of molecules
    use hashMap NUCLEOTIDES_MOLECULAR_MASSES
     */
    public double massMoleculaire() {
        double masse = 18.02;
        String seq = getSeqSymbol();
        int seqLength = seq.length();
        if(seqLength > 0) {
            for(int i=0;i<seqLength;i++) {
                char symbol = seq.charAt(i);
                masse = masse + getMassMoleculaire(Util.NUCLEOTIDES_MOLECULAR_MASSES, symbol);
            }
        }
        return masse;
    }

    public Protein dna2protein() throws InvalidCodonException {
        String seqAA="";
        int pos = 0;
        String codon = "";
        /*
        Step 1: find the codon start ATG
         */
        while(pos < sequence.length()-3) {
            /*
            check that the sequence must have more than 3 nucleotides
             */
			if(sequence.length() < 3) {
			    throw new InvalidCodonException("The sequence must have more than 3 nucleotides");
			}
			/*
			a codon has 3 nucleotides
			 */
            codon = sequence.substring(pos,pos+3).toUpperCase();
			/*
			 ATG: codon start.
			 if it is not codon start, we move forward one nucleotide. If it is nucleotide, we get out the loop, and found codon Start.
			  */
            if(codon.contentEquals("ATG")==false){
                pos=pos+1;
            }else {
                break;
            }
        }
        System.out.println("codon start at position: "+pos);

        /*
        step 2: translate codon into acid amine correspondent
        we start at position of codon start which found at step 1, to the nucleotide at position (seqSymbol.length()-3)
         */
        while(pos < sequence.length()-3) {
			if(sequence.length() < 3) {
				break;
			}
            pos+=3;
            codon = sequence.substring(pos,pos+3).toUpperCase();
            /*
            indicate codon stop. At codon stop, we stop translating.
             */
            if(codon.contentEquals("UAA") || codon.contentEquals("UAG") || codon.contentEquals("UGA")) {
                System.out.println("codon stop at position: "+pos);
                break;
            }else {
                try {
                    /*
                    build the sequence acid amine based on function codon2amino with a parameter "codon"
                     */
                    seqAA = seqAA + Util.codon2amino(codon);
                } catch (InvalidCodonException e) {
                    e.printStackTrace();
                }
            }
        }
        Protein protein = new Protein(seqAA);
        return protein;
    }
}
