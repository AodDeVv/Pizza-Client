// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.collections.fastutil.sets;

import java.util.Arrays;
import qolskyblockmod.pizzaclient.util.collections.fastutil.HashUtil;

public class ObjectOpenHashSet<K>
{
    protected K[] key;
    protected int mask;
    protected int n;
    protected int maxFill;
    protected final int minN;
    protected int size;
    protected final float f;
    
    public ObjectOpenHashSet(final int expected, final float f) {
        this.f = f;
        final int arraySize = HashUtil.arraySize(expected, f);
        this.n = arraySize;
        this.minN = arraySize;
        this.mask = this.n - 1;
        this.maxFill = HashUtil.maxFill(this.n, f);
        this.key = (K[])new Object[this.n + 1];
    }
    
    public ObjectOpenHashSet(final int expected) {
        this(expected, 0.7f);
    }
    
    public ObjectOpenHashSet(final float expected) {
        this(16, expected);
    }
    
    public ObjectOpenHashSet() {
        this(16, 0.7f);
    }
    
    public void add(final K k) {
        final K[] key = this.key;
        int pos;
        K curr;
        if ((curr = key[pos = (HashUtil.mix(k.hashCode()) & this.mask)]) != null) {
            if (curr.equals(k)) {
                return;
            }
            while ((curr = key[pos = (pos + 1 & this.mask)]) != null) {
                if (curr.equals(k)) {
                    return;
                }
            }
        }
        key[pos] = k;
        if (this.size++ >= this.maxFill) {
            this.rehash(HashUtil.arraySize(this.size + 1, this.f));
        }
    }
    
    public boolean contains(final Object k) {
        final K[] key = this.key;
        int pos;
        K curr;
        if ((curr = key[pos = (HashUtil.mix(k.hashCode()) & this.mask)]) == null) {
            return false;
        }
        if (k.equals(curr)) {
            return true;
        }
        while ((curr = key[pos = (pos + 1 & this.mask)]) != null) {
            if (k.equals(curr)) {
                return true;
            }
        }
        return false;
    }
    
    public void remove(final Object k) {
        final K[] key = this.key;
        int pos;
        K curr;
        if ((curr = key[pos = (HashUtil.mix(k.hashCode()) & this.mask)]) == null) {
            return;
        }
        if (k.equals(curr)) {
            this.removeEntry(pos);
            return;
        }
        while ((curr = key[pos = (pos + 1 & this.mask)]) != null) {
            if (k.equals(curr)) {
                this.removeEntry(pos);
            }
        }
    }
    
    private void removeEntry(final int pos) {
        --this.size;
        this.shiftKeys(pos);
        if (this.n > this.minN && this.size < this.maxFill / 4 && this.n > 16) {
            this.rehash(this.n / 2);
        }
    }
    
    protected final void shiftKeys(int pos) {
        final K[] key = this.key;
        int last = 0;
    Label_0006:
        while (true) {
            pos = ((last = pos) + 1 & this.mask);
            K curr;
            while ((curr = key[pos]) != null) {
                final int slot = HashUtil.mix(curr.hashCode()) & this.mask;
                Label_0090: {
                    if (last <= pos) {
                        if (last >= slot) {
                            break Label_0090;
                        }
                        if (slot > pos) {
                            break Label_0090;
                        }
                    }
                    else if (last >= slot && slot > pos) {
                        break Label_0090;
                    }
                    pos = (pos + 1 & this.mask);
                    continue;
                }
                key[last] = curr;
                continue Label_0006;
            }
            break;
        }
        key[last] = null;
    }
    
    protected void rehash(final int newN) {
        final K[] key = this.key;
        final int mask = newN - 1;
        final K[] newKey = (K[])new Object[newN + 1];
        int i = this.n;
        int j = this.size;
        while (j-- != 0) {
            while (key[--i] == null) {}
            int pos;
            if (newKey[pos = (HashUtil.mix(key[i].hashCode()) & mask)] != null) {
                while (newKey[pos = (pos + 1 & mask)] != null) {}
            }
            newKey[pos] = key[i];
        }
        this.n = newN;
        this.mask = mask;
        this.maxFill = HashUtil.maxFill(this.n, this.f);
        this.key = newKey;
    }
    
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    public int size() {
        return this.size;
    }
    
    public void clear() {
        if (this.size == 0) {
            return;
        }
        this.size = 0;
        Arrays.fill(this.key, null);
    }
}
