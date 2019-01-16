/*
 * Copyright (c) 2019. DO Manh Dung - M1 BSIB
 * Class NeedlemanWunsch
 * chekc out
 */

public class NeedlemanWunsch extends PairwiseAlignment {
    private int length1 = getLength1();
    private int length2 = getLength2();
    private Sequence seq1 = getSeq1();
    private Sequence seq2 = getSeq2();
    private int row = length1+1;
    private int column = length2+1;
    /*
     * matrixScore
     */
    private int[][] matrixScore = new int[row][column];
    /*
     * 2 type "PROTEIN" or "DNA"
     */
    public NeedlemanWunsch(Sequence seq1, Sequence seq2, String type) {
        super(seq1, seq2, type);
        buildMatrixScore();
    }

    /*
     * build matrix score
     * number of column is the length of sequence1 plus 1
     * number of row is the length of sequence2 plus 1
     */
    public void buildMatrixScore() {
        matrixScore[0][0] = 0;
        int i;
        int j;
        /*
         * first row
         */
        for(i=1;i<row;i++) {
            matrixScore[i][0] = matrixScore[i - 1][0] + GAP;
        }
        /*
         * first column
         */
        for(j=1;j<column;j++) {
            matrixScore[0][j] = matrixScore[0][j - 1] + GAP;
        }
        /*
        we fill the rest of matrix with this function
        fillMatrixScore
        @params: matrixScore, row, column
        return matrixScore has been full filled
         */
        matrixScore = fillMatrixScore(matrixScore, row, column);

        System.out.println();
        /*
         * Print the matrix score
         */
        printMatrixScore(matrixScore);
    }

    /*
     * trace back and find the alignment between 2 sequence based on the matrix score
     * trace back from position [i][j] to position [0][0]
     */
    public void aligneSequences() {
        int i = row-1;
        int j = column-1;
        String align1 = "";
        String align2 = "";

        while(i>0 && j>0) {
            if(matrixScore[i][j]-similarity(i, j) == matrixScore[i-1][j-1]) {
                align1 = align1 + seq1.getSeqSymbol().charAt(i-1);
                align2 = align2 + seq2.getSeqSymbol().charAt(j-1);
                i--;j--;
            }
            else if(matrixScore[i][j] - GAP == matrixScore[i][j-1]) {
                align1 = align1 + gapString;
                align2 = align2 + seq2.getSeqSymbol().charAt(j-1);
                j--;
            }
            else if(matrixScore[i][j] - GAP == matrixScore[i-1][j]) {
                align1 = align1 + seq1.getSeqSymbol().charAt(i-1);;
                align2 = align2 + gapString;
                i--;
            }else {
                throw new Error("Fail");
            }
        }
        System.out.println(new StringBuffer(align1).reverse());
        System.out.println(new StringBuffer(align2).reverse());
    }
}
