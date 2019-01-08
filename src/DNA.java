public class DNA extends Sequence {
    public DNA(String seqSymbol) {
        super(seqSymbol);
        this.type = "DNA";
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
        while(pos < this.seqSymbol.length()-3) {
			if(this.seqSymbol.length() < 3) {
				break;
			}
            codon = this.seqSymbol.substring(pos,pos+3).toUpperCase();
            if(codon.contentEquals("ATG")==false){
                pos=pos+1;
            }else {
                break;
            }
        }
        System.out.println("codon start at position: "+pos);

        while(pos < this.seqSymbol.length()-3) {
			if(this.seqSymbol.length() < 3) {
				break;
			}
            pos+=3;
            codon = this.seqSymbol.substring(pos,pos+3).toUpperCase();
            if(codon.contentEquals("UAA") || codon.contentEquals("UAG") || codon.contentEquals("UGA")) {
                System.out.println("codon stop at position: "+pos);
                break;
            }else {
                try {
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
