/*
 * Copyright (c) 2019. DO Manh Dung - M1 BSIB
 * Class PairwiseAlignment
 */

public abstract class PairwiseAlignment {
    /*
     * Sequence 1 and Sequence 2
     */
    private Sequence seq1;
    private Sequence seq2;
    /*
     * the lengths of sequence 1 and sequence 2
     */
    private int length1;
    private int length2;

    /*
     * type of sequence: "DNA" or "PROTEIN"
     */
    private String type;
    /*
     * GAP value
     */
    public static final int GAP = -2;
    public static final char gapString = '-';
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

    public int getLength1() {
        return length1;
    }

    public void setLength1(int length1) {
        this.length1 = length1;
    }

    public int getLength2() {
        return length2;
    }

    public void setLength2(int length2) {
        this.length2 = length2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    abstract void aligneSequences();
    abstract void buildMatrixScore();

    /*
    fillMatrixScore
    @param: int[][] matrixScore, int row, int column
    @return: matrixScore
    This function help us to fill all the value in the matrix score
     */
    public int[][] fillMatrixScore(int [][] matrixScore, int row, int column){
        int i;
        int j;
        /*
         * the rest of matrix
         */
        for(i=1;i<row;i++) {
            for(j=1;j<column;j++) {
                int diagScore = matrixScore[i - 1][j - 1] + similarity(i, j);
                int upScore = matrixScore[i][j - 1] + GAP;
                int leftScore = matrixScore[i - 1][j] + GAP;
                /*
                 * find the maximum point of diagScore, UpScore and LeftScore then fill it into matrix score at position [i][j]
                 */
                matrixScore[i][j] = Math.max(diagScore, Math.max(upScore,Math.max(leftScore, 0)));
            }
        }
        return matrixScore;
    }

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
