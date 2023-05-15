// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.pathfinding.util;

import java.util.Arrays;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.PathNode;

public final class PathNodeOpenSet
{
    private PathNode[] array;
    public int size;
    
    public PathNodeOpenSet() {
        this.array = new PathNode[1024];
    }
    
    public PathNodeOpenSet(final int size) {
        this.array = new PathNode[size];
    }
    
    public final void insert(final PathNode value) {
        if (this.size >= this.array.length - 1) {
            this.array = Arrays.copyOf(this.array, this.array.length << 1);
        }
        ++this.size;
        value.heapPosition = this.size;
        this.array[this.size] = value;
        int index = this.size;
        int parentInd = index >>> 1;
        for (PathNode parentNode = this.array[parentInd]; index > 1 && parentNode.combinedCost > value.combinedCost; index = parentInd, parentInd = index >>> 1, parentNode = this.array[parentInd]) {
            this.array[index] = parentNode;
            this.array[parentInd] = value;
            value.heapPosition = parentInd;
            parentNode.heapPosition = index;
        }
    }
    
    public final void update(final PathNode val) {
        int index = val.heapPosition;
        int parentInd = index >>> 1;
        for (PathNode parentNode = this.array[parentInd]; index > 1 && parentNode.combinedCost > val.combinedCost; index = parentInd, parentInd = index >>> 1, parentNode = this.array[parentInd]) {
            this.array[index] = parentNode;
            this.array[parentInd] = val;
            val.heapPosition = parentInd;
            parentNode.heapPosition = index;
        }
    }
    
    public final PathNode removeLowest() {
        final PathNode result = this.array[1];
        final PathNode val = this.array[this.size];
        this.array[1] = val;
        val.heapPosition = 1;
        this.array[this.size] = null;
        --this.size;
        result.heapPosition = -1;
        if (this.size < 2) {
            return result;
        }
        int index = 1;
        int smallerChild = 2;
        final double cost = val.combinedCost;
        do {
            PathNode smallerChildNode = this.array[smallerChild];
            double smallerChildCost = smallerChildNode.combinedCost;
            if (smallerChild < this.size) {
                final PathNode rightChildNode = this.array[smallerChild + 1];
                final double rightChildCost = rightChildNode.combinedCost;
                if (smallerChildCost > rightChildCost) {
                    ++smallerChild;
                    smallerChildCost = rightChildCost;
                    smallerChildNode = rightChildNode;
                }
            }
            if (cost <= smallerChildCost) {
                break;
            }
            this.array[index] = smallerChildNode;
            this.array[smallerChild] = val;
            val.heapPosition = smallerChild;
            smallerChildNode.heapPosition = index;
            index = smallerChild;
        } while ((smallerChild <<= 1) <= this.size);
        return result;
    }
    
    public void clear() {
        if (this.size == 0) {
            return;
        }
        for (int i = 0; i < this.size; ++i) {
            this.array[i] = null;
        }
        this.size = 0;
    }
}
