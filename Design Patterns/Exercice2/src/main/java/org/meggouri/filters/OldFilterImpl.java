package org.meggouri.filters;

import java.util.Arrays;

public class OldFilterImpl implements OldFilter {
    @Override
    public int[] applyFilter(String filterName, int[] data) {

        return Arrays.stream(data)
                .map(val -> val + 3).toArray();
    }
}
