public class NeedlemanWunsch extends PairwiseAlignment {
    int row = length1+1;
    int column = length2+1;
    /*
     * matrixScore
     */
    private int[][] matrixScore = new int[row][column];;
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
                matrixScore[i][j] = Math.max(diagScore, Math.max(upScore,leftScore));
            }
        }
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
        final char gapString = '-';
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
