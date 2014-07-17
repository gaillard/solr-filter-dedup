package gaillard.solr.analysis;

import org.apache.lucene.analysis.TokenStream;
import org.apache.solr.analysis.BaseTokenFilterFactory;

public class DedupFilterFactory extends BaseTokenFilterFactory {

    public TokenStream create(TokenStream ts) {
        return new DedupFilter(ts);
    }
}
