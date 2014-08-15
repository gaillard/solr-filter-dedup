package gaillard.solr.analysis;

import java.util.Map;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.TokenFilterFactory;

public class DedupFilterFactory extends TokenFilterFactory {

    public DedupFilterFactory(final Map<String,String> args) {
        super(args);
    }

    public TokenStream create(TokenStream ts) {
        return new DedupFilter(ts);
    }
}
