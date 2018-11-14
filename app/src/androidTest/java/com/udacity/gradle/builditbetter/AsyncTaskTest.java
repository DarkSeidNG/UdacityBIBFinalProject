package com.udacity.gradle.builditbetter;

import android.test.AndroidTestCase;

public class AsyncTaskTest extends AndroidTestCase {
    private String mResult;

    public void testAsyncTask() {
        BackendAsyncTask backendAsyncTask = new BackendAsyncTask(null);
        backendAsyncTask.execute(getContext());

        mResult = backendAsyncTask.get();

        assertNotNull(mResult);
    }

}
