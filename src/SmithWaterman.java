/*
 * Copyright (c) 2019. DO Manh Dung - M1 BSIB
 * Class SmithWaterman
 */

public class SmithWaterman extends PairwiseAlignment {
    /*
     * array maxCoordSW
     * Store position i and j of the max score
     */
    int length1 = getLength1();
    int length2 = getLength2();
    Sequence seq1 = getSeq1();
    Sequence seq2 = getSeq2();
    int[] maxCoordSW = new int[2];
    int row = length1+1;
    int column = length2+1;
    /*
     * matrixScore
     */
    private int[][] matrixScore = new int[row][column];

    /*
     * Constructor
     * 2 type of sequence: "PROTEIN" or "DNA"
     */
    public SmithWaterman(Sequence seq1, Sequence seq2, String type) {
        super(seq1, seq2, type);
        buildMatrixScore();
    }

    /*
     * build matrix score
     * number of column is the length of sequence 1
     * number of row is the length of sequence 2
     */
    public void buildMatrixScore() {
        int i;
        int j;
        /*
         * first column
         */
        for(i=0;i<row;i++) {
            matrixScore[i][0] = 0;
        }
        /*
         * first row
         */
        for(j=0;j<column;j++) {
            matrixScore[0][j] = 0;
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

        /*
         * find the best score of the matrix
         */
        findMaxScore();
    }

    /*
     * find the highest score in the matrix score
     */
    private int findMaxScore() {
        int maxScore=0;
        for(int i=0;i<row;i++) {
            for(int j=0;j<column;j++) {
                if(matrixScore[i][j]>maxScore) {
                    maxScore = matrixScore[i][j];
                    maxCoordSW[0] = i;
                    maxCoordSW[1] = j;
                }
            }
        }
        System.out.println("The maximum score is: "+maxScore+" at position i= "+maxCoordSW[0]+"; j= "+maxCoordSW[1]);
        return maxScore;
    }

    /*
     * trace back and find the alignment between 2 sequence based on the matrix score
     * trace back from the maximum score to the 0
     */
    public void aligneSequences() {
        int i = maxCoordSW[0];
        int j = maxCoordSW[1];
        String align2 = "";
        String align1 = "";
        /*
        Trace back and add gap
         */
        while(i-1>0 && j-1>0) {
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
            /*
            when we got value of matrix at anywhere, we stop the alignment and rewrite the sequences after alignment
             */
            if(matrixScore[i][j] == 0 || matrixScore[i-1][j-1] == 0 || matrixScore[i][j-1] == 0 || matrixScore[i-1][j] == 0) {
                /*
                trace back
                Rewrite 2 sequence after alignment
                reverse 2 sequence, use StringBuffer(arg).reverse().
                 */
                System.out.println(new StringBuffer(align1).reverse());
                System.out.println(new StringBuffer(align2).reverse() );
                return;
            }
        }
    }
}
