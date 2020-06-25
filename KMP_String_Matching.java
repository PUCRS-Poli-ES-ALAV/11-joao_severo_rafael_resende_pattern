class KMP_String_Matching {

    /*
    * Complexidade O(k - n)
    * */

    static int iteracoes = 0;

    private static String geraString(int size, int rep) {
        StringBuilder res = new StringBuilder();;
        String charStr = "ABCDEFGHIJKLMOPQRSTWXYZ";
        int posCharStr = 0;

        if (size > 0 && rep > 0 && rep < charStr.length()) {
            for (int i = 0; i < size; i++) {
                res.append(charStr.charAt(posCharStr));
                posCharStr++;
                if (posCharStr == rep)
                    posCharStr = 0;
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String txt = geraString(500_000, 2);
        String pat = geraString(20_000, 2);

        KMPSearch(pat, txt);
    }

    static void KMPSearch(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();

        int lps[] = new int[M];
        int j = 0;

        computeLPSArray(pat, M, lps);

        int i = 0;
        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                System.out.println("Found pattern "
                        + "at index " + (i - j));
                j = lps[j - 1];
            }

            // mismatch após j matches
            else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
            iteracoes += 1;
        }
    }

    static void computeLPSArray(String pat, int M, int lps[]) {
        // tamanho do maior prefixo sufixo anterior
        int len = 0;
        int i = 1;
        lps[0] = 0;

        // loop calcula lps[i] for i = 1 to M-1
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = len;
                    i++;
                }
            }
            iteracoes += 1;
        }
    }
}