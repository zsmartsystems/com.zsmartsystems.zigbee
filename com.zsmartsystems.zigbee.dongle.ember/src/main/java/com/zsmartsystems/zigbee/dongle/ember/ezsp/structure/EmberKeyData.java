package com.zsmartsystems.zigbee.dongle.ember.ezsp.structure;

public class EmberKeyData {
    private final int key[] = new int[16];

    public EmberKeyData(int[] keyData) {
        setKey(keyData);
    }
    
    
    public EmberKeyData() {
    }


    /**
     * @return the key
     */
    public int[] getKey() {
        return key;
    }
    
    public void setKey(int[] newKey) {
        if(newKey.length != 16) {
            return;
        }
        
        for(int cnt = 0; cnt < 16; cnt++) {
            key[cnt] = newKey[cnt];
        }
    }
}
