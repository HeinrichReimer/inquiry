package com.heinrichreimer.inquiry.convert.converters.arrays.primitive;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.heinrichreimer.inquiry.ContentValue;
import com.heinrichreimer.inquiry.Inquiry;
import com.heinrichreimer.inquiry.convert.Converter;

import java.io.IOException;

public class PrimitiveBooleanArrayConverter extends Converter<boolean[], byte[]> {
    @Nullable
    @Override
    public boolean[] convert(@NonNull Inquiry inquiry, @NonNull ContentValue<byte[]> value, @NonNull Class<? extends boolean[]> fieldType) throws IOException {
        byte[] blob = value.getContent();
        if (blob == null)
            return null;
        boolean[] booleans = new boolean[value.getContent().length];
        for (int i = 0; i < blob.length; i++) {
            booleans[i] = blob[i] == 1;
        }
        return booleans;
    }

    @NonNull
    @Override
    public ContentValue<byte[]> convert(@NonNull Inquiry inquiry, @Nullable boolean[] value) throws IOException {
        if (value == null)
            return ContentValue.valueOf((byte[]) null);
        byte[] blob = new byte[value.length];
        for (int i = 0; i < value.length; i++) {
            blob[i] = (byte) (value[i] ? 1 : 0);
        }
        return ContentValue.valueOf(blob);
    }

    @NonNull
    @Override
    public Class<boolean[]> getInputType() {
        return boolean[].class;
    }

    @NonNull
    @Override
    public Class<byte[]> getOutputType() {
        return byte[].class;
    }
}
