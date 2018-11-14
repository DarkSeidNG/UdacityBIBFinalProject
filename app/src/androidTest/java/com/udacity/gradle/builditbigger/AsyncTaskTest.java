package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.util.Log;

import java.util.concurrent.ExecutionException;

public class AsyncTaskTest extends AndroidTestCase {
    private String mResult;

    public void testAsyncTask() {
        BackendAsyncTask backendAsyncTask = new BackendAsyncTask(null);
        backendAsyncTask.execute(getContext());

        try {
            mResult = backendAsyncTask.get();
            if(mResult.equals("")){
                mResult = null;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Log.w("Test result", mResult);
        assertNotNull(mResult);
    }

}
