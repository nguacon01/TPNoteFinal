public class ProjectTest {
    public static void main(String [] arg) throws InvalidCodonException {
        // TODO Auto-generated method stub
        String sampleDNA = "ctatgggtgcccgtagttacccctatgggg"
                + "ttataagtgtacgaagggaactggagctcata"
                + "gcaacagagccagcccgacaagttgcttatga"
                + "ttcgcctacccccataggtgtagcgaaa" ;

        String sampleProt = "MDSTGEFCWICHQPEGPLKRFCGCKGS"
                + "CAVSHQDCLRGWLETSRRQTCALCGTPYSMKWK"
                + "TKPLREWTWGEEEVLAAMEACLPLVLIPLAVL"
                + "MIVMGTWLLVNHNGFLSPRMQVVLVVIVLLAMI"
                + "VFSASASYVMVEGPGCLDTCTAKNSTVTVNSID"
                +"EAIATQQPTKTDLGLARETLSTRFRRGKCRSCCRLGCVRLCCV" ;

        System.out.println("TESTS DNA") ;
        DNA dna = new DNA(sampleDNA) ;
//        System.out.println (dna) ;

        System.out.println("Masse moleculaire de dna: " + dna.massMoleculaire()) ;
        Protein p = dna.dna2protein() ;
//        System.out.println(p) ;

        System.out.println("\n TESTS PROTEINE") ;
        Protein protein = new Protein(sampleProt) ;
//        System.out.println(protein ) ;
        System.out.println("Masse moleculaire de protein: "+protein. massMoleculaire ()) ;

        System.out.println("Test sous séquence: " + protein. findSubSequence ( new Protein( "RFRRGKCRS" ))) ;

        System.out.println ( " \n PAIRWISE ALIGNMENT" ) ;

//		 DNA s1 = new DNA ( "ccctaggtccca" );
//		 DNA s2 = new DNA ( "cgggtatccaa" );

        Sequence s1 = new DNA( "YIHTRYPTQPCRFGKLLLLLPALRSISPSTIEEVFFK" );
        Sequence s2 = new DNA( "hskahhpsqpvrfgklllllpslrfitaeriellffr" );

        System.out.println ( " \n SMITH WATERMAN" ) ;
        PairwiseAlignment sw = new SmithWaterman(s1,s2,"PROTEIN");
//		 sw.aligneSequences();
        System.out.println (sw); // Visualize alignement

        System.out.println ( " \n NeedlemanWunsch" ) ;
        PairwiseAlignment nw = new NeedlemanWunsch(s1,s2,"PROTEIN");
//		 nw. aligneSequences () ;
        System.out.println ( nw ) ;
    }
}
