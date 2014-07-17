package gaillard.solr.analysis;

import java.io.IOException;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

public final class DedupFilter extends TokenFilter {

    private final CharTermAttribute termAttribute = addAttribute(CharTermAttribute.class);
    private final CharArraySet seenTerms = new CharArraySet(Version.LUCENE_31, 8, false);

    protected DedupFilter(TokenStream input) {
        super(input);
    }

    @Override
    public boolean incrementToken() throws IOException {
        while (input.incrementToken()) {
            final char[] term = termAttribute.buffer();
            final int length = termAttribute.length();

            // clone the term, and add to the set of seen terms.
            char[] termClone = new char[length];
            System.arraycopy(term, 0, termClone, 0, length);
            if (seenTerms.add(termClone)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void reset() throws IOException {
        super.reset();
        seenTerms.clear();
    }
}
