/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search.system.peer.partition;

/**
 *
 * @author mattija
 */
public class PartitionRange {
    private int partitionNum;
    private int partitionMask;
    
    public PartitionRange(int partitionNum, int partitionMask) {
        this.partitionNum = partitionNum;
        this.partitionMask = partitionMask;
    }

    public PartitionRange(String partitionString) {
        String input[] = partitionString.split("/");
        this.partitionNum = Integer.parseInt(input[0]);
        this.partitionMask = partitionMaskFromWidth(Integer.parseInt(input[1]));
    }
    
    public int getPartitionNum() {
        return partitionNum;
    }

    public int getPartitionMask() {
        return partitionMask;
    }
    
    public boolean covers(PartitionRange range) {
        return this.partitionMask <= range.getPartitionMask()
                && (this.partitionNum & this.partitionMask) 
                == (range.partitionNum & range.partitionMask);
    }
    
    @Override
    public String toString() {
        return "" + partitionNum + "/" + partitionMask;
    }
    
    /**
     * Creates a partition mask with the given width.
     * @param width
     * @return 
     */
    public static int partitionMaskFromWidth(int width) {
        return -1 << (Integer.SIZE - width);
    }
    
    /**
     * Creates a partition mask for the given partition range size.
     * If the size supplied is not a power of 2, the size will be adjusted
     * downwards to the closest power of 2 (give size 7, and the size will
     * be 4).
     * @param size
     * @return 
     */
    public static int partitionMaskFromSize(int size) {
        int width = Integer.SIZE;
        for(;size > 1; size >>= 1) {
            width--;
        }
        return PartitionRange.partitionMaskFromWidth(width);
    }
}
