/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search.system.peer.search;

import java.io.Serializable;

/**
 *
 * @author jdowling
 */
public class IndexEntry implements Serializable {
    
    private final int indexId;
    private final String text;

    public IndexEntry(int indexId, String text) {
        this.indexId = indexId;
        this.text = text;
    }

    public int getIndexId() {
        return indexId;
    }

    public String getText() {
        return text;
    }
    
    @Override
    public int hashCode() {
        return text.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IndexEntry other = (IndexEntry) obj;
        if ((this.text == null) ? (other.text != null) : !this.text.equals(other.text)) {
            return false;
        }
        return true;
    }
}
