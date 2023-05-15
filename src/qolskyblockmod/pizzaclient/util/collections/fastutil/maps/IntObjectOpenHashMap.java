// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.collections.fastutil.maps;

import java.util.Arrays;
import qolskyblockmod.pizzaclient.util.collections.fastutil.HashUtil;

public class IntObjectOpenHashMap<V>
{
    protected int mask;
    protected int n;
    protected int maxFill;
    protected final int minN;
    protected int size;
    protected final float f;
    protected int[] key;
    protected V[] value;
    
    public IntObjectOpenHashMap(final int expected, final float f) {
        this.f = f;
        final int arraySize = HashUtil.arraySize(expected, f);
        this.n = arraySize;
        this.minN = arraySize;
        this.mask = this.n - 1;
        this.maxFill = HashUtil.maxFill(this.n, f);
        this.key = new int[this.n + 1];
        this.value = (V[])new Object[this.n + 1];
    }
    
    public IntObjectOpenHashMap(final int expected) {
        this(expected, 0.75f);
    }
    
    public void put(final int k, final V v) {
        final int pos = this.find(k);
        if (pos < 0) {
            this.insert(-pos - 1, k, v);
            return;
        }
        this.value[pos] = v;
    }
    
    private void insert(final int pos, final int k, final V v) {
        this.key[pos] = k;
        this.value[pos] = v;
        if (this.size++ >= this.maxFill) {
            this.rehash(HashUtil.arraySize(this.size + 1, this.f));
        }
    }
    
    private int find(final int k) {
        final int[] key = this.key;
        int pos;
        int curr;
        if ((curr = key[pos = (HashUtil.mix(k) & this.mask)]) == 0) {
            return -(pos + 1);
        }
        if (k == curr) {
            return pos;
        }
        while ((curr = key[pos = (pos + 1 & this.mask)]) != 0) {
            if (k == curr) {
                return pos;
            }
        }
        return -(pos + 1);
    }
    
    private void rehash(final int newN) {
        final int[] key = this.key;
        final V[] value = this.value;
        final int mask = newN - 1;
        final int[] newKey = new int[newN + 1];
        final V[] newValue = (V[])new Object[newN + 1];
        int i = this.n;
        int j = this.size;
        while (j-- != 0) {
            while (key[--i] == 0) {}
            int pos;
            if (newKey[pos = (HashUtil.mix(key[i]) & mask)] != 0) {
                while (newKey[pos = (pos + 1 & mask)] != 0) {}
            }
            newKey[pos] = key[i];
            newValue[pos] = value[i];
        }
        newValue[newN] = value[this.n];
        this.n = newN;
        this.mask = mask;
        this.maxFill = HashUtil.maxFill(this.n, this.f);
        this.key = newKey;
        this.value = newValue;
    }
    
    public V get(final int k) {
        final int[] key = this.key;
        int pos;
        int curr;
        if ((curr = key[pos = (HashUtil.mix(k) & this.mask)]) == 0) {
            return null;
        }
        if (k == curr) {
            return this.value[pos];
        }
        while ((curr = key[pos = (pos + 1 & this.mask)]) != 0) {
            if (k == curr) {
                return this.value[pos];
            }
        }
        return null;
    }
    
    public int getIndex(final int k) {
        final int[] key = this.key;
        int pos;
        int curr;
        if ((curr = key[pos = (HashUtil.mix(k) & this.mask)]) == 0) {
            return -1;
        }
        if (k == curr) {
            return pos;
        }
        while ((curr = key[pos = (pos + 1 & this.mask)]) != 0) {
            if (k == curr) {
                return pos;
            }
        }
        return -1;
    }
    
    public V getFromIndex(final int index) {
        return this.value[index];
    }
    
    public boolean containsKey(final int k) {
        final int[] key = this.key;
        int pos;
        int curr;
        if ((curr = key[pos = (HashUtil.mix(k) & this.mask)]) == 0) {
            return false;
        }
        if (k == curr) {
            return true;
        }
        while ((curr = key[pos = (pos + 1 & this.mask)]) != 0) {
            if (k == curr) {
                return true;
            }
        }
        return false;
    }
    
    private void removeEntry(final int pos) {
        this.value[pos] = null;
        --this.size;
        this.shiftKeys(pos);
        if (this.n > this.minN && this.size < this.maxFill / 4 && this.n > 16) {
            this.rehash(this.n / 2);
        }
    }
    
    public void remove(final int k) {
        final int[] key = this.key;
        int pos;
        int curr;
        if ((curr = key[pos = (HashUtil.mix(k) & this.mask)]) == 0) {
            return;
        }
        if (k == curr) {
            this.removeEntry(pos);
            return;
        }
        while ((curr = key[pos = (pos + 1 & this.mask)]) != 0) {
            if (k == curr) {
                this.removeEntry(pos);
            }
        }
    }
    
    protected final void shiftKeys(int pos) {
        final int[] key = this.key;
        int last = 0;
    Label_0006:
        while (true) {
            pos = ((last = pos) + 1 & this.mask);
            int curr;
            while ((curr = key[pos]) != 0) {
                final int slot = HashUtil.mix(curr) & this.mask;
                Label_0094: {
                    if (last <= pos) {
                        if (last >= slot) {
                            break Label_0094;
                        }
                        if (slot > pos) {
                            break Label_0094;
                        }
                    }
                    else if (last >= slot && slot > pos) {
                        break Label_0094;
                    }
                    pos = (pos + 1 & this.mask);
                    continue;
                }
                key[last] = curr;
                this.value[last] = this.value[pos];
                continue Label_0006;
            }
            break;
        }
        key[last] = 0;
        this.value[last] = null;
    }
    
    public void clear() {
        if (this.size == 0) {
            return;
        }
        this.size = 0;
        Arrays.fill(this.key, 0);
        Arrays.fill(this.value, null);
    }
    
    public int size() {
        return this.size;
    }
}
