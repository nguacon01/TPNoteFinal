import java.util.HashMap;
import java.util.Map;

public class Util {
    /*
    Defined by DO Manh Dung
     */
    public static final String TYPE_DNA = "DNA";
    public static final String TYPE_PROTEIN = "PROTEIN";
    /**
     * codon2amino
     *
     * @param codon (3-letter sequence)
     * @return aminoacid (amino acid corresponding to the codon
     * @throws InvalidCodonException
     */
    public static Character codon2amino(String codon) throws InvalidCodonException{
        codon = codon.toUpperCase();
        Character aminoacid = null;
        aminoacid = CODONS_TO_AMINO.get(codon);
        if (aminoacid == null) throw new InvalidCodonException(codon);
        return aminoacid;
    }

    /**
     * amino2fullname
     *
     * @param amino (one-letter amino acid)
     * @return aminoname (full name of the amino acid)
     * @throws InvalidAminoException
     */
    public static String amino2fullName(Character amino) throws InvalidAminoException {
        amino = Character.toUpperCase(amino);
        String aminoname = AMINOACIDS_FULLNAMES.get(amino);
        if (aminoname == null) throw new InvalidAminoException(amino.toString());
        return aminoname;
    }

    /*
     * public static String[] amino2codons(String amino) throws InvalidAminoException{
     *   // TODO
     * }
     */


    @SuppressWarnings("serial")
    public static final Map<String, Character> CODONS_TO_AMINO = new HashMap<String, Character>()
    {{ put("TTT",'F'); put("TTC",'F'); put("TTA",'L'); put("TTG",'L'); put("TCT",'S'); put("TCC",'S'); put("TCA",'S'); put("TCG",'S');
        put("TAT",'Y'); put("TAC",'Y'); put("TGT",'C'); put("TGC",'C'); put("TGG",'W'); put("CTT",'L'); put("CTC",'L'); put("CTA",'L');
        put("CTG",'L'); put("CCT",'P'); put("CCC",'P'); put("CCA",'P'); put("CCG",'P'); put("CAT",'H'); put("CAC",'H'); put("CAA",'Q');
        put("CAG",'Q'); put("CGT",'R'); put("CGC",'R'); put("CGA",'R'); put("CGG",'R'); put("ATT",'I'); put("ATC",'I'); put("ATA",'I');
        put("ATG",'M'); put("ACT",'T'); put("ACC",'T'); put("ACA",'T'); put("ACG",'T'); put("AAT",'N'); put("AAC",'N'); put("AAA",'K');
        put("AAG",'K'); put("AGT",'S'); put("AGC",'S'); put("AGA",'R'); put("AGG",'R'); put("GTT",'V'); put("GTC",'V'); put("GTA",'V');
        put("GTG",'V'); put("GCT",'A'); put("GCC",'A'); put("GCA",'A'); put("GCG",'A'); put("GAT",'D'); put("GAC",'D'); put("GAA",'E');
        put("GAG",'E'); put("GGT",'G'); put("GGC",'G'); put("GGA",'G'); put("GGG",'G');
    }};

    @SuppressWarnings("serial")
    public static final Map<Character, String> AMINOACIDS_FULLNAMES = new HashMap<Character, String>(){
        {
            put('F',"Phe"); put('L',"Leu"); put('I',"Ile"); put('M',"Met"); put('V',"Val");
            put('S',"Ser"); put('P',"Pro"); put('T',"Thr"); put('A',"Ala"); put('Y',"Tyr");
            put('H',"His"); put('Q',"Gln"); put('N',"Asp"); put('K',"Lys"); put('D',"Asp");
            put('E',"Glu"); put('C',"Cys"); put('W',"Trp"); put('R',"Arg"); put('G',"Gly");
        }
    };

    @SuppressWarnings("serial")
    public static final Map<Character, Double> NUCLEOTIDES_MOLECULAR_MASSES = new HashMap<Character, Double>(){
        {put('A', 323.21); put('C', 289.18); put('G', 329.21); put('T', 304.19);}
    };

    @SuppressWarnings("serial")
    public static final Map<Character, Double> AMINOACIDS_MOLECULAR_MASSES = new HashMap<Character, Double> (){
        {
            put('F', 147.17); put('L', 113.15); put('I', 113.15); put('M', 131.19); put('V', 99.13);
            put('S', 87.07);  put('P', 97.11);  put('T', 101.10); put('A', 71.07);  put('Y', 163.17);
            put('H', 137.14); put('Q', 128.13); put('N', 114.08); put('K', 128.17); put('D', 115.08);
            put('E', 129.11); put('C', 103.10); put('W', 186.20); put('R', 156.18); put('G', 57.05);
        }
    };

    @SuppressWarnings("serial")
    public static final Map<Character, Double> pKa_AMINO_ACIDS = new HashMap<Character, Double>()
    {{ put('+',8.0);  put('-',3.1);
        put('K',10.0); put('R',12.0); put('H',6.5);
        put('E',4.4);  put('D',4.4);  put('Y',10.0); put('C',8.5);
    }};

    @SuppressWarnings("serial")
    public static final Map<Character, Boolean> IS_ACID_AA = new HashMap<Character, Boolean>(){{
        put('+',false); put('-',true);
        put('K',false); put('R',false); put('H',false);
        put('E',true);  put('D',true);  put('Y',true);  put('C',true);
    }};
    /*
     * ==================================================================================================
     * MY UTIL
     */
    /*
     * Index amino acid in matrix blosum62
     */
    @SuppressWarnings("serial")
    public static final Map<Character, Integer> AMINOACIDS_INDEX = new HashMap<Character, Integer>(){
        {
            put('A',0);put('R',1);put('N',2);put('D',3);put('C',4);
            put('Q',5);put('E',6);put('G',7);put('H',8);put('I',9);
            put('L',10);put('K',11);put('M',12);put('F',13);put('P',14);
            put('S',15);put('T',16);put('W',17);put('Y',18);put('V',19);
        }
    };
    @SuppressWarnings("serial")
    public static final Map<Character, Integer> NUCLEOTIDES_INDEX = new HashMap<Character, Integer>(){
        {
            put('A',0);put('G',1);put('C',2);put('T',3);
        }
    };

    /*
     * Matrix blosum62
     * https://en.wikipedia.org/wiki/BLOSUM
     */
    public static final int[][] MatrixBlosum62 = {
            /*	A   R	N	D	C	Q	E	G	H	I	L	K	M	F	P	S	T	W	Y	V*/
            /*A*/ { 4, -1, -2, -2,  0, -1, -1,  0, -2, -1, -1, -1, -1, -2, -1,  1,  0, -3, -2,  0},
            /*R*/ {-1,  5,  0, -2, -3,  1,  0, -2,  0, -3, -2,  2, -1, -3, -2, -1, -1, -3, -2, -3},
            /*N*/ {-2,  0,  6,  1, -3,  0,  0,  0,  1, -3, -3,  0, -2, -3, -2,  1,  0, -4, -2, -3},
            /*D*/ {-2, -2,  1,  6, -3,  0,  2, -1, -1, -3, -4, -1, -3, -3, -1,  0, -1, -4, -3, -3},
            /*C*/ { 0, -3, -3, -3,  9, -3, -4, -3, -3, -1, -1, -3, -1, -2, -3, -1, -1, -2, -2, -1},
            /*Q*/ {-1,  1,  0,  0, -3,  5,  2, -2,  0, -3, -2,  1,  0, -3, -1,  0, -1, -2, -1, -2},
            /*E*/ {-1,  0,  0,  2, -4,  2,  5, -2,  0, -3, -3,  1, -2, -3, -1,  0, -1, -3, -2, -2},
            /*G*/ { 0, -2,  0, -1, -3, -2, -2,  6, -2, -4, -4, -2, -3, -3, -2,  0, -2, -2, -3, -3},
            /*H*/ {-2,  0,  1, -1, -3,  0,  0, -2,  8, -3, -3, -1, -2, -1, -2, -1, -2, -2,  2, -3},
            /*I*/ {-1, -3, -3, -3, -1, -3, -3, -4, -3,  4,  2, -3,  1,  0, -3, -2, -1, -3, -1,  3},
            /*L*/ {-1, -2, -3, -4, -1, -2, -3, -4, -3,  2,  4, -2,  2,  0, -3, -2, -1, -2, -1,  1},
            /*K*/ {-1,  2,  0, -1, -3,  1,  1, -2, -1, -3, -2,  5, -1, -3, -1,  0, -1, -3, -2, -2},
            /*M*/ {-1, -1, -2, -3, -1,  0, -2, -3, -2,  1,  2, -1,  5,  0, -2, -1, -1, -1, -1,  1},
            /*F*/ {-2, -3, -3, -3, -2, -3, -3, -3, -1,  0,  0, -3,  0,  6, -4, -2, -2,  1,  3, -1},
            /*P*/ {-1, -2, -2, -1, -3, -1, -1, -2, -2, -3, -3, -1, -2, -4,  7, -1, -1, -4, -3, -2},
            /*S*/ { 1, -1,  1,  0, -1,  0,  0,  0, -1, -2, -2,  0, -1, -2, -1,  4,  1, -3, -2, -2},
            /*T*/ { 0, -1,  0, -1, -1, -1, -1, -2, -2, -1, -1, -1, -1, -2, -1,  1,  5, -2, -2,  0},
            /*W*/ {-3, -3, -4, -4, -2, -2, -3, -2, -2, -3, -2, -3, -1,  1, -4, -3, -2, 11,  2, -3},
            /*Y*/ {-2, -2, -2, -3, -2, -1, -2, -3,  2, -1, -1, -2, -1,  3, -3, -2, -2,  2,  7, -1},
            /*V*/ { 0, -3, -3, -3, -1, -2, -2, -3, -3,  3,  1, -2,  1, -1, -2, -2,  0, -3, -1,  4}
    };
    /*
     * Matrix blosum62
     * https://en.wikipedia.org/wiki/BLOSUM
     */
    public static final int[][] SubtitutionMatrix = {
            /*	A   G	C	T	*/
            /*A*/ { 3, -3, -3, -3, },
            /*G*/ {-3,  3, -3, -3, },
            /*C*/ {-3, -3,  3, -3, },
            /*T*/ {-3, -3, -3,  3, },
    };
    public static int getIndex(char a,String type) {
        int index;
        if(type != TYPE_DNA && type != TYPE_PROTEIN) {
            throw new Error("Type de sequence is invalide!");
        }
        if(type == TYPE_PROTEIN) {
            index = Util.AMINOACIDS_INDEX.get(a);
            return index;
        }
        index = Util.NUCLEOTIDES_INDEX.get(a);
        return index;
    }
    public static int getDistanceMatrixSubtitution(char a1, char a2, String type){
        if(type == TYPE_PROTEIN) {
            if(0 > getIndex(a1,type) || getIndex(a1,type)> MatrixBlosum62[0].length) {
                throw new Error("Residue "+a1+" is not invalide");
            }
            if(0 > getIndex(a2,type) || getIndex(a2,type)> MatrixBlosum62[0].length) {
                throw new Error("Residue "+a2+" is not invalide");
            }
            return MatrixBlosum62[getIndex(a1,type)][getIndex(a2,type)];
        }
        return SubtitutionMatrix[getIndex(a1,type)][getIndex(a2,type)];

    }
}
