public abstract class PairwiseAlignment {
    /*
     * Sequence 1 and Sequence 2
     */
    Sequence seq1;
    Sequence seq2;
    /*
     * the lengths of sequence 1 and sequence 2
     */
    int length1;
    int length2;
    /*
     * type of sequence: "DNA" or "PROTEIN"
     */
    String type;

    /*
     * GAP value
     */
    public static final int GAP = -2;

    /*
     * Constructor
     */
    public PairwiseAlignment(Sequence seq1, Sequence seq2,String type) {
        this.seq1 = seq1;
        this.seq2 = seq2;
        this.length1 = seq1.getSeqSymbol().length();
        this.length2 = seq2.getSeqSymbol().length();
        this.type = type;
    }

    public Sequence getSeq1() {
        return seq1;
    }

    public void setSeq1(Sequence seq1) {
        this.seq1 = seq1;
    }

    public Sequence getSeq2() {
        return seq2;
    }

    public void setSeq2(Sequence seq2) {
        this.seq2 = seq2;
    }

    abstract void aligneSequences();
    abstract void buildMatrixScore();

    /*
     * se(i,j)
     * find similarity of 2 residues
     */
    public int similarity(int i, int j) {
        int distanceMatrixSubtitution;
        if (i == 0 || j == 0) {
            // it's a gap (indel)
            return 0;
        }
        distanceMatrixSubtitution = Util.getDistanceMatrixSubtitution(seq1.getSeqSymbol().charAt(i-1), seq2.getSeqSymbol().charAt(j-1),this.type);
        return distanceMatrixSubtitution;
    }

    /*
     * print the matrix score
     */
    public void printMatrixScore(int [][] matrixScore) {
        int row = matrixScore.length;
        int column = matrixScore[0].length;

        for(int i=0;i<row;i++) {
            for(int j=0;j<column;j++) {
                System.out.print(matrixScore[i][j]+"	");
            }
            System.out.println();
        }
    }

    public String toString() {
        aligneSequences();
        return "--------";
    }
}
